package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends HeaderAndFooter{

    final static String remove = "[id^='remove']";
    final static String add = "[id^='add']";
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


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Description("Matches the item's name and click it's 'add' button")
    public ProductsPage actionClickAddByName(String itemName){
        for (int i=0; i<itemsNames.size(); i++) {
            if (itemsNames.get(i).getText().equals(itemName) && itemsAddToCartBtn.get(i).getAttribute("id").startsWith("add")) {
                click(itemsAddToCartBtn.get(i));
                break;
            }
        }
        return this;
    }

    @Description("Either adds or removes an item from its specific product page and returns to products page")
    public boolean addRemoveToCartByNameAndBackToProducts(String itemName, int expectedItemCount, boolean addOrRemove) {
        for (int i=0; i<itemsNames.size()+1; i++) {
            if (itemsNames.get(i).getText().equals(itemName)) {
                click(itemsNames.get(i));
                ProductPage pp = new ProductPage(driver);
                return pp.addOrRemoveItemByNameAndBackToProducts(itemName, expectedItemCount, addOrRemove);
            }
        }
        assert true : "The item '"+itemName+"' was not found";
        return false;
    }

    @Description("Either adds or removes an array of items")
    public ProductsPage actionClickAddOrRemoveByArray(String[] itemNames, boolean addOrRemove, int originalSize) {
        int actualCartCount;
        int expectedCartCount;
        for (int i=0; i<itemNames.length; i++) {
            if (addOrRemove) {
                actionClickAddByName(itemNames[i]);
                actualCartCount = returnCartCount();
                expectedCartCount = originalSize + (i + 1);
            } else {
                actionClickRemoveByName(itemNames[i]);
                actualCartCount = returnCartCount();
                expectedCartCount = originalSize - (i + 1);
            }
            assert actualCartCount == expectedCartCount : "Actual cartCount =" + actualCartCount + " Expected cartCount =" + expectedCartCount;
        }
        return this;
    }

    @Description("Either adds or removes an array of items from the specific product page and returns to products page")
    public ProductsPage addRemoveToCartByArrayBackToProducts(String [] itemNames, boolean addOrRemove, int originalSize) {
        boolean match = true;
        int expectedItemCount;
        for (int i=0; i<itemNames.length; i++) {
            if (addOrRemove) {
                expectedItemCount = i+1;
            }else {
                expectedItemCount = originalSize - (i+1);
            }            addRemoveToCartByNameAndBackToProducts(itemNames[i], expectedItemCount, addOrRemove);

        }
        return this;
    }

    @Description("Matches the item's name and click it's 'remove' button")
    public void actionClickRemoveByName(String itemName) {
        for (int i=0; i<itemsNames.size(); i++) {
            if (itemsNames.get(i).getText().equals(itemName) && itemsAddToCartBtn.get(i).getAttribute("id").startsWith("remove")) {
                click(itemsAddToCartBtn.get(i));
                break;
            }
        }
    }

    @Description("Verifies if the specified items array is indeed removed or added")
    public ProductsPage isArrayRemovedOrAdded(String[] itemsNames, boolean addRemove) {
        for (int i=0; i<this.itemsNames.size(); i++) {
            for (String itemsName : itemsNames) {
                if (itemsName.equals(this.itemsNames.get(i).getText())) {
                    if (addRemove) {
                        assert items.get(i).findElement(By.cssSelector(remove)).isDisplayed() : "'remove' button was not found for item '"+this.itemsNames.get(i).getText()+"'";
                    } else {
                        assert items.get(i).findElement(By.cssSelector(add)).isDisplayed() : "'add' button was not found for item '"+this.itemsNames.get(i).getText()+"'";
                    }
                }
            }
        }
        return this;
    }
}
