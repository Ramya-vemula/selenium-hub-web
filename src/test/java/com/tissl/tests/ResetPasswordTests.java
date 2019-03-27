package com.rungway.tests;


import com.rungway.BaseTests;
import com.rungway.page.DashboardPage;
import com.rungway.page.LoginPage;
import com.rungway.page.ResetPasswordConfirmationPage;
import com.rungway.page.ResetPasswordPage;
import com.rungway.utils.Helpers;
import com.rungway.utils.LocalStorage;
import com.rungway.utils.TestData;
import com.rungway.utils.URLConstants;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResetPasswordTests extends BaseTests {

    @Test
    public void verifyForgotPasswordLink() {

        //navigate to login page url
        driver.get(URLConstants.loginPage());

        //click an forgot password link
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordLink.click();

        //verify the current url and the email address field is displayed
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.emailAddressField.isDisplayed();
        Assert.assertEquals(URLConstants.resetPasswordPage(), driver.getCurrentUrl());
    }

    @Test
    public void resetPasswordPageValidation() {

        // navigate to login url
        driver.get(URLConstants.loginPage());

        //click an forgot password link
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordLink.click();


        //verify send link button is disabled
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        Assert.assertFalse(resetPasswordPage.sendLinkButton.isEnabled());

        List<String> invalidEmails = Arrays.asList(
                "plainaddress",
                "#@%^%#$@#$@#.com",
                "@example.com",
                "Joe Smith <email@example.com>",
                "email.example.com",
                "email@example@example.com",
                "email@example.com (Joe Smith)",
                "email@example..com"
        );

        for (String email : invalidEmails) {
            resetPasswordPage.emailAddressField.clear();
            resetPasswordPage.emailAddressField.sendKeys(email);
            resetPasswordPage.sendLinkButton.click();
            Assert.assertFalse(resetPasswordPage.emailAddressField.getAttribute("validationMessage").isEmpty());
        }

    }

    @Test
    public void resetPasswordConfirmationPageIsDisplayed() {

        // navigate to login url
        driver.get(URLConstants.loginPage());

        //click an forgot password link
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordLink.click();


        //enter a valid email address
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.emailAddressField.sendKeys(TestData.USER_EMAIL);

        //click on the send link button
        resetPasswordPage.sendLinkButton.click();

        //verify confirmation message
        ResetPasswordConfirmationPage resetPasswordConfirmationPage = new ResetPasswordConfirmationPage(driver);
        Assert.assertEquals(resetPasswordConfirmationPage.messageArea.getText(), "We sent an email with a reset link to " + TestData.USER_EMAIL);
        resetPasswordConfirmationPage.didntReceiveEmailLink.isDisplayed();

    }

    @Test
    public void loginPageShouldDisplayNavigatingBackFromResetPasswordConfirmationPage() {

        //navigate to login url
        driver.get(URLConstants.loginPage());

        //click an forgot password link
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordLink.click();


        //enter a valid email address
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.emailAddressField.sendKeys(TestData.USER_EMAIL);

        //click on the send link button
        resetPasswordPage.sendLinkButton.click();

        //verify confirmation message
        ResetPasswordConfirmationPage resetPasswordConfirmationPage = new ResetPasswordConfirmationPage(driver);
        Assert.assertEquals(resetPasswordConfirmationPage.messageArea.getText(), "We sent an email with a reset link to " + TestData.USER_EMAIL);
        resetPasswordConfirmationPage.didntReceiveEmailLink.isDisplayed();

        //navigate via browser back button
        driver.navigate().back();
        Assert.assertEquals(URLConstants.loginPage(), driver.getCurrentUrl());

        //verify login page is displayed
        loginPage.pageContainer.isDisplayed();
    }

    @Test
    public void clickingOnEmailNotReceivedLinkShouldNavigateToResetPasswordPage() {

        //navigate to login url
        driver.get(URLConstants.loginPage());

        //click an forgot password link
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordLink.click();


        //enter a valid email address
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.emailAddressField.sendKeys(TestData.USER_EMAIL);

        //click on the send link button
        resetPasswordPage.sendLinkButton.click();

        //verify confirmation message
        ResetPasswordConfirmationPage resetPasswordConfirmationPage = new ResetPasswordConfirmationPage(driver);
        Assert.assertEquals(resetPasswordConfirmationPage.messageArea.getText(), "We sent an email with a reset link to " + TestData.USER_EMAIL);
        resetPasswordConfirmationPage.didntReceiveEmailLink.click();

        // verify reset password page is displayed
        resetPasswordPage.emailAddressField.isDisplayed();
        resetPasswordPage.sendLinkButton.isDisplayed();
    }
}
