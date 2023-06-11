package tests;

import org.testng.annotations.Test;
import pageobjects.*;

public class PriceCalculation extends BaseTest{

    @Test
    public void tc01_addAndGoStraightToCheckout() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemNames), true);
        co.assertEquals(co.isPriceMatchByArray(itemNames, prices), true);
        co.assertEquals(co.isTotalPricesMatch(prices), true);
        co.assertEquals(co.isTaxCalc(taxDivision, sumPrices), true);
    }

    @Test
    public void tc02_addRemoveAndGoToCheckout() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, false, itemNames.length);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(firstUpdatedArray), true);
        co.assertEquals(co.isPriceMatchByArray(firstUpdatedArray, firstUpdatedPrices), true);
        co.assertEquals(co.isTotalPricesMatch(firstUpdatedPrices), true);
        co.assertEquals(co.isTaxCalc(taxDivision, firstUpdatedSumPrices), true);
    }

    @Test
    public void tc03_AddRemoveAddAndRemoveFromCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, false, itemNames.length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, true, firstUpdatedArray.length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(secondPartiallyRemove, itemNames.length);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(secondUpdatedArray), true);
        co.assertEquals(co.isPriceMatchByArray(secondUpdatedArray, secondUpdatedPrices), true);
        co.assertEquals(co.isTotalPricesMatch(secondUpdatedPrices), true);
        co.assertEquals(co.isTaxCalc(taxDivision, secondUpdatedSumPrices), true);
    }

    @Test
    public void tc04addRemoveAddAndRemoveFromCartRefreshThePageLogOutAndLogInAgain() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, false, itemNames.length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(firstPartiallyRemove, true, firstUpdatedArray.length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(secondPartiallyRemove, itemNames.length);
        cp.refresh();
        cp.logout();
        lp.login(username, password);
        psp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(secondUpdatedArray), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(secondUpdatedArray), true);
        co.assertEquals(co.isPriceMatchByArray(secondUpdatedArray, secondUpdatedPrices), true);
        co.assertEquals(co.isTotalPricesMatch(secondUpdatedPrices), true);
        co.assertEquals(co.isTaxCalc(taxDivision, secondUpdatedSumPrices), true);
    }

    @Test
    public void tc05_addRemoveFromCartAndClickCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(firstPartiallyRemove, itemNames.length);
        cp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(firstUpdatedArray),true);
    }
}
