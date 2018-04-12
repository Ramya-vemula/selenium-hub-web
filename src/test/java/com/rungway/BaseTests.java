package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseTests {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {

        String seleniumServerUrl = "http://localhost:5555/wd/hub";
        try {
            driver = new RemoteWebDriver(new URL(seleniumServerUrl), new ChromeOptions().addArguments("start-maximized"));
            driver.get(TestData.getProperty("BASE_URL"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot(driver);
        }
        driver.quit();
    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] saveScreenshot(final WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
