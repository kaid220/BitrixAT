package Bitrix.modules.applets

import Bitrix.TableModuleBase
import geb.Module
import org.openqa.selenium.By

class АпплетСписокКонтактов extends Module{
    static content = {
        Таблица{module new TableModuleBase(статическийID:'CRM_CONTACT_LIST_V12')}
        КнопкаПоказатьКоличество(required:false){$(By.xpath("//a[text()='Показать количество']"))}
        ПолеКоличествоКонтактов{$(By.xpath("//div[@id='crm_contact_list_v12_row_count_wrapper']"))}

    }

}
