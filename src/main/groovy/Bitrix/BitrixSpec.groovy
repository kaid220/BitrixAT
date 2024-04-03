package Bitrix

import Bitrix.page.AutorizePage
import Bitrix.page.DealPage
import geb.spock.GebSpec
import org.openqa.selenium.WebDriver
import spock.lang.Shared

class BitrixSpec extends GebSpec{
    @Shared WebDriver driver

    def setupSpec(){
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
            waitFor {Поле_Логин.value(логин)
            Поле_Логин.value()==логин}
            Кнопка_Далее.click()
            waitFor {Поле_Пароль.displayed}
            waitFor {Поле_Пароль.value(пароль)
                Поле_Пароль.value()==пароль}
            Кнопка_Далее.click()
        }
        at DealPage
    }
    def перейтиПоМеню(String путь){
        String[] пути = путь.split('->')
        DealPage.ЭлементМенюПервогоУровня(пути[0]).click()
        if(пути.size()>1){
            waitFor {DealPage.ЭлементМенюПервогоУровня(пути[1]).displayed}
            DealPage.ЭлементМенюПервогоУровня(пути[1]).click()
        }
    }




}
