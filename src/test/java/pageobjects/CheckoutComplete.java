package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutComplete extends HeaderAndFooter{

    @FindBy(css = ".complete-header")
    WebElement purchaseCompleteHeader;

    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    @Description("Verifies if the 'purchase complete' header is displayed")
    public CheckoutComplete isHeaderdisplayed(String purchaseCompleteHeader) {
        assert this.purchaseCompleteHeader.getText().equals(purchaseCompleteHeader) : "Actual purchase complete title '"+this.purchaseCompleteHeader.getText()+"' does not match the expected title '"+purchaseCompleteHeader+"'";
        return this;
    }
}
