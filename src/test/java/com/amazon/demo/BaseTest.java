package com.amazon.demo;

import com.amazon.demo.common.DriverFactory;
import com.amazon.demo.listeners.TestListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

/**
 * @author Anand B S
 * @date 04 Jul 2021
 */


@Listeners(TestListener.class)

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        DriverFactory.initDriver();
    }

    @AfterSuite
    public void close(){
        DriverFactory.quitDriver();
    }
}
