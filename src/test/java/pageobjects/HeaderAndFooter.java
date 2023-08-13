package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderAndFooter extends BasePage{

    @FindBy(css = "#react-burger-menu-btn")
    WebElement hamburgermenuBtn;
    @FindBy(css = "#logout_sidebar_link")
    WebElement logoutBtn;
    @FindBy(css = ".shopping_cart_link")
    WebElement shoppingCartBtn;
    @FindBy(css = ".shopping_cart_badge")
    WebElement cartCount;
    @FindBy(css = ".title")
    WebElement pageTitle;
    final static String [] social = {".social_twitter>a",
                                     ".social_facebook>a",
                                     ".social_linkedin>a"};

    @FindBy(css = ".social a")
    List<WebElement> socialLinks;

    public HeaderAndFooter(WebDriver driver) {
        super(driver);
    }

    @Description("Clicks the hamburger menu button")
    public void actionClickHamburger(){
        waitFor(hamburgermenuBtn);
        click(hamburgermenuBtn);
    }

    @Description("Clicks the logout button")
    public void actionClickLogout(){
        click(logoutBtn);
    }

    @Description("Clicks the hamburger menu and then loguot button")
    public HeaderAndFooter logout(){
        actionClickHamburger();
        actionClickLogout();
        return this;
    }

    @Description("Click the cart button")
    public HeaderAndFooter actionClickCart() {
        click(shoppingCartBtn);
        return this;
    }

    @Description("Verifies if the cart count matches the given number")
    public HeaderAndFooter isCartCountMatch (int expectedCartCount) {
        assert returnCartCount()==expectedCartCount;
        return this;
    }

    @Description("Returns the cart count")
    public int returnCartCount() {
        int count;
        try {
            count = Integer.parseInt(cartCount.getText());
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }

    @Description("Returns the page title")
    public String getPageTitle() {
        return pageTitle.getText();
    }

    @Description("Verifies if the actual page title matches the given text")
    public HeaderAndFooter isPageTitleMatch(String expectedPageTitle){
        String actualPageTitle = getPageTitle();
        assert expectedPageTitle.equals(actualPageTitle) : "Actual page title '"+actualPageTitle+"' does not match expected page title '"+expectedPageTitle+"'";
        return this;
    }

    @Description("Verifies that the actual social link matches the given social link")
    public HeaderAndFooter compareSocialLinks(String[] socialLinks) {
        assert this.socialLinks.size()==socialLinks.length : "The actual links size ("+this.socialLinks.size()+") does not match the expected social links size ("+socialLinks.length+")";
        for (String expectedSocialLink : socialLinks) {
            for (int i=0; i<this.socialLinks.size()+1; i++) {
                assert i!=this.socialLinks.size() : "The URL '"+expectedSocialLink+"' was not found";
                if (this.socialLinks.get(i).getAttribute("href").contains(expectedSocialLink)) {
                    break;
                }

            }
        }
        return this;
    }
}
