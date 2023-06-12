package tests;

import org.testng.annotations.Test;
import pageobjects.*;

public class BuyProducts extends BaseTest{

    @Test
    public void tc01_addProductsFromProductsPageAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[0]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[0]), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc02_addProductsFromProductPageAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[0]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[0]), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc03_AddProductsFromProductsPageRemoveSomeAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0), true);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length), true);
        itemsNames[0] = removeArrayFromArray(itemsNames[0], partiallyRemoveArrays[0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[0]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[0]), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }

    @Test
    public void tc04_addProductsFromProductPageRemoveSomeAndBuy() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0), true);
        psp.assertEquals(psp.addRemoveToCartByArrayBackToProducts(partiallyRemoveArrays[0], false, itemsNames[0].length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.assertEquals(cp.isInCartByArray(itemsNames[1]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.assertEquals(ci.fillFormAndSubmit(firstName, lastName, zipCode), true);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[1]), true);
        co.actionClickFinish();
        CheckoutComplete cc = new CheckoutComplete(driver);
        cc.assertEquals(cc.isHeaderdisplayed(purchaseCompleteHeader), true);
    }
}
