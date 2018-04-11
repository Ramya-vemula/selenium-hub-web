package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTests {

    @Test
    public void firstTest() {
        driver.get("https://rungway.dev.rungway.uk/");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.pageTitleText(), "Log i");
    }

    @Test
    public void secondTest() {

        driver.get("https://rungway.dev.rungway.uk/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("trash@rungway.com", "Rungway@2017");
    }

}
