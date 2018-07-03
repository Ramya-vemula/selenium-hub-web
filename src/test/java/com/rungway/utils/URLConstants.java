package com.rungway.utils;

public class URLConstants {

    public static String getBaseUrl(){
        return TestData.getProperty("BASE_URL");
    }

    public static String loginPageURL() {
        return getBaseUrl() + "login";
    }

    public static String logoutPageURL() {
        return getBaseUrl() + "logout";
    }
}
