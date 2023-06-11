package pageobjects;

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

    public boolean isInCartBYName(String itemName) {
        for (WebElement cartItemName : cartItemsNames) {
            if (cartItemName.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInCartByArray(String [] itemsNames){
        for (String itemName : itemsNames) {
            if (!isInCartBYName(itemName)) {
                return false;
            }
        }
        return true;
    }

    public void actionClickCheckout() {
        click(checkoutBtn);
    }

    public boolean clickRemoveByArray(String[] itemNames, int originalSize) {
        for (int i=0; i<itemNames.length; i++) {
            for (int j=0; j<itemsRemoveBtns.size()+1; j++) {
                if (i != cartItemsNames.size()) {
                    if (itemNames[i].equals(cartItemsNames.get(j).getText())) {
                        click(itemsRemoveBtns.get(j));
                        if (returnCartCount() != originalSize-(i+1)) {
                            return false;
                        }
                        break;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void actionClickContinueShopping() {
        click(continueShoppingBtn);
    }
}
