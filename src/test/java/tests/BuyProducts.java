package tests;

import org.testng.annotations.Test;
import pageobjects.*;

public class BuyProducts extends BaseTest{

    @Test
    public void tc01_addProductsFromProductsPageAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemNames, true, 0), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemNames), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemNames), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc02_addProductsFromProductPageAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemNames, true, 0), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemNames), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemNames), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc03_AddProductsFromProductsPageRemoveSomeAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemNames, true, 0), true);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, false, itemNames.length), true);
        firstUpdatedArray = removeArrayFromArray(itemNames, firstPartiallyRemove);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(firstUpdatedArray), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(firstUpdatedArray), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc04_addProductsFromProductPageRemoveSomeAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemNames, true, 0), true);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(firstPartiallyRemove, false, itemNames.length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(firstUpdatedArray), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(firstUpdatedArray), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }
}
