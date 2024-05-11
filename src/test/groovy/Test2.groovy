

import Bitrix.BitrixSpec

class Test2 extends BitrixSpec{
        def 'Шаг 1: loginCase'(){
            given:
            пользователь = получитьСвободногоПользователя()
            выполнитьВходВБитрикс(пользователь)
            перейтиПоМеню('Клиенты->444')
            stepPassed=true
        }
    }

