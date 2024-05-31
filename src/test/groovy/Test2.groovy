

import Bitrix.BitrixSpec
import Bitrix.Config
import Bitrix.objects.Пользователь
import Bitrix.pages.AutorizePage
import Bitrix.pages.DealPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Title('Тест 2: Авторизация, проверка негативного и позитивного сценария')
@Stepwise
class Test2 extends BitrixSpec {
    @Shared
    String логин
    @Shared
    Пользователь пользователь1

    def 'Шаг 1: Получить логин тестового пользователя'() {
        given:
        пользователь1 = получитьСвободногоПользователя()
        логин = пользователь.логин
        logger.info("ОР: Пользователь получен")
        stepPassed = true
    }

    def 'Шаг 2:  Осуществить попытку авторизации в Bitrix24, используя логин отсутствующий в системе'() {
        given:
        go Config.getProperty("baseurl")
        at(AutorizePage).with {
            logger.info("Перешли на страницу авторизации")
            String некорректныйЛогин = логин+'forNegative'
            waitFor {
                Поле_Логин.value(некорректныйЛогин)
                Поле_Логин.value() == некорректныйЛогин
            }
            logger.info("Заполнили поле логин значением $некорректныйЛогин")
            Кнопка_Далее.click()
            assert !Поле_Пароль.displayed
            waitFor {СообщениеОНекорректностиЛогина.displayed}
        }
        logger.info("Поле для ввода пароля не отображается, отображается сообщение о некорректности логина")
        stepPassed = true
    }
    def 'Шаг 3: Ввести в поле для логина, логин зафиксированный ранее'() {
        given:
        at(AutorizePage).with {
            waitFor {
                Поле_Логин.value(логин)
                Поле_Логин.value() == логин
            }
            logger.info("Заполнили поле логин значением $логин")
            Кнопка_Далее.click()
            waitFor { Поле_Пароль.displayed }
            logger.info('Отображается поле для ввода пароля')
            stepPassed = true
        }
    }
    def 'Шаг 4: Осуществить попытку авторизации в Bitrix24, введя не верный пароль'() {
        given:
        String некорректныйПароль = пользователь1.пароль + "Negative"
        at(AutorizePage).with {
            waitFor {
                Поле_Пароль.value(некорректныйПароль)
                Поле_Пароль.value() == некорректныйПароль
            }
            logger.info("Заполнили поле пароль")
            Кнопка_Далее.click()
            waitFor {СообщениеОНекорректностиПароля.displayed}

        }
        logger.info("Отобразилось информационное сообщение о некорректности введеных данных")
        stepPassed = true
    }
    def 'Шаг 5: Ввести в поле для ввода пароля корректный пароль и нажать на кнопку далее'() {
        given:
        at(AutorizePage).with {
            waitFor {
                Поле_Пароль.value(пользователь1.пароль)
                Поле_Пароль.value() == пользователь1.пароль
            }
            logger.info("Заполнили поле пароль")
            Кнопка_Далее.click()
            logger.info("Нажали на кнопку Далее")
        }
        sleep(3000)
        assert !page(AutorizePage).Поле_Капча.displayed : "Появилась капча, необходимо корректно осуществить вход в систему с последующим корректным выходом"
        at DealPage
        logger.info("ОР: Авторизация прошла успешна, осуществлен переход на страницу 'Сделки'")
        stepPassed = true
    }
}

