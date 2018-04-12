package com.rungway;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class TestSetup {

    private Properties properties;

    private String environment;

    public TestSetup() {
        loadEnvironment();
        loadProperties();
    }

    public Properties getProperties() {
        return properties;
    }

    private void loadProperties() {
        String resourceFilename = getFilenameFromEnvironment(environment);

        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(resourceFilename));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEnvironment() {
        environment = System.getProperty("ENVIRONMENT", "dev");
        if (StringUtils.isEmpty(environment)) {
            environment = "dev";
        }
    }

    private String getFilenameFromEnvironment(String environment) {
        return environment + ".properties";
    }
}

