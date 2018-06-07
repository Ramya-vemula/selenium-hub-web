package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.LoginPage;
import com.rungway.utils.URLConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPageTests extends BaseTests {

    @Test
    public void verifyAllElementsAreDisplayedOnLoginPage() {

        //navigate to login page
        driver.get(URLConstants.loginPageURL());
        LoginPage loginPage = new LoginPage(driver);

        //verify logo is displayed
        loginPage.logo.isDisplayed();

        //verify page title is correct
        Assert.assertEquals(loginPage.pageTitleText(), "Log in to your Rungway");

        //verify field labels
        Assert.assertEquals(loginPage.emailFieldLabel.getText(), "Email");
        Assert.assertEquals(loginPage.passwordFieldLabel.getText(), "Password");

        //verify placeholder text
        Assert.assertEquals(placeholderText(loginPage.emailAddressField), "Enter your email address");
        Assert.assertEquals(placeholderText(loginPage.passwordField), "Enter your password");

        //verify password field eye icon is displayed
        loginPage.passwordEyeIcon.isDisplayed();

        //verify login button text
        Assert.assertEquals(loginPage.loginButton.getText(), "Log in");
    }

    @Test
    public void verifyPasswordTextIsDisplayed() {

        //navigate to login page
        driver.get(URLConstants.loginPageURL());
        LoginPage loginPage = new LoginPage(driver);

        //enter text into password field
        loginPage.passwordField.sendKeys("test");

        //verify password field eye icon is displayed
        loginPage.passwordEyeIcon.isDisplayed();

        //verify password text is not displayed
        Assert.assertEquals(loginPage.passwordField.getAttribute("type"), "password");

        //click on the password eye, verify password field eye-cross icon is displayed
        loginPage.passwordEyeIcon.click();
        loginPage.passwordEyeCrossIcon.isDisplayed();

        //verify password text is displayed
        Assert.assertEquals(loginPage.passwordField.getAttribute("type"), "text");
    }

    @Test
    public void verifyLoginAsValidUser() {

        //navigate to login page
        driver.get(URLConstants.loginPageURL());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("trash@rungway.com", "Rungway@2017");
    }

    @Test
    public void verifyErrorMessageAppearsWithInvalidEmailAndValidPassword() {

        driver.get(URLConstants.loginPageURL());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("rungway.com", "Rungway@2018");

        // verify failure error message appears
        Assert.assertEquals(loginPage.errorMessage, "Please enter a valid email.");

    }

    @Test
    public void verifyErrorMessageAppearsWithValidEmailAndInValidPassword() {

        driver.get(URLConstants.loginPageURL());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("rohith.vitta@rungway.com", "password");

        // verify failure error message appears
        Assert.assertEquals(loginPage.errorMessage, "Please enter a valid password.");
    }

    @Test
    public void verifyFooterOnLoginPage() {

        //navigate to login page
        driver.get(URLConstants.loginPageURL());
        LoginPage loginPage = new LoginPage(driver);

        //verify footer text
        Assert.assertEquals(loginPage.footer.getText(), "Rungway © 2018 | Read our Privacy Policy and Terms of Use");
    }

    @Test
    public void verifyPPAndTCLinks() throws InterruptedException {

        //navigate to login page
        driver.get(URLConstants.loginPageURL());
        LoginPage loginPage = new LoginPage(driver);

        //click an verify TC link
        loginPage.tcLink.click();
        Assert.assertEquals(loginPage.tcLink.getText(), "Terms of Use");

        //get window handled after opening TC link
        Thread.sleep(1000);
        List<String> browserTabsWithTCLink = new ArrayList<>(driver.getWindowHandles());

        //switch to TC tab and verify the currentUrl
        driver.switchTo().window(browserTabsWithTCLink.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://support.rungway.com/terms-conditions.html");
        Assert.assertEquals(loginPage.pageTitleText(), "End User Licence Agreement");

        //close TC tab
        driver.close();

        //switch to main window
        driver.switchTo().window(browserTabsWithTCLink.get(0));

        //click an verify PP link
        loginPage.ppLink.click();
        Assert.assertEquals(loginPage.ppLink.getText(), "Privacy Policy");

        //get window handled after opening PP link
        Thread.sleep(1000);
        List<String> browserTabsWithPPLink = new ArrayList<>(driver.getWindowHandles());

        //switch to PP tab and verify the currentUrl
        driver.switchTo().window(browserTabsWithPPLink.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://support.rungway.com/privacy-policy.html");
        Assert.assertEquals(loginPage.pageTitleText(), "User Privacy Policy");
    }

}
