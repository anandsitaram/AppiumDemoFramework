package com.amazon.demo.core;


import com.amazon.demo.reports.ExtentReport;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

/**
 * @author Anand B S
 * @date 11 Jul 2021
 */
public class Assertions   {

    public static  void assertEquals(String a ,String b){
        Throwable th= null;
try {
            Assert.assertEquals(a, b);

        } catch (AssertionError e) {
            System.out.println("Not equal");
    ExtentReport.getTest().log(Status.FAIL," ");
            //throw e;
        }
        System.out.println("Equal");

    }




}
