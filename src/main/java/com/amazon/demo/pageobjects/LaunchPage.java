package com.amazon.demo.pageobjects;

import com.amazon.demo.core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Anand B S
 * @date 02 Jul 2021
 */
public class LaunchPage extends BasePage {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private MobileElement btnSkipSignin;

    public LaunchPage() {
        fluentWait.until(ExpectedConditions.visibilityOf(btnSkipSignin));

    }

    public HomePage skipSkinin() {
        click(btnSkipSignin, "Tap on Skip signin button");
        return new HomePage();
    }

}
