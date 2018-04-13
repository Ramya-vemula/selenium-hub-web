package com.rungway.utils;

import com.rungway.TestSetup;

public class TestData {

    private static TestSetup setup;

    static {
        setup = new TestSetup();
    }

    public static String getProperty(final String propertyKey) {
        return setup.getProperties().getProperty(propertyKey);
    }

}
