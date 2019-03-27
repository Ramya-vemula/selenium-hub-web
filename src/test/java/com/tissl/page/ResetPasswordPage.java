package com.tissl.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ResetPasswordPage {

    private WebDriver driver;

    @FindBy(id = "page_header")
    public WebElement pageTitle;

    @FindBy(id = "reset_password_email")
    public WebElement emailAddressField;

    @FindBy(id = "reset_password_button")
    public WebElement sendLinkButton;

    @FindBy(id = "message")
    public WebElement messageArea;

    public ResetPasswordPage(final WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
}
