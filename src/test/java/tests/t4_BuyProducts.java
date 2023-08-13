package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t4_BuyProducts extends BaseTest{

    @Description("The test adds products from the main products page and proceeds with the positive auction flow")
    @Test
    public void tc01_addProductsFromProductsPageAndBuy() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[0]).
        actionClickFinish();
        CheckoutComplete.isHeaderdisplayed(purchaseCompleteHeader);
    }

    @Description("The test adds products from each specific product page and proceeds with the positive auction flow")
    @Test
    public void tc02_addProductsFromProductPageAndBuy() {
        LoginPage.login(username, password);
        ProductsPage.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[0]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[0]).
        actionClickFinish();
        CheckoutComplete.isHeaderdisplayed(purchaseCompleteHeader);
    }

    @Description("The test adds products from the main products page, removes some and proceeds with the positive auction flow")
    @Test
    public void tc03_AddProductsFromProductsPageRemoveSomeAndBuy() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[1]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[1]).
        actionClickFinish();
        CheckoutComplete.isHeaderdisplayed(purchaseCompleteHeader);
    }

    @Description("The test adds products from each specific product page, removes some and proceeds with the positive auction flow")
    @Test
    public void tc04_addProductsFromProductPageRemoveSomeAndBuy() {
        LoginPage.login(username, password);
        ProductsPage.addRemoveToCartByArrayBackToProducts(itemsNames[0], true, 0).
        addRemoveToCartByArrayBackToProducts(partiallyRemoveArrays[0], false, itemsNames[0].length).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[1]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[1]).
        actionClickFinish();
        CheckoutComplete.isHeaderdisplayed(purchaseCompleteHeader);
    }
}
