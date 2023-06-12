package tests;

import org.testng.annotations.Test;
import pageobjects.*;

public class PriceCalculation extends BaseTest{

    @Test
    public void tc01_addAndGoStraightToCheckout() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[0]), true);
        co.assertEquals(co.isPriceMatchByArray(itemsNames[0], prices[0]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[0]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[0]), true);
    }

    @Test
    public void tc02_addRemoveAndGoToCheckout() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[1]), true);
        co.assertEquals(co.isPriceMatchByArray(itemsNames[1], prices[1]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[1]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[1]), true);
    }

    @Test
    public void tc03_AddRemoveAddAndRemoveFromCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, itemsNames[1].length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[1], itemsNames[0].length);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[2]), true);
        co.assertEquals(co.isPriceMatchByArray(itemsNames[2], prices[2]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[2]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[2]), true);
    }

    @Test
    public void tc04addRemoveAddAndRemoveFromCartRefreshThePageLogOutAndLogInAgain() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemsNames[0].length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, itemsNames[1].length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[1], itemsNames[0].length);
        cp.refresh();
        cp.logout();
        lp.login(username, password);
        psp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(itemsNames[2]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(itemsNames[2]), true);
        co.assertEquals(co.isPriceMatchByArray(itemsNames[2], prices[2]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[2]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[2]), true);
    }

    @Test
    public void tc05_addRemoveFromCartAndClickCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemsNames[0], true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[0], itemsNames[0].length);
        cp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(itemsNames[1]),true);
    }
}
