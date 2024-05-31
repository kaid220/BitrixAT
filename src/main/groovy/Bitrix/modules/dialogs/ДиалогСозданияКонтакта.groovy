package Bitrix.modules.dialogs

import Bitrix.DialogBase
import Bitrix.DropDownBase
import org.openqa.selenium.By

class ДиалогСозданияКонтакта extends DialogBase{
    static content ={
        IFrame{find(By.xpath("//div[@class='side-panel-content-container']/iframe"))}
        container{browser.find(By.xpath("//body//div[@class='crm-entity-card-container']"))}
        Поле_Обращение{
                module new DropDownBase(input: "data-cid='HONORIFIC'")
        }
        Поле_Фамилия{container.$(By.xpath("//input[@name='LAST_NAME']"))}
        Поле_Имя(required: false){container.$(By.xpath("//input[@name='NAME']"))}
        Поле_Отчество{container.$(By.xpath("//input[@name='SECOND_NAME']"))}
        Поле_ДатаРождения{container.$(By.xpath("//input[@name='BIRTHDATE']"))}
        Поле_НомерТелефона{container.$(By.xpath("//input[@class='crm-entity-widget-content-input crm-entity-widget-content-input-phone']"))}
        Кнопка_Сохранить{$(By.xpath("//button[text()='Сохранить'][@title='[Ctrl+Enter]']"))}
        СообщениеОбОбязательностиПоляИмя(required:false){Поле_Имя.$(By.xpath("./../following-sibling::div/following-sibling::div[text()='Пожалуйста, введите значение.']"))}
    }

    void заполнитьПолеОбращение(String обращение){
            Поле_Обращение.выбратьЭлемент(обращение)
            logger.info("Поле обращение заполнено значением $обращение")
    }

    void заполнитьПолеИмя(String имя="Иван") {
        withFrame(IFrame) {
            Поле_Имя.value(имя)
            assert Поле_Имя.value() == имя
            logger.info("Поле имя заполнено значением $имя")
        }
    }
    void заполнитьПолеФамилия(String фамилия="Иванов"){
        withFrame(IFrame) {
            Поле_Фамилия.value(фамилия)
            assert Поле_Фамилия.value() == фамилия
            logger.info("Поле фамилия заполнено значением $фамилия")
        }
    }
    void заполнитьПолеОтчество(String отчество="Иванович"){
        withFrame(IFrame) {
            Поле_Отчество.value(отчество)
            assert Поле_Отчество.value() == отчество
            logger.info("Поле отчество заполнено значением $отчество")
        }
    }
    void заполнитьПолеДатаРождения(String датаРождения="03.02.1996"){
        withFrame(IFrame) {
            Поле_ДатаРождения.value(датаРождения)
            assert Поле_ДатаРождения.value() == датаРождения
            logger.info("Поле датаРождения заполнено значением $датаРождения")
        }
    }
    void заполнитьПолеНомерТелефона(String номерТелефона="+79122456789"){
        withFrame(IFrame) {
            Поле_НомерТелефона.value(номерТелефона)
            assert Поле_НомерТелефона.value() ==
                    "${номерТелефона.substring(0,2)} ${номерТелефона.substring(2,5)} ${номерТелефона.substring(5,8)}-${номерТелефона.substring(8,10)}-${номерТелефона.substring(10)}"
            logger.info("Поле номерТелефона заполнено значением $номерТелефона")
        }
    }
    void сохранитьЗапись(){
            Кнопка_Сохранить.click()
    }



}
