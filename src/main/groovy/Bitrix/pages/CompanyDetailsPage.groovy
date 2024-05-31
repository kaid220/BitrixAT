package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетКарточкаКомпании
import Bitrix.modules.applets.АпплетКарточкаКонтакта

class CompanyDetailsPage extends BitrixPage{
    String nameObj="company/details"
    static content = {
        Апплет_КарточкаКомпании{module new АпплетКарточкаКомпании()}

    }
}
