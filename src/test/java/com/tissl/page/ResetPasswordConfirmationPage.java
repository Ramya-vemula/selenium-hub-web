package com.tissl.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ResetPasswordConfirmationPage {

    @FindBy(id = "page_header")
    public WebElement pageTitle;

    @FindBy(css = ".uk-margin-top > a")
    public WebElement didntReceiveEmailLink;

    @FindBy(className = "message")
    public WebElement messageArea;

    private WebDriver driver;

    public ResetPasswordConfirmationPage(final WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
}
