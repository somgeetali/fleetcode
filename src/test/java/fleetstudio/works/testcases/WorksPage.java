package fleetstudio.works.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import fleetstudio.works.pageobjects.WorksPO;
import fleetstudio.works.util.BrowserManager;

public class WorksPage {

	@Test
	public void launchWorkPage() {
		WebDriver driver = BrowserManager.getDriver();
		WorksPO workObj = new WorksPO(driver);
		workObj.clickWorkLink();
	}
	
	@Test
	public void readMoreProductDevGreenLightPage() {
		WebDriver driver = BrowserManager.getDriver();
		WorksPO workObj = new WorksPO(driver);
		workObj.clickWorkLink();
		workObj.clickReadMoreLink();
	}
	
	@Test(enabled=false)
	public void readMoreProductDevGiLeadPage() {
		
	}
	
	@Test(enabled=false)
	public void readMoreQABMWPage() {
		
	}
	
	@Test(enabled=false)
	public void filterProductDevGreenLightPage() {
			
		}
	
	@Test(enabled=false)
	public void filterProductDevGiLeadPage() {
			
		}
	
	@Test(enabled=false)
public void filterQABMWPage() {
		
	}
	
}
