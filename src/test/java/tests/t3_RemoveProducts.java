package tests;

import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;

public class t3_RemoveProducts extends BaseTest{

    @Test
    public void tc01_removeItemsFromProductsPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemsNames[0], false, itemsNames[0].length), true);
    }

    @Test
    public void tc01_removeItemsFromProductPage(){
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemsNames[0], false, itemsNames[0].length),true);
    }
}
