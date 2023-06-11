package pageobjects;

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

    public void fillUsernameField(String username){
        fillText(usernameField, username);
    }

    public void fillPasswordField(String password){
        fillText(passwordField, password);
    }

    public void actionClickLogin(){
        click(loginBtn);
    }

    public String returnErrorMessage(){
        return errorMessage.getText();
    }

    public void login(String username, String password){
        fillUsernameField(username);
        fillPasswordField(password);
        actionClickLogin();
    }
}
