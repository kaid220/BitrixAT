package Bitrix

import geb.Page
import org.openqa.selenium.By

class BitrixPage extends Page{
    String nameObj="bitrix24"
    static at={waitFor {browser.driver.currentUrl.contains(nameObj)}}
    static content={
        Меню{find(By.className('main-buttons-box'))}
        ЭлементМеню{ значение->Меню.find(By.xpath("//span[text()='$значение' and @class='main-buttons-item-text-box']"))}
        МенюПрофиля{}
        открытьМенюПрофиля{$('span.user-name').click()}
        КнопкаВыйти(required:false){$(By.xpath("//div[text()='Выйти']/parent::div/parent::*"))}
    }
}


