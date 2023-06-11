package tests;

import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;

public class ImageComparison extends BaseTest{

    @Test
    public void tc01_matchImagesInProductsPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        ProductsPage psp = new ProductsPage(driver);
    }
}
