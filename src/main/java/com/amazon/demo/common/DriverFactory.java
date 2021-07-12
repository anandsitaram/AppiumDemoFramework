package com.amazon.demo.common;

import com.amazon.demo.reports.ExtentReport;
import com.amazon.demo.utils.PropertyUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class DriverFactory {

    public static AppiumDriver<MobileElement> appiumDriver;
    private static AppiumService appiumService;
    private static DesiredCapabilities capabilities;
    private DriverFactory() {

    }

    public static void initDriver() {

        if (Objects.isNull(DriverManager.getDriver())) {

            String deviceType = PropertyUtil.getPropertyValue("device.type");
            appiumService = new AppiumService().initAppiumService();
            capabilities = new DesiredCapabilities();
            switch (deviceType) {

                case "android":
                    initAndroidDriver();
                    break;
                case "Ios":
                    break;
                default:
                    System.out.println("Throw Exception");
                    break;
            }
        }
    }

    private static void initAndroidDriver() {

        setAppCapabilities();
        AppiumService.startAppiumServer(Integer.parseInt(PropertyUtil.getPropertyValue("port")));

        appiumDriver = new AndroidDriver<>(appiumService.getUrl(), capabilities);
        DriverManager.setDriver(appiumDriver);


    }

    public static void quitDriver() {

        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
            AppiumService.stopAppiumServer();

        }
    }

    public static void setAppCapabilities() {

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyUtil.getPropertyValue("deviceName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PropertyUtil.getPropertyValue("appPackage"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, PropertyUtil.getPropertyValue("appActivity"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 75);
        String resetValue = PropertyUtil.getPropertyValue("reset");
        switch (resetValue) {

            case "noreset":
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                break;
            case "fullreset":
                capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
                //Place the app under app folder
                capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "");
                break;
            default:
                ExtentReport.getTest().log(Status.INFO, "no value provided for reset in config file so noreset is set to true");
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        }
    }

}
