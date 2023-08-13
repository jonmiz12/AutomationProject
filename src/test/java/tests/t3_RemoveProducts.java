package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t3_RemoveProducts extends BaseTest{

    @Description("The test adds products from the main products page, removes them and verifies the cart items number has updated")
    @Test
    public void tc01_removeItemsFromProductsPage() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickAddOrRemoveByArray(itemsNames[0], false, itemsNames[0].length);
    }

    @Description("The test adds products from each specific product page, removes them and verifies the cart items number has updated")
    @Test
    public void tc01_removeItemsFromProductPage(){
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        addRemoveToCartByArrayBackToProducts(itemsNames[0], false, itemsNames[0].length);
    }
}
