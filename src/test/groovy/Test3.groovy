import Bitrix.BitrixSpec
import Bitrix.pages.CompanyDetailsPage
import Bitrix.pages.CompanyListPage
import Bitrix.pages.ContactDetailsPage
import Bitrix.pages.ContactListPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

@Title('Тест 3: Создание компании')
@Stepwise
class Test3 extends BitrixSpec{
    def "Шаг 1: Выполнить вход в Битрикс24"() {
        given:
        пользователь = получитьСвободногоПользователя()
        выполнитьВходВБитрикс(пользователь)
        logger.info("ОР: Осуществлен вход в Битрикс24")
        stepPassed=true
    }
    def "Шаг 2: Перейти по меню Клиенты - Компании"(){
        given:
        перейтиПоМеню('Клиенты->Компании')
        at CompanyListPage
        logger.info("ОР: Открылась страница 'Компании'")
        stepPassed=true

    }
    @Shared Integer колвоКомпаний
    def "Шаг 3: Зафиксировать текущее количество компаний"(){
        given:
        waitFor {page(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed}
        for(int i=0;i<10 && at(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed;i++){
            waitFor { page(CompanyListPage).ПанельИнструментовКомпании.КнопкаСоздать.displayed}
            обновитьСтраницуБраузера()
            waitFor { at(CompanyListPage).ПанельИнструментовКомпании.КнопкаСоздать.displayed}
            page(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.click()
            sleep(5000)
        }
        waitFor { !page(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed}
        колвоКомпаний = page(CompanyListPage).АпплетСписокКомпаний.ПолеКоличествоКомпаний.text().split(' ')[page(CompanyListPage).АпплетСписокКомпаний.ПолеКоличествоКомпаний.text().split(' ').size()-1] as Integer
        assert колвоКомпаний!=null
        logger.info("Количество компаний ='$колвоКомпаний'")
        logger.info("ОР: Значение зафиксированно")
        stepPassed=true
    }
    def "Шаг 4: Нажать на кнопку 'Создать'"(){
        given:
        at(CompanyListPage).ПанельИнструментовКомпании.КнопкаСоздать.click()
        at CompanyDetailsPage
        assert page(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.displayed
        logger.info("ОР: Открылась форма создания компании")
        stepPassed=true
    }
    def "Шаг 5: Заполнить поле: Название"(){
        given:
        at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.with {
            withFrame(IFrame) {
                at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.заполнитьПолеНазвание('Тест3')
            }
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true

    }
    def "Шаг 6: Заполнить поле: Тип компании"(){
        given:
        at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.with {
            withFrame(IFrame) {
                at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.заполнитьПолеТипКомпании('Клиент')
            }
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true
    }

    def "Шаг 7: Заполнить поле: Сфера деятельности"(){
        given:
        at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.with {
            withFrame(IFrame) {
                at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.заполнитьПолеСфераДеятельности('Производство')
            }
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true
    }
    def "Шаг 8: Заполнить поле: Номер телефона"(){
        given:
        at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.with {
            withFrame(IFrame) {
                at(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.заполнитьПолеНомерТелефона()
            }
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true
    }
    def "Шаг 10: Нажать на кнопку Сохранить"(){
        given:
        withFrame(page(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.IFrame) {
            page(CompanyDetailsPage).Апплет_КарточкаКомпании.Диалог_СозданиеКомпании.сохранитьЗапись()
        }
        assert page(CompanyDetailsPage).Апплет_КарточкаКомпании.Кнопка_ЗакрытьКарточку.displayed
        sleep(3000)
        logger.info("ОР: Диалог Создания Компании закрыт")
        stepPassed=true

    }
    def "Шаг 11: Нажать кнопку 'Закрыть' на карточке компании"(){
        given:
        for(int i=0;i<5&&driver.currentUrl.contains('company/details');i++){
            page(CompanyDetailsPage).Апплет_КарточкаКомпании.Кнопка_ЗакрытьКарточку.click()
            sleep(3000)
        }
        waitFor {at(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed}
        logger.info("ОР: Вернулись на страницу со списком компаний")
        stepPassed=true
    }
    def "Шаг 12: Убедиться что число записей в списке компаний стало больше зафиксированного"(){
        given:
        for(int i=0;i<10 && at(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed;i++){
            waitFor { page(CompanyListPage).ПанельИнструментовКомпании.КнопкаСоздать.displayed}
            обновитьСтраницуБраузера()
            waitFor { at(CompanyListPage).ПанельИнструментовКомпании.КнопкаСоздать.displayed}
            page(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.click()
            sleep(5000)
        }
        waitFor { !page(CompanyListPage).АпплетСписокКомпаний.КнопкаПоказатьКоличество.displayed}
        Integer новоеКолВоКомпаний = page(CompanyListPage).АпплетСписокКомпаний.ПолеКоличествоКомпаний.text().split(' ')[page(CompanyListPage).АпплетСписокКомпаний.ПолеКоличествоКомпаний.text().split(' ').size()-1] as Integer
        assert колвоКомпаний<новоеКолВоКомпаний
        logger.info("ОР: Количичество компаний увеличено")
        stepPassed=true
    }
}
