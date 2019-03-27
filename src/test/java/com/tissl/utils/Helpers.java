package com.tissl.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static void waitForURLToChange(WebDriver driver, String url) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe(url));
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

    public static String getAccessToken(String username, String password) throws IOException, JSONException {
        String url = URLConstants.apiTokenURL();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");


        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", username));
        urlParameters.add(new BasicNameValuePair("password", password));
        urlParameters.add(new BasicNameValuePair("scope", "read write"));
        urlParameters.add(new BasicNameValuePair("grant_type", "password"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String json = reader.readLine();
        JSONTokener tokener = new JSONTokener(json);
        JSONObject result = new JSONObject(tokener);
        String accessToken = result.get("access_token").toString();
        return accessToken;
    }
}
