package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.*;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class BaseTest {
    String purchaseCompleteHeader = "Thank you for your order!";
    String username = "standard_user";
    String password = "secret_sauce";
    String firstName = "Jon";
    String lastName = "miz";
    String zipCode = "12345";
    String[][] partiallyRemoveArrays = {Utils.readProperty("firstPartiallyRemove").split(","),
                                        Utils.readProperty("secondPartiallyRemove").split(",")};
    String [][] itemsNames = {Utils.readProperty("itemNames").split(","),
                              removeArrayFromArray(Utils.readProperty("itemNames").split(","), partiallyRemoveArrays[0]),
                              removeArrayFromArray(Utils.readProperty("itemNames").split(","), partiallyRemoveArrays[1])};
    Double[][] prices = {convertArrayToDouble(Utils.readProperty("prices").split(",")),
                         removePartiallyRemoveArrayFromPrices(Utils.readProperty("itemNames").split(","), itemsNames[1], convertArrayToDouble(Utils.readProperty("prices").split(","))),
                         removePartiallyRemoveArrayFromPrices(Utils.readProperty("itemNames").split(","), itemsNames[2], convertArrayToDouble(Utils.readProperty("prices").split(",")))};
    Double[] pricesSums = {sumPrices(prices[0]),
                           sumPrices(prices[1]),
                           sumPrices(prices[2])};
    Double taxDivision = 12.5;
    String firstNameError = "Error: First Name is required";
    String lastNameError = "Error: Last Name is required";
    String zipCodeError = "Error: Postal Code is required";
    String checkoutOverviewHeader = "Checkout: Overview";

    String emulatorName = "Pixel_XL_API_33";
    String[] socialLinks = {"linkedin.com/company/sauce-labs",
                            "facebook.com/saucelabs",
                            "twitter.com/saucelabs",};
    WebDriver driver;

    public LoginPage LoginPage;
    public CheckoutOverview CheckoutOverview;
    public CheckoutInformation CheckoutInformation;
    public CheckoutComplete CheckoutComplete;
    public HeaderAndFooter HeaderAndFooter;
    public CartPage CartPage;
    public ProductPage ProductPage;
    public ProductsPage ProductsPage;

    public void initializeObjects(){
        CartPage = new CartPage(driver);
        CheckoutComplete = new CheckoutComplete(driver);
        CheckoutInformation = new CheckoutInformation(driver);
        CheckoutOverview = new CheckoutOverview(driver);
        HeaderAndFooter = new HeaderAndFooter(driver);
        LoginPage = new LoginPage(driver);
        ProductPage = new ProductPage(driver);
        ProductsPage = new ProductsPage(driver);
    }
    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        try {
            Runtime.getRuntime().exec("taskkill.exe /F /IM chromedriver.exe /T" + "cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Utils.readProperty("url"));
        initializeObjects();
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

    public String[] removeArrayFromArray(String[] bigArr, String[] smallArr) {
        ArrayList<String> list = new ArrayList<String>();
        String[] arr = new String[list.size()];
        boolean remove;
        for (int i=0; i<bigArr.length; i++){
            for (int j=0; j<smallArr.length; j++){
                if (smallArr[j].equals(bigArr[i])) {
                    break;
                } else {
                    if (j==smallArr.length-1) {
                        list.add(bigArr[i]);
                    }
                }
            }
        }
        arr = list.toArray(arr);
        return arr;
    }

    public Double[] convertArrayToDouble(String[] sArr) {
        Double[] dArr = new Double[sArr.length];
        for (int i=0; i<sArr.length; i++) {
            dArr[i]=Double.parseDouble(sArr[i]);
        }
        return dArr;
    }

    public Double sumPrices(Double[] prices) {
        Double sum = 0.0;
        for (Double price : prices) {
            sum = sum + price;
        }
        return sum;
    }

    public Double[] removePartiallyRemoveArrayFromPrices(String[] itemNames, String[] updatedArray, Double[] prices) {
        ArrayList<Double> UpdatedPrices = new ArrayList<Double>();
        Double[] arr = new Double[updatedArray.length];
        for (int i=0; i<itemNames.length; i++) {
            for (int j=0; j<updatedArray.length; j++) {
                if (itemNames[i].equals(updatedArray[j])) {
                    UpdatedPrices.add(prices[i]);
                }
            }
        }
        arr = UpdatedPrices.toArray(arr);
        return arr;
    }
}
