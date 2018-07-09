package com.rungway.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {

    public static void waitForElementToAppear(WebDriver driver, WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static void waitForPageLoadComplete(WebDriver driver) {
        waitForPageLoadComplete(driver, 30);
    }

}
