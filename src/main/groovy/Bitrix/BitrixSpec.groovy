package Bitrix

import Bitrix.pages.AutorizePage
import Bitrix.pages.DealPage
import Bitrix.objects.Пользователь
import geb.spock.GebSpec
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.WebDriver
import spock.lang.Shared

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BitrixSpec extends GebSpec{

    @Shared WebDriver driver
    @Shared protected static  Logger logger
    @Shared boolean stepPassed
    @Shared запущенныйТест =this.getClass().getName()
    @Shared String датаЗапуска
    @Shared String датаОкончанияТеста
    @Shared Пользователь пользователь
    def setupSpec(){
        logger = LogManager.getLogger(this.getClass())
        датаЗапуска = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
        logger.info("Запущен тест: $запущенныйТест")
        driver = getNewDriver()
        созданиеИлиОбновлениеЗаписиОТесте('start')
    }

    def setup(){
        logger.info(specificationContext.currentIteration.name)
        stepPassed=false
    }
    def cleanupSpec(){
        driver.close()
        logger.info("Завершен тест: $запущенныйТест")
        if(пользователь!=null) освободитьПользователя(пользователь)
         датаОкончанияТеста = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
        созданиеИлиОбновлениеЗаписиОТесте('end')


    }


    WebDriver getNewDriver(){
        browser.getDriver()
    }

    def созданиеИлиОбновлениеЗаписиОТесте(String startOrEnd){
        if(startOrEnd=='start') {
            logger.info("Проверяем есть ли тест в таблице 'tests'")
            String query = "SELECT * FROM autotests WHERE testName='$запущенныйТест'"
            if (DataBase.doSelectQuery(query).size() > 0) {
                logger.info("Тест присутствует в таблице")
                query = "UPDATE autotests SET lastDateStart = '$датаЗапуска',lastStatus = 'RUNNING' WHERE testName='$запущенныйТест' "
                DataBase.doDbScript(query)
            } else {
                logger.info("Выполняется первый запуск теста, приступаем к добалению записи в таблицу 'testslist'")
                query = "INSERT INTO `bitrixat`.`autotests` (`firstDateStart`, `lastDateStart`, `lastDateStop`, `testName`, `lastStatus`) VALUES ('$датаЗапуска', '$датаЗапуска', 'FirstRun', '$запущенныйТест', 'RUNNING')"
                DataBase.doDbScript(query)
            }
        }
        else if(startOrEnd=='end'){
            датаОкончанияТеста = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
            if(stepPassed){
                String query="UPDATE autotests SET lastDateStop = '$датаОкончанияТеста', lastStatus = 'PASS' WHERE testName = '$запущенныйТест' "
                DataBase.doDbScript(query)
            }
            else {
                String query="UPDATE `bitrixat`.`autotests` SET `lastDateStop` = '$датаОкончанияТеста', `lastStatus` = 'FAILED' WHERE `testName` = '$запущенныйТест'"
                DataBase.doDbScript(query)
            }
        }
        else throw new Exception("Передано некоректное значение, метод принимает на вход: 'start' либо 'end'")
    }


    def выполнитьВходВБитрикс(String логин,String пароль){
        try {
            if (!browser.driver) driver = getNewDriver()
            go 'https://b24-wqf2lv.bitrix24.ru'
            at(AutorizePage).with {
                logger.info("Перешли на страницу авторизации")
                waitFor {
                    Поле_Логин.value(логин)
                    Поле_Логин.value() == логин
                }
                logger.info("Заполнили поле логин значением $логин")
                Кнопка_Далее.click()
                waitFor { Поле_Пароль.displayed }
                waitFor {
                    Поле_Пароль.value(пароль)
                    Поле_Пароль.value() == пароль
                }
                logger.info("Заполнили поле пароль")
                Кнопка_Далее.click()
            }
            at DealPage
        }
        catch (Exception e){
            e.printStackTrace()
            logger.info("Ошибка при авторизации пользователя")
        }
    }
    def выполнитьВходВБитрикс(Пользователь пользователь){
        выполнитьВходВБитрикс(пользователь.логин,пользователь.пароль)
    }

    def перейтиПоМеню(String путь){
        try {
            logger.info("Осуществляется переход '$путь'")
            List<String> пути = путь.split('->')
            def view = at BitrixPage
            for (String элементПути : пути) {
                logger.info("Нажимаем на $элементПути")
                waitFor { view.ЭлементМеню(элементПути).displayed }
                view.ЭлементМеню(элементПути).click()
            }
        }
        catch (Exception e){
            logger.info(e.printStackTrace())
        }
    }

    def обновитьСтраницуБраузера(){
        try {
            logger.info('Производим обновление страницы средствами webdriver')
            sleep(2000)
            browser.driver.navigate().refresh()
            sleep(2000)
        }
        catch (Exception e){
            e.getStackTrace()
        }
    }

    Пользователь получитьСвободногоПользователя(){
        logger.info('Осуществляется поиск свободного пользователя')
        String логин = DataBase.doSelectQuery("SELECT Employee FROM users WHERE isUse='0'").get(0).get('Employee')
        if (логин==''||логин==null) throw new Exception("Отсутствуют свободные пользователи")
        logger.info("Занимаем пользователя в БД")
        DataBase.doDbScript("UPDATE users SET isUse = '1' WHERE (`Employee` = '$логин')")
        logger.info("Получен пользователь '$логин'")
        String ключЛогина
        try {
            ключЛогина = Config.properties.find {it->it.value=='xifural75@yandex.ru'}.getKey().toString()
            String пароль = Config.getProperty("userPassword${ключЛогина.substring(ключЛогина.length()-1)}")
            пользователь = new Пользователь(логин,пароль)
            пользователь
        }
       catch (Exception e){
           e.printStackTrace()
       }
    }

    def освободитьПользователя(Пользователь пользователь){
        logger.info("Освобождается пользователь ${пользователь.getЛогин()}")
        DataBase.doDbScript("UPDATE users SET isUse = '0' WHERE (`Employee` = '${пользователь.логин}')")
        sleep(2000)
        if(DataBase.doSelectQuery("SELECT isUse FROM users WHERE Employee='${пользователь.логин}'").get(0).get('isUse')!="0")
            throw new Exception("Значение isUse не было изменено на '0'")
    }


}
