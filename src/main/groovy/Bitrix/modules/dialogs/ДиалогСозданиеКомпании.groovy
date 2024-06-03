package Bitrix.modules.dialogs

import Bitrix.DialogBase
import Bitrix.DropDownBase
import geb.Module
import org.openqa.selenium.By

class ДиалогСозданиеКомпании extends DialogBase{
    static content ={
        IFrame{find(By.xpath("//div[@class='side-panel-content-container']/iframe"))}
        container{browser.find(By.xpath("//body//div[@class='crm-entity-card-container']"))}
        Поле_ТипКомпании{module new DropDownBase(input: "data-cid='COMPANY_TYPE'")}
        Поле_ТипДеятельности{module new DropDownBase(input: "data-cid='INDUSTRY'")}
        Поле_Название{container.$(By.xpath("//input[@name='TITLE']"))}
        Поле_НомерТелефона{container.$(By.xpath("//input[@class='crm-entity-widget-content-input crm-entity-widget-content-input-phone']"))}
        Кнопка_Сохранить{$(By.xpath("//button[text()='Сохранить'][@title='[Ctrl+Enter]']"))}
    }
    void заполнитьПолеНазвание(String название){
        waitFor {
            Поле_Название.click()
            Поле_Название.value(название)
            Поле_Название.value()==название
        }

        logger.info("Поле название заполнено значением $название")
    }
    void заполнитьПолеТипКомпании(String типКомпании) {
            Поле_ТипКомпании.выбратьЭлемент(типКомпании)
            logger.info("Поле 'Тип компании' заполнено значением $типКомпании")

    }
    void заполнитьПолеСфераДеятельности(String сфераДеятельность) {
            Поле_ТипДеятельности.выбратьЭлемент(сфераДеятельность)
            logger.info("Поле 'Сфера деятельности' заполнено значением $сфераДеятельность")
    }
    void заполнитьПолеНомерТелефона(String номерТелефона="+79122456789"){
            Поле_НомерТелефона.value(номерТелефона)
            assert Поле_НомерТелефона.value() ==
                    "${номерТелефона.substring(0,2)} ${номерТелефона.substring(2,5)} ${номерТелефона.substring(5,8)}-${номерТелефона.substring(8,10)}-${номерТелефона.substring(10)}"
            logger.info("Поле номерТелефона заполнено значением $номерТелефона")

    }
    void сохранитьЗапись(){
        Кнопка_Сохранить.click()
    }
}
