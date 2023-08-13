package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CheckoutOverview extends HeaderAndFooter {

    @FindBy(css = ".inventory_item_name")
    List<WebElement> cartItemsNames;
    @FindBy(css = ".inventory_item_price")
    List<WebElement> itemPrices;
    @FindBy(css = "#finish")
    WebElement finishBtn;
    @FindBy(css = ".summary_tax_label")
    WebElement taxPrice;
    @FindBy(css = "#cancel")
    WebElement cancelBtn;

    public CheckoutOverview(WebDriver driver) {
        super(driver);
    }

    @Description("Verifies if the given item name is displays in the cart list")
    public boolean isInCartBYName(String itemName) {
        for (WebElement cartItemName : cartItemsNames) {
            if (cartItemName.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    @Description("Verifies if the array of items is displayed in the cart list")
    public CheckoutOverview isInCartByArray(String [] itemsNames){
        for (String itemName : itemsNames) {
            assert isInCartBYName(itemName) : "The item '"+itemName+"' wasn't found in the checkoutOverview page";
        }
        return this;
    }

    @Description("Clicks the finish button")
    public CheckoutOverview actionClickFinish() {
        click(finishBtn);
        return this;
    }

    @Description("Verifies that the given price match the given item name on the items list")
    public boolean isPriceMatchByName(String itemName, double price) {
        boolean match = false;
        for (int i=0; i<cartItemsNames.size(); i++) {
            if ((cartItemsNames.get(i).getText().equals(itemName))) {
                match=false;
                if (removeFirst$AndConvertToDouble(itemPrices.get(i).getText())==price) {
                    match=true;
                }
            }
        }
        return match;
    }

    @Description("Verifies that each item's price matches it's corresponding given name and price")
    public CheckoutOverview isPriceMatchByArray(String [] itemNames, Double [] prices) {
        assert itemNames.length==prices.length : "Items list size ("+itemNames.length+") does not match prices list size ("+prices.length+")";
        for (int i=0; i<cartItemsNames.size(); i++) {
            assert isPriceMatchByName(itemNames[i], prices[i]) : "The item '"+itemNames[i]+"' does not match price '"+prices[i]+"'";
        }
        return this;
    }

    @Description("If the first character of the text is '$', it is removed adn the text is returned, if not, it splits the text in two where there is a space and return the second part")
    public Double removeFirst$AndConvertToDouble(String phrase) {
        if (phrase.startsWith("$")) {
            return Double.parseDouble(phrase.substring(1));
        } else {
            return Double.parseDouble(phrase.split(" ")[1].substring(1));
        }
    }

    @Description("Sums up all item's prices and returns the sum")
    public Double sumPrices() {
        Double sum=0.0;
        for (int i=0; i<itemPrices.size(); i++) {
           sum = sum + removeFirst$AndConvertToDouble(itemPrices.get(i).getText());
        }
        return sum;
    }

    @Description("Verifies that the total prices sum is equal to the sum of given prices array")
    public CheckoutOverview isTotalPricesMatch(Double [] prices) {
        Double sum = 0.0;
        for (Double price : prices) {
            sum = sum + price;
        }
        Double expectedSum = sumPrices();
        assert sum.equals(expectedSum) : "Actual sum ("+sum+") does not match expected sum ("+expectedSum+")";
        return this;
    }

    @Description("Verifies that the tax calculation equals to the expected tax calculation using the given tax % and the prices sum")
    public CheckoutOverview isTaxCalc(Double taxPercentage, Double sumPrices) {
        Double currentTax = removeFirst$AndConvertToDouble(this.taxPrice.getText());
        Double expectedTax = sumPrices/taxPercentage;
        bigDecimal = new BigDecimal(expectedTax);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        expectedTax = bigDecimal.doubleValue();
        assert Math.abs(currentTax - expectedTax) < 0.001 : "The gap between actual tax ("+currentTax+") and expected tax ("+expectedTax+") is bigger than 0.001";
        return this;
    }

    @Description("Clicks cancel button")
    public void actionClickCancel() {
        click(cancelBtn);
    }
}
