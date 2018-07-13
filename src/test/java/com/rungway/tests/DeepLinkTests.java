package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.LoginPage;
import com.rungway.utils.URLConstants;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class DeepLinkTests extends BaseTests {

    @Test
    @Ignore
    public void verifyLoginPageIsDisplginayedForAllDeeplinks() {

        //navigate to my activity url
        driver.get(URLConstants.loginPage());
        LoginPage loginPage = new LoginPage(driver);

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //navigate to discussionsPage url
        driver.get(URLConstants.discussionsPage());

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //navigate to logout url
        driver.get(URLConstants.logoutPage());

        //verify login page container is displayed
        loginPage.pageContainer.isDisplayed();

        //TODO:Add more navigation tests once the url redirection is fixed on web
    }
}
