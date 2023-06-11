package pageobjects;

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

    public void actionClickFinish() {
        click(finishBtn);
    }

    public boolean isPriceMatchByName(String itemName, double price) {
        boolean match = false;
        for (int i=0; i<cartItemsNames.size(); i++) {
            if ((cartItemsNames.get(i).getText().equals(itemName))) {
                match=false;
                if (removeFirstAndConvertToDouble(itemPrices.get(i).getText())==price) {
                    match=true;
                }
            }
        }
        return match;
    }

    public boolean isPriceMatchByArray(String [] itemNames, Double [] prices) {
        if (itemNames.length!=prices.length) {
            return false;
        }
        for (int i=0; i<cartItemsNames.size(); i++) {
            if (!isPriceMatchByName(itemNames[i], prices[i])) {
                return false;
            }
        }
        return true;
    }

    public Double removeFirstAndConvertToDouble(String phrase) {
        if (phrase.startsWith("$")) {
            return Double.parseDouble(phrase.substring(1));
        } else {
            return Double.parseDouble(phrase.split(" ")[1].substring(1));
        }
    }

    public Double sumPrices() {
        Double sum=0.0;
        for (int i=0; i<itemPrices.size(); i++) {
           sum = sum + removeFirstAndConvertToDouble(itemPrices.get(i).getText());
        }
        return sum;
    }

    public boolean isTotalPricesMatch(Double [] prices) {
        Double sum = 0.0;
        for (Double price : prices) {
            sum = sum + price;
        }
        Double expectedSum = sumPrices();
        return sum.equals(expectedSum);
    }

    public boolean isTaxCalc(Double taxPercentage, Double sumPrices) {
        Double currentTax = removeFirstAndConvertToDouble(this.taxPrice.getText());
        Double expectedTax = sumPrices/taxPercentage;
        bigDecimal = new BigDecimal(expectedTax);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        expectedTax = bigDecimal.doubleValue();
        return Math.abs(currentTax - expectedTax) < 0.001;
    }

    public void actionClickCancel() {
        click(cancelBtn);
    }
}
