package Bitrix.modules.toolBars

import geb.Module
import org.openqa.selenium.By

class ПанельИнструментовКомпании extends Module{
    static content={
        container{$(By.xpath("//div[@id='uiToolbarContainer']"))}
        КнопкаСоздать{container.$(By.xpath("//a/span[text()='Создать']"))}
    }
}
