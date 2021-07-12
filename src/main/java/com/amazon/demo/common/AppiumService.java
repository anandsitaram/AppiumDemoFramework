package com.amazon.demo.common;

import com.amazon.demo.utils.PropertyUtil;
import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class AppiumService {


    private static AppiumDriverLocalService appiumDriverLocalService;

    private static AppiumDriverLocalService buildAppiumDefaultService() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    private static AppiumDriverLocalService buildAppiumServiceWithPort(int port) {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(port));
    }

    private static AppiumDriverLocalService buildAppiumServiceCustom() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingPort(4721)
                .usingDriverExecutable(new File(""))
                .withAppiumJS(new File(""))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        );
    }

    public static void startAppiumServer(int port) {
        if (!checkIfServerIsRunning(port)) {
            appiumDriverLocalService.stop();
            Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
        }
        appiumDriverLocalService.start();
        appiumDriverLocalService.clearOutPutStreams();

    }

    public static void stopAppiumServer() {
        appiumDriverLocalService.stop();

    }

    public static boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    public AppiumService initAppiumService() {
        String appiumServiceValue = PropertyUtil.getPropertyValue("appiumService");

        switch (appiumServiceValue) {

            case "Default":
                appiumDriverLocalService = buildAppiumDefaultService();
                appiumDriverLocalService.start();
                break;
            case "DefaultWithPort":
                appiumDriverLocalService = buildAppiumServiceWithPort(Integer.parseInt(PropertyUtil.getPropertyValue("port")));
                appiumDriverLocalService.start();
                break;
        }

        return this;
    }

    public URL getUrl() {
        return appiumDriverLocalService.getUrl();
    }


}
