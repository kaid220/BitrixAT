


import Bitrix.BitrixSpec
import Bitrix.Config
import Bitrix.DataBase
import Bitrix.pages.ContactDetailsPage
import Bitrix.pages.ContactListPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Title('Тест 1: Создание контакта')
@Stepwise
class Test1 extends BitrixSpec{

    def "Шаг 1: Выполнить вход в Битрикс24"() {
        given:
        пользователь = получитьСвободногоПользователя()
        выполнитьВходВБитрикс(пользователь)
        logger.info("ОР: Осуществлен вход в Битрикс24")
        stepPassed=true
    }
    def "Шаг 2: Перейти по меню Клиенты - Контакты"(){
        given:
        перейтиПоМеню('Клиенты->Контакты')
        at ContactListPage
        logger.info("ОР: Открылась страница 'Контакты'")
        stepPassed=true

    }
    @Shared Integer колвоКонтактов
    def "Шаг 3: Зафиксировать текущее количество контактов"(){
        given:
        waitFor {page(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.displayed}
        for(int i=0;i<10 && at(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.displayed;i++){
            waitFor { page(ContactListPage).ПанельИнструментовКонтакты.КнопкаСоздать.displayed}
            обновитьСтраницуБраузера()
            waitFor { at(ContactListPage).ПанельИнструментовКонтакты.КнопкаСоздать.displayed}
            page(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.click()
            sleep(5000)
            }
            waitFor { !page(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.displayed}
            колвоКонтактов = page(ContactListPage).АпплетСписокКонтактов.ПолеКоличествоКонтактов.text().split(' ')[page(ContactListPage).АпплетСписокКонтактов.ПолеКоличествоКонтактов.text().split(' ').size()-1] as Integer
            assert колвоКонтактов!=null
        logger.info("Количество контактов ='$колвоКонтактов'")
        logger.info("ОР: Значение зафиксированно")
        stepPassed=true

    }
    def "Шаг 4: Нажать на кнопку 'Создать'"(){
        given:
        at(ContactListPage).ПанельИнструментовКонтакты.КнопкаСоздать.click()
        at ContactDetailsPage
        assert page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.displayed
        logger.info("ОР: Открылась форма создания контакта")
        stepPassed=true

    }

    def "Шаг 5: Заполнить поле: Обращение"(){
        given:
          at(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
              withFrame(IFrame) {
                  at(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.заполнитьПолеОбращение('г-н')
              }
          }
          logger.info("ОР: Поле заполнено")
          stepPassed=true

    }

    def "Шаг 6: Заполнить поле: Фамилия"(){
        given:
        page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
            заполнитьПолеФамилия()
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }

    def "Шаг 7: Заполнить поле: Отчество"(){
        given:
        at(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
            заполнитьПолеОтчество()
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }

    def "Шаг 8: Заполнить поле: Номер телефона"(){
        given:
        page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
            заполнитьПолеНомерТелефона()
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }


    def "Шаг 9: Заполнить поле: Дата Рождения"(){
        given:
        page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
            заполнитьПолеДатаРождения()
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }

    def "Шаг 10: Нажать на кнопку Сохранить"(){
        given:
        withFrame(page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.IFrame) {
            page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.сохранитьЗапись()
            waitFor { page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.СообщениеОбОбязательностиПоляИмя.displayed }
        }
        logger.info("ОР: Отобразилось сообщение о обязательности поля Имя")
        stepPassed=true

    }
    def "Шаг 11: Заполнить поле: Имя"(){
        given:
        at(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.with {
            заполнитьПолеИмя()
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }
    def "Шаг 12: Нажать на кнопку Сохранить"(){
        given:
            withFrame(page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.IFrame) {
                page(ContactDetailsPage).Апплет_КарточкаКонтакта.Диалог_СозданиеКонтакта.сохранитьЗапись()
            }
            assert page(ContactDetailsPage).Апплет_КарточкаКонтакта.Кнопка_ЗакрытьКарточку.displayed
        logger.info("ОР: Диалог Создания Контакта закрыт")
        stepPassed=true

    }

    def "Шаг 13: Нажать кнопку 'Закрыть' на карточке контакта"(){
        given:
        page(ContactDetailsPage).Апплет_КарточкаКонтакта.Кнопка_ЗакрытьКарточку.click()
        waitFor {at(ContactListPage).АпплетСписокКонтактов.Таблица.displayed}
        logger.info("ОР: Вернулись на страницу со списком контактов")
        stepPassed=true
    }

    def "Шаг 14: Убедиться что число записей в списке контактов стало больше зафиксированного"(){
        given:
        page(ContactDetailsPage).Апплет_КарточкаКонтакта.Кнопка_ЗакрытьКарточку.click()
        waitFor {at(ContactListPage).АпплетСписокКонтактов.Таблица.displayed}
        for(int i=0;i<10 && at(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.displayed;i++){
            waitFor { page(ContactListPage).ПанельИнструментовКонтакты.КнопкаСоздать.displayed}
            обновитьСтраницуБраузера()
            waitFor { at(ContactListPage).ПанельИнструментовКонтакты.КнопкаСоздать.displayed}
            page(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.click()
            sleep(5000)
        }
        waitFor { !page(ContactListPage).АпплетСписокКонтактов.КнопкаПоказатьКоличество.displayed}
        Integer новоеКолВоКонтактов = page(ContactListPage).АпплетСписокКонтактов.ПолеКоличествоКонтактов.text().split(' ')[page(ContactListPage).АпплетСписокКонтактов.ПолеКоличествоКонтактов.text().split(' ').size()-1] as Integer
        assert колвоКонтактов<новоеКолВоКонтактов
        logger.info("ОР: Количичество контактов увеличено")
        stepPassed=true
    }
}