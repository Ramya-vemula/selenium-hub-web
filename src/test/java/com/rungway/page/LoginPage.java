package com.rungway.page;

import com.rungway.utils.Helpers;
import com.rungway.utils.URLConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(css = "svg.SVGInline-svg")
    public WebElement logo;

    @FindBy(tagName = "h1")
    public WebElement pageTitle;

    @FindBy(id = "login_email")
    public WebElement emailAddressField;

    @FindBy(id = "login_password")
    public WebElement passwordField;

    @FindBy(css = ".SVGInline.eye")
    public WebElement passwordEyeIcon;

    @FindBy(css = ".SVGInline.eye-cross")
    public WebElement passwordEyeCrossIcon;

    @FindBy(id = "login_button")
    public WebElement loginButton;

    @FindBy(className = "message")
    public WebElement errorMessage;

    @FindBy(css = "label[title='Email']")
    public WebElement emailFieldLabel;

    @FindBy(css = "label[title='Password']")
    public WebElement passwordFieldLabel;

    @FindBy(className = "login-links")
    public WebElement footer;

    @FindBy(css = "a#terms-of-use")
    public WebElement tcLink;

    @FindBy(css = "a#privacy-policy")
    public WebElement ppLink;

    private WebDriver driver;

    public LoginPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void loginAsUser(final String email, final String password) {
        emailAddressField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void loginAsValidUser() {
        loginAsUser("rohith.vitta@rungway.com", "Rungway@2018");
        //wait for dashboard page title
        DashboardPage dashboardPage = new DashboardPage(driver);
        Helpers.waitForElementToAppear(driver, dashboardPage.pageTitle);

    }
}
