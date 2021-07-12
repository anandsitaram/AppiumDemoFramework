package com.amazon.demo;

import com.amazon.demo.core.Assertions;
import com.amazon.demo.pageobjects.HomePage;
import com.amazon.demo.utils.JsonUtil;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author Anand B S
 * @date 07 Jul 2021
 */
public class AmazonTest extends  BaseTest {

    @Test
    public void testCase1()  {
        Map<String, String> data = JsonUtil.getMapData("TestCase01");
        Assertions assertion= new Assertions();
        assertion.assertEquals("BB", "");
        String productName=data.get("ProductName");
        int index=Integer.valueOf(data.get("index"));
        HomePage homePage= new HomePage();
        homePage.searchProduct(productName)
                .scrollSelectProduct(index)
                .addProductToCart()
                .goToCart();



    }
}
