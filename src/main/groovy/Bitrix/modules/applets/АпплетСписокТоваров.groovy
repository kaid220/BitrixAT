package Bitrix.modules.applets

import Bitrix.TableModuleBase
import geb.Module
import org.openqa.selenium.By

class АпплетСписокТоваров extends Module{
    static content = {
        Таблица{module new TableModuleBase(статическийID:'CrmProductGrid_table')}
        ПолеКоличествоТоваров{$(By.xpath("//div[@id='CrmProductGrid_bottom_panels']//span[text()='Всего:']/following::span")).firstElement()}
    }
}
