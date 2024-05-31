import Bitrix.BitrixSpec
import spock.lang.Stepwise
import spock.lang.Title

@Title('Тест 4: Создание кампании')
@Stepwise
class Test4 extends BitrixSpec{
    def "Шаг 1: Выполнить вход в Битрикс24"() {
        given:
        logger.info("ОР: Осуществлен вход в Битрикс24")
        stepPassed=true
    }
}
