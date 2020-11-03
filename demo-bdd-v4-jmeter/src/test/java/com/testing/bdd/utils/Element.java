package com.testing.bdd.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Element extends BaseTest{

    WebDriver driver;

    public Element(WebDriver driver) {
        this.driver = driver;
    }
    public void input(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

    public void dropdown(WebElement field, String value) {
        field.click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p-dropdown//li[contains(@class,'ui-dropdown-item') " +
                                "and contains(.,'" + value + "')]")));
        driver.findElement(By.xpath("//p-dropdown//li[contains(@class,'ui-dropdown-item') " +
                "and contains(.,'" + value + "')]")).click();
    }

}
