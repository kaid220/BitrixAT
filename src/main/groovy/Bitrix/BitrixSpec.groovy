package Bitrix

import Bitrix.pages.AutorizePage
import Bitrix.pages.DealPage
import geb.spock.GebSpec
import org.apache.log4j.*
import org.openqa.selenium.WebDriver
import spock.lang.Shared

class BitrixSpec extends GebSpec{

    @Shared WebDriver driver
    @Shared protected static Logger logger
    def setupSpec(){
        logger= Logger.getLogger(this.getClass().getName())
        driver = getNewDriver()
    }
    def cleanupSpec(){
        driver.close()
    }

    WebDriver getNewDriver(){
        browser.getDriver()
    }


    def выполнитьВходВБитрикс(String логин,String пароль){
        if(!browser.driver) driver = getNewDriver()
        go 'https://b24-wqf2lv.bitrix24.ru'
        at(AutorizePage).with {
            logger.info("Перешли на страницу авторизации")
            waitFor {Поле_Логин.value(логин)
            Поле_Логин.value()==логин}
            logger.info("Заполнили поле логин значением $логин")
            Кнопка_Далее.click()
            waitFor {Поле_Пароль.displayed}
            waitFor {Поле_Пароль.value(пароль)
                Поле_Пароль.value()==пароль}
            logger.info("Заполнили поле пароль")
            Кнопка_Далее.click()
        }
        at DealPage

    }

    def перейтиПоМеню(String путь){
        logger.info("Осуществляется переход '$путь'")
        List<String> пути = путь.split('->')
        def view = at BitrixPage
        for (String элементПути : пути){
            logger.info("Нажимаем на $элементПути")
            waitFor { view.ЭлементМеню(элементПути).displayed}
            view.ЭлементМеню(элементПути).click()
        }
    }




}
