package Bitrix

import geb.Page
import org.openqa.selenium.By

class BitrixPage extends Page{
    String nameObj
    static at={browser.driver.currentUrl.contains(nameObj)}
    static content={
        Меню{find(By.className('main-buttons-box'))}
        ЭлементМенюПервогоУровня{ значение->Меню.find(By.xpath("//span[text()='$значение' and @class='main-buttons-item-text-box']"))}
        //ЭлементМенюВторогоУровня{String значение-> find(By.xpath("//span[text()='$значение' and @class='']"))}
    }

}


