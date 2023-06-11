package tests;

import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;

public class SocialMediaLinks extends BaseTest{

    @Test
    public void tc01_verifySocialMediaLinksInProductsPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.assertEquals(psp.compareSocialLinks(socialLinks), true);
    }

    @Test
    public void tc01_verifySocialMediaLinksInProductPage() {
        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
        ProductsPage psp = new ProductsPage(driver);
        psp.actionClickItemName(itemNames[0]);
        ProductPage pp = new ProductPage(driver);
        pp.assertEquals(psp.compareSocialLinks(socialLinks), true);
    }
}
