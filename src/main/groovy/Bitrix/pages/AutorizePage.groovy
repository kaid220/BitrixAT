package Bitrix.pages

import Bitrix.BitrixPage
import org.openqa.selenium.By

class AutorizePage extends BitrixPage{
    String nameObj = 'oauth/authorize/'
    static content ={
        Поле_Логин(required:false){find(By.xpath('//input[@id="login"]'))}
        Поле_Пароль(required:false){$(By.xpath('//input[@id="password"]'))}
        Кнопка_Далее{$(By.xpath('//button[text()="Далее"]'))}
        СообщениеОНекорректностиПароля(required: false){$(By.xpath("//span[text()='Неверный логин или пароль.']"))}
        СообщениеОНекорректностиЛогина(required: false){$(By.xpath("//span[text()='Не удалось найти пользователя с таким e-mail']"))}
        Поле_Капча(required: false){$(By.xpath("//input[@id='captcha-input']"))}
    }
}
