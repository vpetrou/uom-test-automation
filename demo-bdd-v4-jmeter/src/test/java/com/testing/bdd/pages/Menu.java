package com.testing.bdd.pages;

import com.testing.bdd.utils.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Menu extends BaseTest {

    private WebDriver driver;

    @FindBy(id = "newContact")
    WebElement newContactButton;
    @FindBy(id = "listOfContacts")
    WebElement listOfContactsButton;

    public Menu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage(String pageName) {
        System.out.println("The user navigates to " + pageName + " page.");
        switch (pageName) {
            case "Add New Contact":
                newContactButton.click();
                break;
            case "Contact List":
                listOfContactsButton.click();
                break;
        }
        waitForLoad();
    }

}
