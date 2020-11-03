package com.testing.bdd.pages;

import com.testing.bdd.utils.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactList extends BaseTest {

    private WebDriver driver;

    @FindBy(css = "h3")
    WebElement pageTitle;

    public ContactList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyContactListPageOpens() {
        Assert.assertEquals("Contact List", pageTitle.getText());
    }

    public void verifyNewContact(String newContact) {
        System.out.println("A new contact is created successfully");
        Assert.assertEquals(1,
                driver.findElements(By.xpath("//table[contains(.,'" + newContact + "')]")).size());
    }

}
