package Bitrix.modules.applets

import Bitrix.TableModule
import geb.Module
import org.openqa.selenium.By

class АпплетСписокКонтактов extends Module{
    static content = {
        Таблица{module new TableModule(статическийID:'CRM_CONTACT_LIST_V12')}
        КнопкаПоказатьКоличество{$(By.xpath("//a[text()='Показать количество']"))}
        ПолеКоличествоКонтактов{$(By.xpath("//div[@id='crm_contact_list_v12_row_count_wrapper']"))}

    }
    Integer количествоКонтактов(){
        if(КнопкаПоказатьКоличество.displayed) КнопкаПоказатьКоличество.click()
        waitFor {ПолеКоличествоКонтактов.displayed}
        int колвоКонтактов =ПолеКоличествоКонтактов.text().split(' ')[ПолеКоличествоКонтактов.text().split(' ').size()-1] as Integer
        assert колвоКонтактов!=null
        колвоКонтактов
    }
}
