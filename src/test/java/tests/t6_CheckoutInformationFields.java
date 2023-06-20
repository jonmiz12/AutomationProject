package tests;

import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutInformation;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;

public class t6_CheckoutInformationFields extends BaseTest {

    @Test
    public void tc01_fillAllFields() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit(firstName, lastName, zipCode);
        co.assertEquals(co.getPageTitle(), checkoutOverviewHeader);
    }

    @Test
    public void tc02_fillOnlyFirstNameField() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit(firstName, "", "");
        co.assertEquals(co.returnErrorMsg(), lastNameError);
    }

    @Test
    public void tc03_fillOnlyLastNameField() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit("", lastName, "");
        co.assertEquals(co.returnErrorMsg(), firstNameError);
    }

    @Test
    public void tc04_fillOnlyZipCodeField() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit("", "", zipCode);
        co.assertEquals(co.returnErrorMsg(), firstNameError);
    }

    @Test
    public void tc05_fillOnlyFirstAndLastNameFields() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit(firstName, lastName, "");
        co.assertEquals(co.returnErrorMsg(), zipCodeError);
    }

    @Test
    public void tc06_fillOnlyFirstNameZipCodeFields() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit(firstName, "", zipCode);
        co.assertEquals(co.returnErrorMsg(), lastNameError);
    }

    @Test
    public void tc07_fillOnlyLastNameAndZipCodeFields() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickAddToCartByName(itemsNames[0][0]);
        psp.actionClickCart();
        CartPage cp = new CartPage(driver);
        cp.actionClickCheckout();
        CheckoutInformation co = new CheckoutInformation(driver);
        co.fillFormAndSubmit("", lastName, zipCode);
        co.assertEquals(co.returnErrorMsg(), firstNameError);
    }
}
