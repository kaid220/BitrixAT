package Bitrix.pages

import Bitrix.BitrixPage
import Bitrix.modules.applets.АпплетКарточкаТовара


class CatalogProductPage  extends BitrixPage{
    String nameObj = "catalog/14/product"
    static content = {
        Апплет_КарточкаТовара{module new АпплетКарточкаТовара()}
    }
}
