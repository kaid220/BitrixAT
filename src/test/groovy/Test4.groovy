import Bitrix.BitrixSpec
import Bitrix.pages.CatalogPage
import Bitrix.pages.CatalogProductPage
import Bitrix.pages.CompanyDetailsPage
import Bitrix.pages.ContactDetailsPage
import Bitrix.pages.ContactListPage
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Title

import java.time.LocalDateTime

@Title('Тест 4: Создание кампании')
@Stepwise
class Test4 extends BitrixSpec {
    def "Шаг 1: Выполнить вход в Битрикс24"() {
        given:
        пользователь = получитьСвободногоПользователя()
        выполнитьВходВБитрикс(пользователь)
        logger.info("ОР: Осуществлен вход в Битрикс24")
        stepPassed = true
    }

    def "Шаг 2: Перейти по меню Товары и Склады - Каталог Товаров"() {
        given:
        перейтиПоМеню('Товары и Склады->Каталог товаров')
        at CatalogPage
        logger.info("ОР: Открылась страница 'Каталог Товаров'")
        stepPassed = true
    }
    @Shared
    Integer колвоТоваров

    def "Шаг 3: Зафиксировать текущее количество товаров"() {
        given:
        waitFor { page(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.displayed }
        колвоТоваров = page(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.text.split(' ')[page(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.text.split(' ').size() - 1] as Integer
        assert колвоТоваров != null
        logger.info("Количество товаров ='$колвоТоваров'")
        logger.info("ОР: Значение зафиксированно")
        stepPassed = true
    }
    def "Шаг 4: Нажать на кнопку 'Создать'"(){
        given:
        at(CatalogPage).ПанельИнструментовТовары.КнопкаСоздать.click()
        at CatalogProductPage
        assert page(CatalogProductPage).Апплет_КарточкаТовара.Диалог_СозданиеТовара.displayed
        logger.info("ОР: Открылась форма создания товара")
        stepPassed=true

    }
    def "Шаг 5: Заполнить поле: Наименование"(){
        given:
        at(CatalogProductPage).Апплет_КарточкаТовара.Диалог_СозданиеТовара.with {
            withFrame(IFrame) {
                at(CatalogProductPage).Апплет_КарточкаТовара.Диалог_СозданиеТовара.заполнитьПолеНаименование('Test4'+LocalDateTime.now().toString())
            }
        }
        logger.info("ОР: Поле заполнено")
        stepPassed=true
    }
    def "Шаг 6: Нажать на кнопку Сохранить"(){
        given:
        withFrame(page(CatalogProductPage).Апплет_КарточкаТовара.Диалог_СозданиеТовара.IFrame) {
            page(CatalogProductPage).Апплет_КарточкаТовара.Диалог_СозданиеТовара.сохранитьЗапись()
        }
        assert page(CatalogProductPage).Апплет_КарточкаТовара.Кнопка_ЗакрытьКарточку.displayed
        sleep(3000)
        logger.info("ОР: Диалог Создания Товара закрыт")
        stepPassed=true

    }
    def "Шаг 7: Нажать кнопку 'Закрыть' на карточке товара"(){
        given:
        for(int i=0;i<5&&driver.currentUrl.contains('catalog/14/product');i++){
            page(CatalogProductPage).Апплет_КарточкаТовара.Кнопка_ЗакрытьКарточку.click()
            sleep(3000)
        }
        waitFor {at(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.displayed}
        logger.info("ОР: Вернулись на страницу со списком товаров")
        stepPassed=true
    }
    def "Шаг 8: Убедиться что число записей в списке контактов стало больше зафиксированного"(){
        given:
        Integer новоеКолВоТоваров = page(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.text.split(' ')[page(CatalogPage).Апплет_СписокТоваров.ПолеКоличествоТоваров.text.split(' ').size()-1] as Integer
        assert колвоТоваров<новоеКолВоТоваров
        logger.info("ОР: Количичество товаров увеличено")
        stepPassed=true
    }
}
