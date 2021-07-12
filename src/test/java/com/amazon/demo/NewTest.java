package com.amazon.demo;

import com.amazon.demo.common.DriverFactory;
import com.amazon.demo.common.DriverManager;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {
	


	@BeforeClass
	public void setUp(){
		DriverFactory.initDriver();
	}

	@AfterClass
	public void close(){
		DriverFactory.quitDriver();
	}
  @Test
  public void testA() {
	  WebElement byAccessibilityId =  DriverManager.getDriver().findElementByAccessibilityId("Accessibility");
		  System.out.println("ByAccessibilityId - " + byAccessibilityId.getText());
		
	  
  }
	@Test
	public void Test02_ById() {

		MobileElement byId = DriverManager.getDriver().findElementById("android:id/text1");
		System.out.println("ById- " + byId.getText());

	}


}
