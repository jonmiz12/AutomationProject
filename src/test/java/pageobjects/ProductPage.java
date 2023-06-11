package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends HeaderAndFooter {

    @FindBy(css = ".inventory_details_name.large_size")
    WebElement itemName;
    @FindBy(css = ".btn_inventory")
    WebElement addOrRemoveFromCart;
    @FindBy(css = "#back-to-products")
    WebElement backToProductsBtn;
    @FindBy(css = "[id^=\"add\"]")
    WebElement addToCartBtn;
    @FindBy(css = "[id^=\"remove\"]")
    WebElement RemoveFromCartBtn;



    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String returnItemName() {
        return itemName.getText();
    }

    public void actionClickAddRemoveBtn() {
        click(addOrRemoveFromCart);
    }

    public void actionClickBackToProducts() {
        click(backToProductsBtn);
    }

    public boolean addOrRemoveItemByNameAndBackToProducts(String itemName, int expectedItemCount, boolean addOrRemove) {
        if (isNameMatch(itemName)) {
            if (addOrRemove) {
                click(addToCartBtn);
            } else {
                click(RemoveFromCartBtn);
            }
            if (returnCartCount() != expectedItemCount) {
                return false;
            }
        } else {
            return false;
        }
        click(backToProductsBtn);
        return true;
    }

    public boolean isNameMatch(String itemName) {
        if (returnItemName().equals(itemName)) {
            return true;
        } else {
            return false;
        }
    }
}
