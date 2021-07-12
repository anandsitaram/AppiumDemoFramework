package com.amazon.demo.listeners;

import com.amazon.demo.common.DriverManager;
import com.amazon.demo.reports.ExtentReport;
import com.amazon.demo.utils.DateUtil;
import com.amazon.demo.utils.LogUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @author Anand B S
 * @date 04 Jul 2021
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
                .assignAuthor("Anand BS");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (Objects.isNull(result.getThrowable())) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            LogUtil.log().error(sw);

        }

        File file = DriverManager.getDriver().getScreenshotAs(OutputType.FILE);
        String fileName= DateUtil.getDateTime()+"-"+result.getName() + ".png";
        String imagePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName;
        try {
            FileUtils.copyFile(file, new File(imagePath));
            Reporter.log("The failure screenshot is ");
            Reporter.log("<a href='"+ imagePath + "'> <img src='"+ imagePath + "' height='400' width='400'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentReport.getTest().fail("Test Failed :- "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
result.setStatus(2);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Script is Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentReport.getTest().log(Status.SKIP, "Test Script is Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentReport.getReporter().flush();
    }

}
