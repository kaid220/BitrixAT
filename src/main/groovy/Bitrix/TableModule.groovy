package Bitrix

import geb.Module
import geb.navigator.DefaultNavigator
import geb.navigator.Navigator
import org.openqa.selenium.By

class TableModule extends Module{
    static content = {
        String статическийID=""
        Таблица(required: false) {$(By.xpath("//table[@id='$статическийID']"))}
        Колонки(required: false){Таблица.$(By.xpath("//thead/tr/th"))}
        Колонка(required: false){Integer номерКолонки->Колонки[номерКолонки] as Navigator}
        КолонкаПоНазванию(required: false){String названиеКолонки->
            def номерКолонки = номерКолонки(названиеКолонки)
            Колонка(номерКолонки) as Navigator
        }
        Строки(required: false){Таблица?.$('tr') as Navigator}
        Строка(required: false){Integer номерСтроки ->
            def строки = Строки
            assert номерСтроки>0 && строки.size() <= строки.size() : "Номер строки ($номерСтроки) больше числа строк в таблице (${строки.size()})"
            строки[номерСтроки-1] as Navigator
        }
    }

    int номерКолонки(String имяКолонки){
        def номерКолонки = waitFor{Колонки?.findIndexOf {имяКолонки == названиеКолонки(it)}+1  }
        assert номерКолонки>=0
        номерКолонки
    }

    String названиеКолонки(Navigator колонка){
        def название = колонка?.text()?.trim()
        название
    }
}
