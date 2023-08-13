package pageobjects;

import io.qameta.allure.Description;
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
    @FindBy(css = "[id^='add']")
    WebElement addToCartBtn;
    @FindBy(css = "[id^='remove']")
    WebElement RemoveFromCartBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Description("Returns the item's name")
    public String returnItemName() {
        return itemName.getText();
    }

    @Description("Either removes or adds the given item and click 'Back to products'")
    public boolean addOrRemoveItemByNameAndBackToProducts(String itemName, int expectedItemCount, boolean addOrRemove) {
        int actualCartCount;
        if (isNameMatch(itemName)) {
            if (addOrRemove) {
                click(addToCartBtn);
            } else {
                click(RemoveFromCartBtn);
            }
            actualCartCount = returnCartCount();
            assert actualCartCount == expectedItemCount : "Actual cart count ("+actualCartCount+") does not match expected cart count ("+expectedItemCount+")";
        }
        click(backToProductsBtn);
        return true;
    }

    @Description("Verifies if the given name matches the actual product's name")
    public boolean isNameMatch(String itemName) {
        String actualItemName = returnItemName();
        assert actualItemName.equals(itemName) : "Actual item name ("+actualItemName+") does not match expected item name ("+itemName+")";
        return true;
    }
}
