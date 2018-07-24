package com.rungway.utils;

public class URLConstants {

    public static String getBaseUrl() {
        return TestData.getProperty("BASE_URL");
    }

    public static String apiBaseUrl() {
        return TestData.getProperty("API_BASE_URL");
    }

    public static String loginPage() {
        return getBaseUrl() + "login";
    }

    public static String logoutPage() {
        return getBaseUrl() + "logout";
    }

    public static String myActivityPage() {
        return getBaseUrl() + "my-activity";
    }

    public static String discussionsPage() {
        return getBaseUrl() + "discussions";
    }

    public static String profilePage() {
        return getBaseUrl() + "profilePage";
    }

    public static String apiTokenURL() {
        return apiBaseUrl() + "oauth/token";
    }

    public static String resetPasswordPage() {
        return getBaseUrl() + "reset-password";
    }
}
