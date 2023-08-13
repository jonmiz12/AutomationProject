package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends HeaderAndFooter{
    @FindBy(css = ".cart_item")
    List<WebElement> cartItems;
    @FindBy(css = ".inventory_item_name")
    List<WebElement> cartItemsNames;
    @FindBy(css = "#checkout")
    WebElement checkoutBtn;
    @FindBy(css = "[id^=\"remove\"]")
    List<WebElement> itemsRemoveBtns;
    @FindBy(css = "#continue-shopping")
    WebElement continueShoppingBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Description("Verifies if the given item name is in the cart")
    public boolean isInCartBYName(String itemName) {
        for (WebElement cartItemName : cartItemsNames) {
            if (cartItemName.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    @Description("Verifies if the given items array is in the cart")
    public CartPage isInCartByArray(String [] itemsNames){
        for (String itemName : itemsNames) {
            assert isInCartBYName(itemName) : "Item '"+itemName+"' was not found in the cart";
        }
        return this;
    }

    @Description("Refreshes the page")
    public CartPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    @Description("Clicks the checkout button")
    public CartPage actionClickCheckout() {
        click(checkoutBtn);
        return this;
    }

    @Description("Removes items by the given items array")
    public CartPage clickRemoveByArray(String[] itemNames, int originalSize) {
        int actualCartCount;
        int expectedCartCount;
        assert itemNames.length<=cartItemsNames.size(): "Items to remove ("+itemNames.length+") are greater than items in cart ("+(cartItemsNames.size()+")");
        for (int i=0; i<itemNames.length; i++) {
            for (int j=0; j<itemsRemoveBtns.size()+1; j++) {
                if (itemNames[i].equals(cartItemsNames.get(j).getText())) {
                    click(itemsRemoveBtns.get(j));
                    actualCartCount = returnCartCount();
                    expectedCartCount = originalSize-(i+1);
                    assert actualCartCount==expectedCartCount : "Actual cartCount =" + actualCartCount + " Expected cartCount =" + expectedCartCount;
                    break;
                }
            }
        }
        return this;
    }

    @Description("Clicks the 'Continue shopping' button")
    public void actionClickContinueShopping() {
        click(continueShoppingBtn);
    }
}
