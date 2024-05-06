package uiTests


import Bitrix.BitrixSpec
import Bitrix.Config
import Bitrix.pages.ContactDetailsPage
import Bitrix.pages.ContactListPage
import spock.lang.Shared

class test extends BitrixSpec{
    def "��������� ���� � �������24"() {
        given:
        ���������������������(Config.getProperty("userLogin1"), Config.getProperty("userPassword1"))
        logger.info("��: ����������� ���� � �������24")
    }
    def "������� �� ���� ������� - ��������"(){
        given:
        �������������('�������->��������')
        at ContactListPage
        logger.info("��: ��������� �������� '��������'")
    }
    @Shared Integer ��������������
    def "������������� ������� ���������� ���������"(){
        given:
        waitFor {ContactListPage.������_���������������.������������������������.displayed}
        �������������� = ContactListPage.������_���������������.�������������������()
        logger.info("���������� ��������� ='$��������������'")
        logger.info("��: �������� ��������������")
    }
    def "������ �� ������ '�������'"(){
        given:
        ContactListPage.��������������������������.�������������.click()
        assert at(ContactDetailsPage).������_����������������.displayed
        logger.info("????????? ????? ???????? ????????")
    }

  /*  def "????????? ????: ?????????, ???????, ???, ????????, ???? ????????, ?????????, ???????, ?????"(){
        given:

    }*/
}