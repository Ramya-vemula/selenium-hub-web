package com.rungway;


import com.rungway.utils.TestData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public abstract class BaseTests {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(final String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {

            //path to geckodriver
            System.setProperty("webdriver.gecko.driver", "/Users/Rohith/selenium/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {

            //path to chromedriver
            System.setProperty("webdriver.chrome.driver", "/Users/Rohith/selenium/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }

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
}
