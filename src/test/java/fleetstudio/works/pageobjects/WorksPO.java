package fleetstudio.works.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class WorksPO extends BasePO {
	
	public WorksPO(WebDriver driver){
		super(driver);
		this.driver = driver;
	}

	By worksLink = By.xpath("//a[text()='Work']");
	By productDev = By.xpath("//span[text() ='Product Dev']");
	By readMoreLink = By.xpath("//div/a[text()='Read More']");
	By EmilcottGreenlightLink = By.xpath("//strong[text()='Emilcott Greenlight']");
	
	public void clickWorkLink() {
		waitUntil(worksLink).click();
		Assert.assertEquals(waitUntil(productDev).getText(), "Product Dev");
	}
	
	public void clickReadMoreLink() {
		waitUntil(readMoreLink).click();
		Assert.assertEquals(waitUntil(productDev).getText(), "Product Dev");
		Assert.assertEquals(waitUntil(EmilcottGreenlightLink).getText(), "Emilcott Greenlight");
	}
	
}
