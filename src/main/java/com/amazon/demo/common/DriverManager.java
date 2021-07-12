package com.amazon.demo.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class DriverManager {

    private static final ThreadLocal<AppiumDriver<MobileElement>> dr = new ThreadLocal<>();

    private DriverManager() {

    }

    public static AppiumDriver<MobileElement> getDriver() {
        return dr.get();
    }

    public static void setDriver(AppiumDriver<MobileElement> driver) {
        dr.set(driver);
    }

    public static void unload() {
        dr.remove();

    }


}
