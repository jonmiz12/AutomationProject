package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class t8_SocialMediaLinks extends BaseTest{

    @Description("The test matches the actual social links with the expected social links at the products page")
    @Test
    public void tc01_verifySocialMediaLinksInProductsPage() {
        LoginPage.login(username, password);
        ProductsPage.compareSocialLinks(socialLinks);
    }

    @Description("The test adds a specific product and then matches the actual social links with the expected social links at the specified product page")
    @Test
    public void tc01_verifySocialMediaLinksInProductPage() {
        LoginPage.login(username, password);
        ProductsPage.actionClickAddByName(itemsNames[0][0]);
        HeaderAndFooter.compareSocialLinks(socialLinks);
    }
}
