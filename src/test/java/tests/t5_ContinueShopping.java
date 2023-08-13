package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t5_ContinueShopping extends  BaseTest{

    @Description("The test adds products, proceeds to cart page, clicks 'continue shopping and verifies all items are still in the cart")
    @Test
    public void tc01_addProductsGoToCheckoutAndContinueShopping() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]).
        actionClickContinueShopping();
        ProductsPage.isArrayRemovedOrAdded(itemsNames[0], true);
    }

    @Description("The test add products, proceeds to overview page, clicks cancel and verifies all items are still in the cart ")
    @Test
    public void tc01_addProductsGoToPaymentAndCancel() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.actionClickCancel();
        ProductsPage.isArrayRemovedOrAdded(itemsNames[0], true);
    }
}
