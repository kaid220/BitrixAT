package Bitrix

import geb.Page

class BitrixPage extends Page{
    static String nameObj
    static at={browser.driver.currentUrl.contains(nameObj)}
}


