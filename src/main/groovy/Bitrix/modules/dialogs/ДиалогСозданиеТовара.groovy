package Bitrix.modules.dialogs

import Bitrix.DialogBase
import Bitrix.DropDownBase
import geb.Module
import org.openqa.selenium.By

class ДиалогСозданиеТовара extends DialogBase{
    static content ={
        IFrame{find(By.xpath("//div[@class='side-panel-content-container']/iframe"))}
        container{browser.find(By.xpath("//body//div[@class='ui-side-panel-content']"))}
        Поле_Наименование{container.$(By.xpath("//input[@name='NAME']"))}
        Кнопка_Сохранить{$(By.xpath("//button[text()='Сохранить'][@title='[Ctrl+Enter]']"))}
    }
    void заполнитьПолеНаименование(String наименованиеТовара) {
            Поле_Наименование.value(наименованиеТовара)
            assert Поле_Наименование.value() == наименованиеТовара
            logger.info("Поле наименование заполнено значением $наименованиеТовара")
    }
    void сохранитьЗапись(){
        Кнопка_Сохранить.click()
    }
}
