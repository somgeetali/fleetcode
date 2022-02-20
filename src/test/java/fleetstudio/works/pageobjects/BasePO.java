package fleetstudio.works.pageobjects;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePO {
 WebDriver driver;
	public BasePO(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement waitUntil(By by) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	return element;
	}
	
}
