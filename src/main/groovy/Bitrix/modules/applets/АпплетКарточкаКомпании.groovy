package Bitrix.modules.applets

import Bitrix.modules.dialogs.ДиалогСозданиеКомпании
import Bitrix.modules.dialogs.ДиалогСозданиеКонтакта
import geb.Module
import org.openqa.selenium.By

class АпплетКарточкаКомпании extends Module{
    static content={
        Кнопка_ЗакрытьКарточку{$(By.xpath("//div[@title='Закрыть']"))}
        Диалог_СозданиеКомпании{module new ДиалогСозданиеКомпании()}
    }
}
