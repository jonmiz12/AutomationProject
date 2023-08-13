package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t7_PriceCalculation extends BaseTest{

    @Description("The test add products, proceeds to overview page and verifies the products prices match the calculated sum and tax")
    @Test
    public void tc01_addAndGoStraightToCheckout() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[0]).
        isPriceMatchByArray(itemsNames[0], prices[0]).
        isTotalPricesMatch(prices[0]).
        isTaxCalc(taxDivision, pricesSums[0]);
    }

    @Description("The test add products, removes some proceeds to overview page and verifies the products prices match the calculated sum and tax")
    @Test
    public void tc02_addRemoveAndGoToCheckout() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[1]).
        isPriceMatchByArray(itemsNames[1], prices[1]).
        isTotalPricesMatch(prices[1]).
        isTaxCalc(taxDivision, pricesSums[1]);
    }

    @Description("The test add products, removes some, adds them again, proceeds to cart page, removes some, proceeds to overview page and verifies the products prices match the calculated sum and tax")
    @Test
    public void tc03_AddRemoveAddAndRemoveFromCart() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, itemsNames[1].length).
        actionClickCart();
        CartPage.clickRemoveByArray(partiallyRemoveArrays[1], itemsNames[0].length).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[2]).
        isPriceMatchByArray(itemsNames[2], prices[2]).
        isTotalPricesMatch(prices[2]).
        isTaxCalc(taxDivision, pricesSums[2]);
    }

    @Description("The test add products, removes some, adds them again, proceeds to cart page, removes some, refreshes the browser, logs out, logs in, proceeds to overview page and verifies the products prices match the calculated sum and tax")
    @Test
    public void tc04addRemoveAddAndRemoveFromCartRefreshThePageLogOutAndLogInAgain() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length).
        actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, itemsNames[1].length).
        actionClickCart();
        CartPage.clickRemoveByArray(partiallyRemoveArrays[1], itemsNames[0].length).
        refresh().
        logout();
        LoginPage.login(username, password);
        ProductsPage.actionClickCart();
        CartPage.isInCartByArray(itemsNames[2]).
        actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview.isInCartByArray(itemsNames[2]).
        isPriceMatchByArray(itemsNames[2], prices[2]).
        isTotalPricesMatch(prices[2]).
        isTaxCalc(taxDivision, pricesSums[2]);
    }

    @Description("The test add products, proceeds to cart page, removes some, clicks cart, verifies the items are still in the cart")
    @Test
    public void tc05_addRemoveFromCartAndClickCart() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddOrRemoveByArray(itemsNames[0], true, 0).
        actionClickCart();
        CartPage.clickRemoveByArray(partiallyRemoveArrays[0], itemsNames[0].length).
        actionClickCart();
        CartPage.isInCartByArray(itemsNames[1]);
    }
}
