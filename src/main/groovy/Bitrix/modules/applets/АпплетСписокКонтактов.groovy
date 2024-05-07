package Bitrix.modules.applets

import Bitrix.TableModule
import geb.Module
import org.openqa.selenium.By
import org.openqa.selenium.Keys

import java.awt.event.KeyEvent

class АпплетСписокКонтактов extends Module{
    static content = {
        Таблица{module new TableModule(статическийID:'CRM_CONTACT_LIST_V12')}
        КнопкаПоказатьКоличество(required:false){$(By.xpath("//a[text()='Показать количество']"))}
        ПолеКоличествоКонтактов{$(By.xpath("//div[@id='crm_contact_list_v12_row_count_wrapper']"))}

    }

}
