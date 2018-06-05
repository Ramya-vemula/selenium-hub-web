package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseTests {

    public WebDriver driver;

    public WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") final String browser) throws MalformedURLException {
        String seleniumServerUrl = "http://" + TestData.getProperty("SELENIUM_SERVER_IP") + ":5555/wd/hub";

        switch (browser) {

            case "firefox":
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.firefox());
                break;

            case "chrome":
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.chrome());
                break;

            case "ie":
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.internetExplorer());
                break;

            default:
                try {
                    throw new Exception("** Invalid browser parameter **");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot();
        }
        driver.quit();
    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public String placeholderText(final WebElement element) {
        return element.getAttribute("placeholder");

    }
}
