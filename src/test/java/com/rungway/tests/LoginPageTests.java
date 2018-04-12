package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTests {

    @Test
    public void firstTest() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.pageTitleText(), "Log in");
    }

    @Test
    public void secondTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("trash@rungway.com", "Rungway@2017");
    }

}
