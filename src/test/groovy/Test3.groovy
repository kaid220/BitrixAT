import Bitrix.BitrixSpec
import spock.lang.Stepwise
import spock.lang.Title

@Title('Тест 3: Пробный')
@Stepwise
class Test3 extends BitrixSpec{
    def "Шаг 1: Выполнить вход в Битрикс24"() {
        given:
        logger.info("Тест для проверки")
        stepPassed=true
    }
}
