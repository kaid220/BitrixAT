package uiTests


import Bitrix.BitrixSpec
import Bitrix.Config
import Bitrix.pages.ContactDetailsPage
import Bitrix.pages.ContactListPage
import spock.lang.Shared

class test extends BitrixSpec{
    def "Выполнить вход в Битрикс24"() {
        given:
        выполнитьВходВБитрикс(Config.getProperty("userLogin1"), Config.getProperty("userPassword1"))
        logger.info("ОР: Осуществлен вход в Битрикс24")
    }
    def "Перейти по меню Клиенты - Контакты"(){
        given:
        перейтиПоМеню('Клиенты->Контакты')
        at ContactListPage
        logger.info("ОР: Открылась страница 'Контакты'")
    }
    @Shared Integer колвоКонтактов
    def "Зафиксировать текущее количество контактов"(){
        given:
        waitFor {ContactListPage.Апплет_СписокКонтактов.КнопкаПоказатьКоличество.displayed}
        колвоКонтактов = ContactListPage.Апплет_СписокКонтактов.количествоКонтактов()
        logger.info("Количество контактов ='$колвоКонтактов'")
        logger.info("ОР: Значение зафиксированно")
    }
    def "Нажать на кнопку 'Создать'"(){
        given:
        ContactListPage.ПанельИнструментовКонтакты.КнопкаСоздать.click()
        assert at(ContactDetailsPage).Диалог_СозданиеКонтакта.displayed
        logger.info("????????? ????? ???????? ????????")
    }

  /*  def "????????? ????: ?????????, ???????, ???, ????????, ???? ????????, ?????????, ???????, ?????"(){
        given:

    }*/
}