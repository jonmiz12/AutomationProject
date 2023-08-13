package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t2_AddProducts extends BaseTest {

    @Description("The test adds products from the main products page and verifies the cart items number has updated")
    @Test
    public void tc01_AddItemsFromProductsPage() {
        LoginPage.login(username, password);
        HeaderAndFooter.isCartCountMatch(0);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]);
    }

    @Description("The test adds products from each specific product page and verifies the cart items number has updated")
    @Test
    public void tc02_AddItemsFromProductPage(){
        LoginPage.login(username, password);
        HeaderAndFooter.isCartCountMatch(0);
        ProductsPage.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0)
        .actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]);
    }
}
