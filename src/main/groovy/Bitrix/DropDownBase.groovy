package Bitrix

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.By

class DropDownBase extends Module{
    /**
     * обязательное поле
     * испульзуется для поиска input элемента выпадающего списка
     */
    String input=''

    static content = {
        container{$(By.xpath("//div[@$input]"))}
        Поле(required: false) {container.$(By.xpath(".//div[@class='ui-ctl-element']")).firstElement()}
        dropdown{container.$(By.xpath(".//div[contains(@class,'dropdown')]")).firstElement()}
        Название{container.find(By.xpath("//label"))}
        Список(required:false){
            $(By.xpath("//div[contains(@style, 'display: block;')][contains(@id,'menu-popup')]"))
        }
        ЭлементСпискаПоТексту{String текст->Список.$(By.xpath("//span//*[text()='$текст']"))}
    }

    @Override
    Object value(){
        String значениеПоля = waitFor {
            def value = Поле.text
            if(value=='') return '[<EMPTY>]'
            else return value
        }
        return значениеПоля == '[<EMPTY>]' ? '' : значениеПоля.trim()
    }

    Navigator открытьСписок(){
        waitFor {dropdown.click()
            sleep(2000)
        Список.displayed}
            Список
    }

    def выбратьЭлемент(String название){
        открытьСписок()
        waitFor {
            ЭлементСпискаПоТексту(название).displayed
        }
        ЭлементСпискаПоТексту(название).click()
        assert Поле.text==название
    }


}
