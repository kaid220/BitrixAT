package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.dialogs.ДиалогСозданияКонтакта


class ContactDetailsPage extends BitrixPage{
    String nameObj="contact/details"
    static content = {
        Диалог_СозданиеКонтакта{ module new ДиалогСозданияКонтакта()}
    }
}
