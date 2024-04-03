import Bitrix.BitrixPage
import Bitrix.BitrixSpec
import Bitrix.page.DealPage

class test extends BitrixSpec{
    def 'loginCase'(){
        given:
        выполнитьВходВБитрикс('xifural75@yandex.ru','AutoTests123.')
        перейтиПоМеню('Клиенты->Контакты')
    }
}