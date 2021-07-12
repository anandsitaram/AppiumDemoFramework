package com.amazon.demo.utils;

import com.amazon.demo.exceptions.ValueNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class PropertyUtil {

    private static Properties prop = new Properties();

    private PropertyUtil() {

    }

    static {
        try {
            FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fn);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public static String getPropertyValue(String key) {
        String value = prop.getProperty(key);
        if (Objects.isNull(value)) {
            throw new ValueNotFoundException("value is null or key not found in properties file");

        }
        return value;

    }


}
