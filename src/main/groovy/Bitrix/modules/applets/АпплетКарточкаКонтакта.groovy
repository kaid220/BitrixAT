package Bitrix.modules.applets

import Bitrix.modules.dialogs.ДиалогСозданиеКонтакта
import geb.Module
import org.openqa.selenium.By

class АпплетКарточкаКонтакта extends Module{
    static content={
        Кнопка_ЗакрытьКарточку{$(By.xpath("//div[@title='Закрыть']"))}
        Диалог_СозданиеКонтакта{module new ДиалогСозданиеКонтакта()}
    }
}
