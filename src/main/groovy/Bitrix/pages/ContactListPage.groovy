package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетСписокКонтактов
import Bitrix.modules.toolBars.ПанельИнструментовКонтакты

class ContactListPage extends BitrixPage{
    String nameObj="contact/list"
    static content = {
        ПанельИнструментовКонтакты{ module new ПанельИнструментовКонтакты() }
        Апплет_СписокКонтактов{module new АпплетСписокКонтактов()}
    }
}
