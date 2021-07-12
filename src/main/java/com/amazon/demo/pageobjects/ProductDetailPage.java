package com.amazon.demo.pageobjects;

import com.amazon.demo.constants.ProductConstants;
import com.amazon.demo.core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Anand B S
 * @date 03 Jul 2021
 */
public class ProductDetailPage extends BasePage {


    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'rupees')]")
    private MobileElement txtPrice;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='title_feature_div']/android.view.View")
    private MobileElement txtProductTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='add-to-cart-button']")
    private MobileElement btnAddToCart;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart_count")
    private MobileElement txtCount;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Cart']")
    private MobileElement lnkCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SEE MORE']")
    private MobileElement lnkSeeMore;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='productDescription_fullView']/android.view.View")
    private MobileElement txtProductDesc;

    public ProductDetailPage() {
        fluentWait.until(ExpectedConditions.visibilityOf(txtProductTitle));

    }

    public String getProductName() {
        String text = getText(txtProductTitle, "Fetch the title of the product ");
        return text;
    }

    public String getPrice() {
        scrollTillTextDisplayed(ProductConstants.RUPEES.getValue());
        String text = getText(txtPrice, "Fetch the Price of the product ");
        return text.replaceAll("(^[0-9])","");
    }

    public void goToCart() {
        click(lnkCart, "Go to AddToCart");
    }

    public String getAddToCartCount() {
        String text = getText(txtCount, "Fetch the count of Add to cart icon ");
        return text;
    }

    public String getProductDescription() {
        scrollTillTextDisplayed(ProductConstants.DESCRIPTION.getValue());
        if (lnkSeeMore.isDisplayed()) {
            click(lnkSeeMore, "Tap on SeeMore link");
        }
        String text = getText(txtProductDesc, "Fetch the Description of the product");
        return text;
    }

    public ProductDetailPage addProductToCart() {
        scrollTillTextDisplayed(ProductConstants.ADDTOCART.getValue());
        click(btnAddToCart, "Tap on AddToCart");
        return this;

    }

}
