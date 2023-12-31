package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {
	WebDriver driver;
	JavascriptExecutor js; 
	Actions actions;
	WebDriverWait wait;
	BigDecimal bigDecimal;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
	}
	
	public void fillText (WebElement el, String text) {
		highlightElement(el, "lightblue");
		el.clear();
		el.sendKeys(text);
	}
	
	public void movetTo (WebElement el) {
		actions.moveToElement(el).build().perform();
	}
	
	public void sleep (long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}
	
	public void waitFor(WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	public void waitFor(List<WebElement> el) {
		wait.until(ExpectedConditions.visibilityOfAllElements(el));
	}
	
	public void click(WebElement el) {
		highlightElement(el, "yellow");
		el.click();
	}
	
	public void assertEquals (String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public void assertEquals (Boolean actual, Boolean expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public void assertEquals (int actual, int expected) {
		Assert.assertEquals(actual, expected);
	}
	
	private void highlightElement(WebElement element, String color) {
		String originalStyle = element.getAttribute("style");
		String newStyle = "border: 3px solid blue; background-color:" + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);", element);
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + originalStyle + "');},200);", element);
	}

	public Object refresh() {
		driver.navigate().refresh();
		return this;
	}

	public String returnTextAfterRegex(String text, String pattern) {
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(text);

		if (matcher.find()) {
			String extractedText = matcher.group(1);
			return extractedText;
		}
		return null;
	}
}
