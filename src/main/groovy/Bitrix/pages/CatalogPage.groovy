package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетКарточкаКонтакта
import Bitrix.modules.applets.АпплетСписокКонтактов
import Bitrix.modules.applets.АпплетСписокТоваров

class CatalogPage extends BitrixPage{
    String nameObj = "catalog"
    static content = {
        Апплет_СписокТоваров{module new АпплетСписокТоваров()}

    }
}
