package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.DashboardPage;
import com.rungway.page.LoginPage;
import com.rungway.utils.Helpers;
import com.rungway.utils.LocalStorage;
import com.rungway.utils.TestData;
import com.rungway.utils.URLConstants;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPageTests extends BaseTests {

    @Test
    public void verifyAllElementsAreDisplayedOnLoginPage() {

        //navigate to login page
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);

        //verify logo is displayed
        loginPage.logo.isDisplayed();

        //verify page title is correct
        Assert.assertEquals(loginPage.pageTitle.getText(), "Log in to your Rungway");

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
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);

        //enter text into password field
        loginPage.passwordField.sendKeys("test");

        //verify password field eye icon is displayed
        loginPage.passwordEyeIcon.isDisplayed();

        //verify password text is not displayed
        Assert.assertEquals(passwordDisplayedType(loginPage.passwordField), "password");

        //click on the password eye, verify password field eye-cross icon is displayed
        loginPage.passwordEyeIcon.click();
        loginPage.passwordEyeCrossIcon.isDisplayed();

        //verify password text is displayed
        Assert.assertEquals(passwordDisplayedType(loginPage.passwordField), "text");
    }

    @Test
    public void verifyLoginAsValidUser() {

        //navigate to login page
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser();
    }

    @Test
    public void verifyErrorMessageAppearsWithInvalidEmailAndValidPassword() {

        driver.get(URLConstants.loginPage());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("rungway.com", TestData.USER_PASSWORD);

        // verify failure error message appears
        Assert.assertEquals(loginPage.errorMessage.getText(), "There's something wrong with your email or password, please try again");

    }

    @Test
    public void verifyErrorMessageAppearsWithValidEmailAndInValidPassword() {

        driver.get(URLConstants.loginPage());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser(TestData.USER_EMAIL, "password");

        // verify failure error message appears
        Assert.assertEquals(loginPage.errorMessage.getText(), "There's something wrong with your email or password, please try again");
    }

    @Test
    public void verifyFooterOnLoginPage() {

        //navigate to login page
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);

        //verify footer text
        Assert.assertEquals(loginPage.footer.getText(), "Rungway © 2018 | Read our Privacy Policy and Terms of Use");
    }

    @Test
    public void verifyPPAndTCLinks() throws InterruptedException {

        //navigate to login page
        driver.get(URLConstants.loginPage());
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
        Assert.assertEquals(loginPage.pageTitle.getText(), "End User Licence Agreement");

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
        Assert.assertEquals(loginPage.pageTitle.getText(), "User Privacy Policy");
    }

    @Test
    public void loginAsValidUserVerifyLocalStorageElements() throws InterruptedException {

        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser();

        // verify local storage elements are present
        Thread.sleep(1000);
        LocalStorage localStorage = new LocalStorage(driver);
        String jwt = localStorage.getItemFromLocalStorage("RW_jwt");
        Assert.assertNotNull(jwt);
    }

    @Test
    public void logoutAsValidUserVerifyLocalStorageElementIsNull() throws InterruptedException {

        //login as a valid user
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser();

        //logout
        driver.get(URLConstants.logoutPage());

        Thread.sleep(1000);
        // verify local storage elements is null
        LocalStorage localStorage = new LocalStorage(driver);
        String validAccessToken = localStorage.getItemFromLocalStorage("RW_jwt");
        Assert.assertNull(validAccessToken);
    }

    @Test
    @Ignore
    public void insertExpiredTokenInLocalStorageVerifyLoginPageIsDisplayed() {

        driver.get(URLConstants.getBaseUrl());

        //insert expired access token into local storage
        LocalStorage localStorage = new LocalStorage(driver);
        localStorage.setItemInLocalStorage("RW_jwt", TestData.EXPIRED_TOKEN);

        //navigate to my login url, verify login page is displayed
        //TODO: Enable this test once redirection is fixed on web
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.pageContainer.isDisplayed();
    }

    @Test
    public void insertValidTokenInLocalStorageVerifyLoginPageIsNotDisplayed() throws IOException {

        driver.get(URLConstants.getBaseUrl());

        String accessToken = Helpers.getAccessToken(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        //insert expired access token into local storage
        LocalStorage localStorage = new LocalStorage(driver);
        localStorage.setItemInLocalStorage("RW_jwt", accessToken);

        //navigate to my base url, verify dashboard page is displayed
        driver.get(URLConstants.getBaseUrl());
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.pageTitle.isDisplayed();
    }
}
