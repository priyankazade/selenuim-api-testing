package com.ridecell.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ridecell.utilities.LoggerClass;
import com.ridecell.utilities.TestUtils;

public class BasePage {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static EventFiringWebDriver e_driver;
	protected static ChromeOptions chromeOptions;
	protected static FirefoxOptions FireFoxOptions;
	protected static Logger log;
	
	  protected ITestResult result; 
	  protected ExtentReports extent; 
	  protected ExtentTest extentTest;

	public BasePage() {

		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/ridecell/config/config.properties");
			//src\test\java\com\ridecell\config\
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("io exception");
		}
	}

	protected static void initializaton() {
		startLogger();
		String browserName = properties.getProperty("browser");
		driver = getDriver(browserName);
		
		e_driver = new EventFiringWebDriver(driver);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(properties.getProperty("url"));

	}

	private static <fireFoxOptions> WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", TestUtils.WORKSAPCE_PATH + "//drivers//chromedriver.exe");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--start-maximized");
			System.out.println("Headless browser initiated");
			return new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", TestUtils.WORKSAPCE_PATH + "//drivers//geckodriver.exe");
			FireFoxOptions = new FirefoxOptions();
			FireFoxOptions.addArguments("--headless");
			FireFoxOptions.addArguments("--start-maximized");
			return new FirefoxDriver();
		} 
		return null;
	}
	
	private static void startLogger() {
		log = Logger.getLogger(LoggerClass.class);
		PropertyConfigurator.configure(TestUtils.WORKSAPCE_PATH + "\\src\\main\\resources\\log4j.properties");
	}

	public void tearDownMain() {
		driver.manage().deleteAllCookies();
		driver.close();
	}

	public void FormatResult() { 
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Failed test case is ::" + result.getName());
			extentTest.log(LogStatus.FAIL, "Failed test case is ::" +
					result.getThrowable()); TestUtils.takeScreenShot(driver);
					extentTest.log(LogStatus.FAIL,
							(extentTest).addScreenCapture(TestUtils.SCREENSHOT_PATH)); 
		} else if (result.getStatus() == ITestResult.SKIP) { 
			extentTest.log(LogStatus.SKIP, "Skipped test case is ::" + result.getName()); 
		} else if (result.getStatus()== ITestResult.SUCCESS) { 
			extentTest.log(LogStatus.PASS, "Passed testc case is ::" + result.getName()); 
		}
		extent.endTest(extentTest);
	}
}
