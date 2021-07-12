package com.amazon.demo.reports;

import com.amazon.demo.utils.PropertyUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.IReporter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anand B S
 * @date 04 Jul 2021
 */
public class ExtentReport implements IReporter {

    static ExtentReports extent;
    final static String filePath = "Extent.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter= new ExtentSparkReporter(filePath);
            sparkReporter.config().setDocumentTitle("Amazon Mobile Automation framework");
            sparkReporter.config().setReportName("AmazonApp");
            sparkReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.setSystemInfo("Platform", PropertyUtil.getPropertyValue("device.type").toUpperCase());
            extent.setSystemInfo("Device Name ", PropertyUtil.getPropertyValue("deviceName"));
            extent.setSystemInfo("Environment",PropertyUtil.getPropertyValue("env"));
            extent.attachReporter(sparkReporter);
        }

        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

}
