package com.amazon.demo.pageobjects;

import com.amazon.demo.core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Anand B S
 * @date 02 Jul 2021
 */
public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Search']")
    private MobileElement searchBar;

    public HomePage() {
        fluentWait.until(ExpectedConditions.visibilityOf(searchBar));
    }

    public ProductResultPage searchProduct(String product) {
        click(searchBar, "Tap on Search");
        sendKeys(searchBar, product, "Enter the product");
        pressEnter();
        return new ProductResultPage();
    }


}
