package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseTests {

    public WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") final String browser) throws MalformedURLException {
        String seleniumServerUrl = "http://" + TestData.getProperty("SELENIUM_SERVER_IP") + ":5555/wd/hub";

        switch (browser) {

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), firefoxOptions);
                break;

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), chromeOptions);
                break;

            case "ie":
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                driver = new RemoteWebDriver(new URL(seleniumServerUrl), internetExplorerOptions);
                break;

            default:
                System.out.println("** Invalid browser parameter **");
        }
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

    public String passwordDisplayedType(final WebElement element) {
        return element.getAttribute("type");

    }
}
