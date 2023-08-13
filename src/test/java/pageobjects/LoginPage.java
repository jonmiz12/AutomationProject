package pageobjects;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(css = "#user-name")
    WebElement usernameField;
    @FindBy(css = "#password")
    WebElement passwordField;
    @FindBy(css = "#login-button")
    WebElement loginBtn;
    @FindBy(css = ".error-message-container.error")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Description("Fills the userName field with the given text")
    public void fillUsernameField(String username){
        fillText(usernameField, username);
    }

    @Description("Fills the password field with the given text")
    public void fillPasswordField(String password){
        fillText(passwordField, password);
    }

    @Description("Clicks the login button")
    public void actionClickLogin(){
        click(loginBtn);
    }

    @Description("Returns the error message")
    public String returnErrorMessage(){
        return errorMessage.getText();
    }

    @Description("Fills the login form and clicks the 'Login' button")
    public LoginPage login(String username, String password){
        fillUsernameField(username);
        fillPasswordField(password);
        actionClickLogin();
        return this;
    }
}
