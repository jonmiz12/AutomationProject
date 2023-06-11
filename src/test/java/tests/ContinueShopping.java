package tests;

import org.testng.annotations.Test;
import pageobjects.*;

public class ContinueShopping extends  BaseTest{

    @Test
    public void tc01_addProductsGoToCheckoutAndContinueShopping() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemNames), true);
        cp.actionClickContinueShopping();
        psp.assertEquals(psp.isArrayRemovedOrAdded(itemNames, true), true);
    }

    @Test
    public void tc01_addProductsGoToPaymentAndCancel() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemNames), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.actionClickCancel();
        psp.assertEquals(psp.isArrayRemovedOrAdded(itemNames, true), true);
    }
}
