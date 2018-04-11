package com.rungway.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(css = ".login-wrap h1")
    private WebElement pageTitle;

    @FindBy(id = "email")
    private WebElement emailAddressField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "submit_btn")
    private WebElement loginButton;

    public LoginPage(final WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public String pageTitleText() {

        return pageTitle.getText();
    }

    public void loginAsUser(final String email, final String password) {

        emailAddressField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
