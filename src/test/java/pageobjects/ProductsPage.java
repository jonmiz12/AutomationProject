package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends HeaderAndFooter{

    final static String remove = "[id^=\"remove\"]";
    final static String add = "[id^=\"add\"]";
    @FindBy(css = ".inventory_item")
    List<WebElement> items;
    @FindBy(css = ".inventory_item_name")
    List<WebElement> itemsNames;
    @FindBy(css = ".inventory_item_img img")
    List<WebElement> itemsImages;
    @FindBy(css = ".btn_inventory")
    List<WebElement> itemsAddToCartBtn;
    @FindBy(css = ".inventory_item_price")
    List<WebElement> itemsPrices;
    @FindBy(css = remove)
    List<WebElement> itemsRemoveBtns;
    @FindBy(css = add)
    List<WebElement> itemsAddBtns;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void actionClickAddToCartByName(String itemName){
        for (int i=0; i<itemsNames.size(); i++) {
            if (itemsNames.get(i).getText().equals(itemName) && itemsAddToCartBtn.get(i).getAttribute("id").startsWith("add")) {
                click(itemsAddToCartBtn.get(i));
                break;
            }
        }
    }

    public void actionClickItemNameByName(String itemName) {
        for (WebElement el : itemsNames) {
            if (el.getText().equals(itemName)){
                click(el);
            }
        }
    }

    public int returnRemoveBtnNum(){
        return itemsRemoveBtns.size();
    }

    public boolean matchItemsCountAndRemove(){
        return returnCartCount()==returnRemoveBtnNum();
    }

    public void actionClickItemName(String itemName) {
        for (WebElement itemsName : itemsNames) {
            if (itemsName.getText().equals(itemName)) {
                click(itemsName);
                break;
            }
        }
    }

    public boolean addRemoveToCartByNameAndBackToProducts(String itemName, int expectedItemCount, boolean addOrRemove) {
        for (int i=0; i<itemsNames.size()+1; i++) {
            if (itemsNames.get(i).getText().equals(itemName)) {
                click(itemsNames.get(i));
            } else {
                if (i==itemsNames.size()+1) {
                    return false;
                }
            }
        }
        ProductPage pp = new ProductPage(driver);
        return pp.addOrRemoveItemByNameAndBackToProducts(itemName, expectedItemCount, addOrRemove);
    }

    public boolean actionClickAddOrRemoveByArray(String[] itemNames, boolean addOrRemove, int originalSize) {
        for (int i=0; i<itemNames.length; i++) {
            if (addOrRemove) {
                actionClickAddToCartByName(itemNames[i]);
                if (returnCartCount() != originalSize + (i+1)) {
                    return false;
                }
            } else {
                actionClickRemoveFromCartByName(itemNames[i]);
                if (returnCartCount() != originalSize-(i+1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean addRemoveToCartByArrayBackToProducts(String [] itemNames, boolean addOrRemove, int originalSize) {
        boolean match = true;
        int expectedItemCount;
        for (int i=0; i<itemNames.length; i++) {
            if (addOrRemove) {
                expectedItemCount = i+1;
            }else {
                expectedItemCount = originalSize - (i+1);
            }
            if (match) {
                match = addRemoveToCartByNameAndBackToProducts(itemNames[i], expectedItemCount, addOrRemove);
            } else {
                break;
            }
        }
        return match;
    }

    public void actionClickRemoveFromCartByName(String itemName) {
        for (int i=0; i<itemsNames.size(); i++) {
            if (itemsNames.get(i).getText().equals(itemName) && itemsAddToCartBtn.get(i).getAttribute("id").startsWith("remove")) {
                click(itemsAddToCartBtn.get(i));
                break;
            }
        }
    }

    public boolean isArrayRemovedOrAdded(String[] itemsNames, boolean addRemove) {
        for (int i=0; i<this.itemsNames.size(); i++) {
            for (String itemsName : itemsNames) {
                if (itemsName.equals(this.itemsNames.get(i).getText())) {
                    if (addRemove) {
                        if (!items.get(i).findElement(By.cssSelector(remove)).isDisplayed()) {
                            return false;
                        }
                    } else {
                        if (!items.get(i).findElement(By.cssSelector(add)).isDisplayed()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
