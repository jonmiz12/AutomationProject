package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutComplete extends HeaderAndFooter{

    @FindBy(css = ".complete-header")
    WebElement purchaseCompleteHeader;

    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    public boolean isHeaderdisplayed(String purchaseCompleteHeader) {
        System.out.println(this.purchaseCompleteHeader.getText());
        if (!this.purchaseCompleteHeader.getText().equals(purchaseCompleteHeader)) {
            return false;
        }
        return true;
    }
}
