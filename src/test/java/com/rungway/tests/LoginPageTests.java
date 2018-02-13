package com.rungway.tests;

import com.rungway.BaseTests;
import com.rungway.page.LoginPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTests {

  @Test
  @Description("This test will fail, due to assertion")
  public void firstTest() {
  	driver.get("https://rungway-test.dev.rungway.uk/");
    LoginPage loginPage = new LoginPage(driver);
    Assert.assertEquals(loginPage.pageTitleText(), "Log n");
  }

  @Test
  @Description("This test should pass")
  public void secondTest() {

	  driver.get("https://rungway-test.dev.rungway.uk/");
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.loginAsUser("trash@rungway.com", "Rungway@2017");
  }

}
