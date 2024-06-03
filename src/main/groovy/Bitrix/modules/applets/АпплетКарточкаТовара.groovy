package Bitrix.modules.applets

import Bitrix.modules.dialogs.ДиалогСозданиеКонтакта
import Bitrix.modules.dialogs.ДиалогСозданиеТовара
import geb.Module
import org.openqa.selenium.By

class АпплетКарточкаТовара extends Module{
    static content={
        Кнопка_ЗакрытьКарточку{$(By.xpath("//div[@title='Закрыть']"))}
        Диалог_СозданиеТовара{module new ДиалогСозданиеТовара()}
    }
}
