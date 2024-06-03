package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетКарточкаКонтакта
import Bitrix.modules.applets.АпплетСписокКомпаний
import Bitrix.modules.applets.АпплетСписокКонтактов
import Bitrix.modules.toolBars.ПанельИнструментовКомпании
import Bitrix.modules.toolBars.ПанельИнструментовКонтакты

class CompanyListPage extends BitrixPage{
    String nameObj="company/list"
    static content = {
        ПанельИнструментовКомпании{ module new ПанельИнструментовКомпании() }
        АпплетСписокКомпаний{module new АпплетСписокКомпаний()}

    }
}
