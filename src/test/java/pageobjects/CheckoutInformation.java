package pageobjects;

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

    public boolean fillFormAndSubmit(String firstName, String lastName, String zipCode){
        fillFirstNameField(firstName);
        if (!firstNameField.getAttribute("value").equals(firstName)) {
            return false;
        }
        fillLastNameField(lastName);
        if (!lastNameField.getAttribute("value").equals(lastName)) {
            return false;
        }
        fillZipCodeField(zipCode);
        if (!zipCodeField.getAttribute("value").equals(zipCode)) {
            return false;
        }
        actionClickContinue();
        return true;
    }

    public void fillFirstNameField(String firstName){
        fillText(firstNameField, firstName);
    }

    public void fillLastNameField(String lastName){
        fillText(lastNameField, lastName);
    }
    public void fillZipCodeField(String zipCode){
        fillText(zipCodeField, zipCode);
    }

    public void actionClickContinue(){
        click(continueBtn);
    }

    public String returnErrorMsg() {
        return errorMsg.getText();
    }
}
