package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public abstract class BaseTests {

    public WebDriver driver;

    public WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") final String browser) {
        if (browser.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", TestData.getProperty("GEKO_DRIVER_PATH"));
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", TestData.getProperty("CHROME_DRIVER_PATH"));
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();
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

    @AfterMethod(alwaysRun = true)
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

    public void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForVisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
}
