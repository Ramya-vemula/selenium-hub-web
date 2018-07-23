package com.rungway.page;

import com.rungway.utils.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DashboardPage {

    @FindBy(tagName = "h1")
    public WebElement pageTitle;


    private WebDriver driver;

    public DashboardPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        Helpers.waitForPageLoadComplete(driver);
    }
}
