package ru.job4j.dream.util;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String getProperty(String param) {
        return loadProperties().getProperty(param);
    }

    private static Properties loadProperties() {
        var properties = new Properties();
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return properties;
    }
}
