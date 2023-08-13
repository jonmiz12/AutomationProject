package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformation extends HeaderAndFooter {

    @FindBy(css = "#first-name")
    WebElement firstNameField;
    @FindBy(css = "#last-name")
    WebElement lastNameField;
    @FindBy(css = "#postal-code")
    WebElement zipCodeField;
    @FindBy(css = "#continue")
    WebElement continueBtn;
    @FindBy(css = ".error-message-container.error")
    WebElement errorMsg;

    public CheckoutInformation(WebDriver driver) {
        super(driver);
    }

    @Description("Fills the firstName, lastName and zipCode fields, verifies they have been filled and clicks continue")
    public CheckoutInformation fillFormAndSubmit(String firstName, String lastName, String zipCode){
        fillFirstNameField(firstName);
        assert firstNameField.getAttribute("value").equals(firstName) : "Actual first name filled does not match expected first name '"+firstName+"'";
        fillLastNameField(lastName);
        assert lastNameField.getAttribute("value").equals(lastName) : "Actual last name filled does not match expected last name '"+lastName+"'";
        fillZipCodeField(zipCode);
        assert zipCodeField.getAttribute("value").equals(zipCode) : "Actual zip code filled does not match expected zip code '"+zipCode+"'";
        actionClickContinue();
        return this;
    }

    @Description("Fills the firstName field")
    public void fillFirstNameField(String firstName){
        fillText(firstNameField, firstName);
    }

    @Description("Fills the lastName field")
    public void fillLastNameField(String lastName){
        fillText(lastNameField, lastName);
    }

    @Description("Fills the zipCode field")
    public void fillZipCodeField(String zipCode){
        fillText(zipCodeField, zipCode);
    }

    @Description("CLicks the continue button")
    public void actionClickContinue(){
        click(continueBtn);
    }

    @Description("Returns the form's error message")
    public String returnErrorMsg() {
        return errorMsg.getText();
    }

    @Description("Verifies that the form's error message match the given error message")
    public CheckoutInformation isErrorMsgMatch (String expectedErrorMsg) {
        String actualErrorMsg = returnErrorMsg();
        assert actualErrorMsg.equals(expectedErrorMsg) : "Actual error message '"+actualErrorMsg+"' does not match epected error message '"+expectedErrorMsg+"'";
        return this;
    }
}
