package com.rungway;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


public abstract class BaseTests {

	public WebDriver driver;

	private DesiredCapabilities chromeBrowserCapabilities() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return capabilities;
	}

	@BeforeMethod(description = "Setup")
	public void setup() throws MalformedURLException {

		String seleniumServerUrl = "http://0.0.0.0:5555/wd/hub";
		driver = new RemoteWebDriver(new URL(seleniumServerUrl), chromeBrowserCapabilities());
	}

	@AfterMethod(description = "Tear down")
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			saveScreenshot(driver);
		}
		driver.quit();
	}


	@Attachment(value = "Page Screenshot", type = "image/png")
	private byte[] saveScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
