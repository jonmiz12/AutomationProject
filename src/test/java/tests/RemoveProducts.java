package tests;

import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;

public class RemoveProducts extends BaseTest{

    @Test
    public void tc01_removeItemsFromProductsPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemNames, false, itemNames.length), true);
    }

    @Test
    public void tc01_removeItemsFromProductPage(){
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemNames, false, itemNames.length),true);
    }
}
