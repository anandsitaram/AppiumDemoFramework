package com.amazon.demo.pageobjects;

import com.amazon.demo.core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author Anand B S
 * @date 02 Jul 2021
 */
public class ProductResultPage extends BasePage {

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
    private List<MobileElement> lstproductTitle;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_results_count_text")
    private MobileElement txtSearchCount;

    public ProductResultPage() {
        fluentWait.until(ExpectedConditions.visibilityOf(txtSearchCount));

    }

    public ProductDetailPage scrollSelectProduct(int index) {
        waitForElement(txtSearchCount);
        scrollAndSelectProduct(lstproductTitle, index);
        return new ProductDetailPage();
    }


}


