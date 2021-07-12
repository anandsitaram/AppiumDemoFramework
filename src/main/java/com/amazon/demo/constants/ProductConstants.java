package com.amazon.demo.constants;

/**
 * @author Anand B S
 * @date 03 Jul 2021
 */
public enum   ProductConstants {
    ADDTOCART("Add to Cart"),
    DESCRIPTION("Description"),
    RUPEES("rupees");

    private String value;
    ProductConstants(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
