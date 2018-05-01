package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        String seleniumServerUrl = "http://34.248.197.92:4444/wd/hub";

        if (browser.equalsIgnoreCase("firefox")) {
            driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.firefox());
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.chrome());
        } else if (browser.equalsIgnoreCase("ie")) {
            driver = new RemoteWebDriver(new URL(seleniumServerUrl), DesiredCapabilities.internetExplorer());
        } else {
            //If no browser passed throw exception
            try {
                throw new Exception("Browser is not correct");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
        driver.get(TestData.getProperty("BASE_URL"));

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

    public void waitForVisibilityOfElement(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForVisibilityOfElement(final By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForElementToDisappear(final WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void waitForElementToDisappear(final By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
}
