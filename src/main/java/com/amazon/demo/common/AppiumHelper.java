package com.amazon.demo.common;

import org.openqa.selenium.Dimension;

/**
 * @author Anand B S
 * @date 03 Jul 2021
 */
public class AppiumHelper {

    private int startX;
    private int endX;
    private int startY;
    private int endY;

    public AppiumHelper() {
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public AppiumHelper getDeviceDimension() {
        Dimension dimension = DriverManager.getDriver().manage().window().getSize();
        setStartX(dimension.width / 2);
        setEndX(getStartX());
        setStartY((int) (dimension.height * 0.8));
        setEndY((int) (dimension.height * 0.2));

        return this;
    }

}
