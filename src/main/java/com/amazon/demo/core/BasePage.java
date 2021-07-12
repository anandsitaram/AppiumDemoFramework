package com.amazon.demo.core;

import com.amazon.demo.common.AppiumHelper;
import com.amazon.demo.common.DriverManager;
import com.amazon.demo.reports.ExtentReport;
import com.amazon.demo.utils.LogUtil;
import com.amazon.demo.utils.PropertyUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Anand B S
 * @date 03 Jul 2021
 */
public class BasePage {

    protected static Wait<AppiumDriver> fluentWait;
    private static AppiumHelper appiumHelper;
    private static TouchAction<?> touchAction;
    private static WebDriverWait webDriverWait;

    protected BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        appiumHelper = new AppiumHelper();
        webDriverWait = new WebDriverWait(DriverManager.getDriver(), Long.valueOf(PropertyUtil.getPropertyValue("global_timeout")));
        fluentWait = new FluentWait<AppiumDriver>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class);

    }


    protected void pressEnter() {
        ((AndroidDriver) DriverManager.getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

    }

    protected void scrollTillElement(MobileElement mobileElement) {

        ArrayList<MobileElement> allProductList = new ArrayList<>();
        appiumHelper.getDeviceDimension();
        int startX = appiumHelper.getStartX();
        int endX = startX;
        int startY = appiumHelper.getStartY();
        int endY = appiumHelper.getEndY();

        TouchAction<?> ta = new TouchAction<>(DriverManager.getDriver());
        ta.press(PointOption.point(startX, startY)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(ElementOption.element(mobileElement))
                .release().perform();


    }

    protected void scrollAndSelectProduct(List<MobileElement> productList, int index) {

        ArrayList<MobileElement> allProductList = new ArrayList<>();
        appiumHelper.getDeviceDimension();
        int startX = appiumHelper.getStartX();
        int endX = startX;
        int startY = appiumHelper.getStartY();
        int endY = appiumHelper.getEndY();
        int i = 0;
        while (i < index) {
            List<MobileElement> currentProductList = productList;
            allProductList.addAll(currentProductList);
            if (allProductList.size() > index) {
                allProductList.get(index - 1).click();
                break;
            }
            TouchAction<?> ta = new TouchAction<>(DriverManager.getDriver());
            ta.press(PointOption.point(startX, startY)).
                    waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endX, endY))
                    .release().perform();
            i++;
        }

    }

    protected void scrollTillTextDisplayed(String text) {
        ExtentReport.getTest().log(Status.INFO, "Scroll till the text " + text);
        String uiSelector = "new UiSelector().textContains(\"" + text
                + "\")";

        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                + uiSelector + ");";

        ((AndroidDriver) DriverManager.getDriver()).findElementByAndroidUIAutomator(command);
    }

    protected void waitForScreenLoad(MobileElement mobileElement) {
        webDriverWait.until(d -> mobileElement.isDisplayed());
    }

    protected void waitForElement(MobileElement mobileElement) {
        webDriverWait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(mobileElement));
    }

    protected void waitForElementClickable(MobileElement mobileElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
    }


    protected void handleStale(MobileElement element) {
        webDriverWait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(MobileElement mobileElement, String message) {
        waitForElement(mobileElement);
        LogUtil.log().info(message);
        ExtentReport.getTest().log(Status.INFO, message);
        mobileElement.click();
    }

    protected void sendKeys(MobileElement mobileElement, String text, String message) {
        waitForElement(mobileElement);
        LogUtil.log().info(message);
        ExtentReport.getTest().log(Status.INFO, message);
        mobileElement.sendKeys(text);
    }

    protected String getAttribute(MobileElement mobileElement, String attribute) {
        waitForElement(mobileElement);
        return mobileElement.getAttribute(attribute);
    }

    protected String getText(MobileElement e, String message) {
        String text = null;
        text = getAttribute(e, "text");
        LogUtil.log().info(message + text);
        ExtentReport.getTest().log(Status.INFO, message + text);
        return text;
    }
}
