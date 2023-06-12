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
        co.assertEquals(co.isPriceMatchByArray(itemNames, prices[0]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[0]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[0]), true);
    }

    @Test
    public void tc02_addRemoveAndGoToCheckout() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemNames.length);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(updatedArrays[0]), true);
        co.assertEquals(co.isPriceMatchByArray(updatedArrays[0], prices[1]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[1]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[1]), true);
    }

    @Test
    public void tc03_AddRemoveAddAndRemoveFromCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemNames.length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, updatedArrays[0].length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[1], itemNames.length);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(updatedArrays[1]), true);
        co.assertEquals(co.isPriceMatchByArray(updatedArrays[1], prices[2]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[2]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[2]), true);
    }

    @Test
    public void tc04addRemoveAddAndRemoveFromCartRefreshThePageLogOutAndLogInAgain() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], false, itemNames.length);
        psp.assertEquals(psp.actionClickAddOrRemoveByArray(partiallyRemoveArrays[0], true, updatedArrays[0].length), true);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[1], itemNames.length);
        cp.refresh();
        cp.logout();
        lp.login(username, password);
        psp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(updatedArrays[1]), true);
        cp.actionClickCheckout();
        CheckoutInformation ci = new CheckoutInformation(driver);
        ci.fillFormAndSubmit(firstName, lastName, zipCode);
        CheckoutOverview co = new CheckoutOverview(driver);
        co.assertEquals(co.isInCartByArray(updatedArrays[1]), true);
        co.assertEquals(co.isPriceMatchByArray(updatedArrays[1], prices[2]), true);
        co.assertEquals(co.isTotalPricesMatch(prices[2]), true);
        co.assertEquals(co.isTaxCalc(taxDivision, pricesSums[2]), true);
    }

    @Test
    public void tc05_addRemoveFromCartAndClickCart() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddOrRemoveByArray(itemNames, true, 0);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.clickRemoveByArray(partiallyRemoveArrays[0], itemNames.length);
        cp.actionClickCart();
        cp.assertEquals(cp.isInCartByArray(updatedArrays[0]),true);
    }
}
