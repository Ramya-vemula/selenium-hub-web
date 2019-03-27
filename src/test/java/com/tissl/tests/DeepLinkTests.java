package com.tissl.tests;


import com.tissl.BaseTests;
import com.tissl.page.LoginPage;
import com.tissl.utils.Helpers;
import com.tissl.utils.URLConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeepLinkTests extends BaseTests {

    @Test
    public void verifyLoginPageIsDisplayedForAllDeeplinks() {

        //navigate to login page url
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //navigate to discussionsPage url
        driver.get(URLConstants.discussionsPage());

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //navigate to my-activity url
        driver.get(URLConstants.myActivityPage());

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //navigate to logout url
        driver.get(URLConstants.logoutPage());

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();
    }

    @Test
    public void verifyDiscussionsPageIsDisplayedAfterLogin() {

        //navigate to discussions page url
        driver.get(URLConstants.discussionsPage());
        LoginPage loginPage = new LoginPage(driver);

        //login as valid user
        loginPage.loginAsValidUser();

        //verify discussions page is displayed
        Helpers.waitForURLToChange(driver, URLConstants.discussionsPage());
        Assert.assertEquals(driver.getCurrentUrl(), URLConstants.discussionsPage());
    }

    @Test
    public void verifyMyActivityPageIsDisplayedAfterLogin() {

        //navigate to my-activity page url
        driver.get(URLConstants.myActivityPage());
        LoginPage loginPage = new LoginPage(driver);

        //login as valid user
        loginPage.loginAsValidUser();

        //verify my-activity page is displayed
        Helpers.waitForURLToChange(driver, URLConstants.myActivityPage());
        Assert.assertEquals(URLConstants.myActivityPage(), driver.getCurrentUrl());
    }
}
