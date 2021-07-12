package com.amazon.demo;

import com.amazon.demo.utils.JsonUtil;
import com.amazon.demo.utils.LogUtil;
import com.amazon.demo.utils.PropertyUtil;
import com.amazon.demo.utils.TestUtil;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class unittests {

    @Test
    public void propertyValidation(){

        System.out.println(PropertyUtil.getPropertyValue(null));

    }
    @Test
    public void propertyValidation1(){

        Map<String, String> data = JsonUtil.getMapData("TestCase01");

        System.out.println(data.get("index"));
        LogUtil.log().error("Error Message");
        LogUtil.log().info("Info Message");
        LogUtil.log().warn("Warn Message");
        LogUtil.log().debug("Debug Message");
        System.out.println(TestUtil.generateRandomNumber(7,7,1));

    }
    @Test
    public void propertyValidation12(){



        System.out.println("Anand");
        LogUtil.log().error("Error Message");
        LogUtil.log().info("Info Message");
        LogUtil.log().warn("Warn Message");
        LogUtil.log().debug("Debug Message");
        System.out.println(TestUtil.generateRandomNumber(7,7,1));



    }

}
