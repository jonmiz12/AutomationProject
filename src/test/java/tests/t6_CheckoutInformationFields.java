package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t6_CheckoutInformationFields extends BaseTest {

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc01_fillAllFields() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, zipCode).
        isPageTitleMatch(checkoutOverviewHeader);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc02_fillOnlyFirstNameField() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, "", "").
        isErrorMsgMatch(lastNameError);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc03_fillOnlyLastNameField() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit("", lastName, "").
        isErrorMsgMatch(firstNameError);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc04_fillOnlyZipCodeField() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit("", "", zipCode).
        isErrorMsgMatch(firstNameError);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc05_fillOnlyFirstAndLastNameFields() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, lastName, "").
        isErrorMsgMatch(zipCodeError);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc06_fillOnlyFirstNameZipCodeFields() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit(firstName, "", zipCode).
        isErrorMsgMatch(lastNameError);
    }

    @Description("The test adds products, proceeds to information page and attempts to proceeds with the given firstName lastNaem and zipCode")
    @Test
    public void tc07_fillOnlyLastNameAndZipCodeFields() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]).
        actionClickCart();
        CartPage.actionClickCheckout();
        CheckoutInformation.fillFormAndSubmit("", lastName, zipCode).
        isErrorMsgMatch(firstNameError);
    }
}
