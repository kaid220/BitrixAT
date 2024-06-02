package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетКарточкаКонтакта

class ContactDetailsPage extends BitrixPage{
    String nameObj="contact/details"
    static content = {
        Апплет_КарточкаКонтакта{module new АпплетКарточкаКонтакта()}

    }
}
