package Bitrix.modules.applets

import Bitrix.TableModuleBase
import geb.Module
import org.openqa.selenium.By

class АпплетСписокТоваров extends Module{
    static content = {
        Таблица{module new TableModuleBase(статическийID:'CrmProductGrid_table')}

    }
}
