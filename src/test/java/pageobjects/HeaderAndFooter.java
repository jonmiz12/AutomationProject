package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderAndFooter extends BasePage{

    @FindBy(css = "#react-burger-menu-btn")
    WebElement hamburgermenuBtn;
    @FindBy(css = "#logout_sidebar_link")
    WebElement logoutBtn;
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

    public void actionClickHamburger(){
        waitFor(hamburgermenuBtn);
        click(hamburgermenuBtn);
    }

    public void actionClickLogout(){
        click(logoutBtn);
    }

    public void logout(){
        actionClickHamburger();
        actionClickLogout();
    }

    public void actionClickCart() {
        click(cartCount);
    }

    public int returnCartCount() {
        int count;
        try {
            count = Integer.parseInt(cartCount.getText());
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean compareSocialLinks(String[] socialLinks) {
        String currentUrl;
        for (WebElement actualSocialLink : this.socialLinks) {
            for (int i=0; i<socialLinks.length+1; i++) {
                currentUrl = actualSocialLink.getAttribute("href");
                if (currentUrl.contains(socialLinks[i]) || i<socialLinks.length) {
                    break;
                }
                return false;
            }
        }
        return true;


//        if (socialLinks.length!=social.length){
//            return false;
//        }
//        String currentUrl;
//        for (WebElement socialLink : this.socialLinks) {
//            click(socialLink);
//            Set<String> allWindowHandles = driver.getWindowHandles();
//
//            currentUrl = driver.getCurrentUrl();
//            String domain = returnTextAfterRegex(currentUrl, "\\.(.*?)\\.");
//            for (int i=0; i<socialLinks.length+1; i++) {
//                if (currentUrl.contains("twitter") && socialLinks[i].contains("twitter") && i<socialLinks.length) {
//                    break;
//                }
//                return false;
//            }
//        }
//        return true;
    }
}
