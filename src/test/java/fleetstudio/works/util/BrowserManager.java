package fleetstudio.works.util;

import fleetstudio.works.util.*;
import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserManager {

	private static ExtentReports extent;
	private static ExtentTest logger;
	private static String url ;
	private static String browserName ;
	private static String environment ;
	private static String userName ;
	private static String password ;
	private static WebDriver driver;
	
	public static void getProperties() {
		 JSONParser parser = new JSONParser();
		 
		  try {
		 
		   Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\fleetstudio\\works\\util\\Constants.json"));
		 
		   JSONObject jsonObject = (JSONObject) obj;
		   browserName = (String) jsonObject.get("Browser");
		   System.out.println("Name Of browser: "+browserName);
		   
		   url = (String) jsonObject.get("URL");
		   System.out.println("Name Of url: "+url);

		  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			  } catch (IOException e) {
			   e.printStackTrace();
			  } catch (ParseException e) {
			   e.printStackTrace();
			  }
			 
	}
	
	public static void setUpBrowser() {
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/FleetExtentReport.html", true);
		driver.get(url);
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getTitle(), "Fleet Studio | Home");
	}

	@BeforeTest
	public static WebDriver getDriver() {
		System.out.println("Before Test Setup");
		getProperties();
		switch(browserName)
		{
		case "Chrome":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\java\\fleetstudio\\works\\drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
			 break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\java\\fleetstudio\\works\\drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 break;
		}
	    setUpBrowser();
		return driver;
}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		}
	
	@AfterTest
	public void tearDown(ITestResult result) throws Exception {
		System.out.println("On Tear Down");
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Failed "+result.getName());
			String screenshotPath = getScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL,logger.addScreenCapture(screenshotPath));
		}
		else {
			if(result.getStatus()==ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, "Test Passed "+result.getName());
			}
		}
		driver.quit();
		extent.endTest(logger);
		extent.close();
		driver = null;
	}
}
