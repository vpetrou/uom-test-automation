package com.testing.bdd;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class StepDefinitions {

    WebDriver driver;
    WebDriverWait wait;

    @Before("@UI")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", getAbsolutePath() + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @After("@UI")
    public void tearDown() {
        driver.quit();
    }

    @Given("the Demo Application is opened")
    public void the_Demo_Application_is_opened() {
        System.out.println("Demo Application is opened.");
        String URL = System.getProperty("app.url");
        if(URL == null || URL.isEmpty()) {
            driver.get("http://localhost:7001");
        } else {
            driver.get(URL);
        }
        waitForLoad();
        Assert.assertEquals("Contact List", driver.findElement(By.cssSelector("h3")).getText());
    }

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String pageName) {
        System.out.println("The user navigates to " + pageName + " page.");
        switch (pageName) {
            case "Add New Contact":
                driver.findElement(By.id("newContact")).click();
                break;
            case "Contact List":
                driver.findElement(By.id("listOfContacts")).click();
                break;
        }
        Assert.assertEquals(pageName, driver.findElement(By.cssSelector("h3")).getText());
    }

    @When("the user adds a new contact")
    public void the_user_adds_a_new_contact(DataTable dataTable) {
        System.out.println("The user adds a new contact.");
        WebElement name = driver.findElement(By.name("name"));
        name.clear();
        name.sendKeys(getValue(dataTable, "Name"));
        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        email.sendKeys(getValue(dataTable, "Email"));

        WebElement city = driver.findElement(By.name("city"));
        city.click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p-dropdown[@name='city']//li[contains(@class,'ui-dropdown-item') " +
                                "and contains(.,'Athens')]")));
        driver.findElement(By.xpath("//p-dropdown[@name='city']//li[contains(@class,'ui-dropdown-item') " +
                "and contains(.,'Athens')]")).click();

        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        saveButton.click();
        waitForLoad();
        Assert.assertEquals("Contact List", driver.findElement(By.cssSelector("h3")).getText());
    }

    @Then("a new Contact {string} is created successfully")
    public void a_new_Contact_is_created_successfully(String newContact) throws InterruptedException {
        System.out.println("A new contact is created successfully");
        Assert.assertEquals(1,
                driver.findElements(By.xpath("//table[contains(.,'" + newContact + "')]")).size());
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(500);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    /*
     * |Label |Label2|
     * |Value |Value2|
     *
     * returns the value under every label
     */

    public String getValue(DataTable dTable, String label) {
        List<List<String>> rows = dTable.asLists();
        int column = getColumn(dTable, label);
        if (column >= 0) {
            return rows.get(1).get(column);
        } else
            return null;
    }

    private int getColumn(DataTable dTable, String label) {
        int column = 0;
        int k = 0;
        boolean flagNotFound = true;
        List<List<String>> rows = dTable.asLists();
        for (List<String> cell : rows) {
            for (int i = 0; i < cell.size(); i++) {
                if (rows.get(k).get(i).equalsIgnoreCase(label)) {
                    column = i;
                    flagNotFound = false;
                    break;
                }
            }
            k++;
        }
        if (flagNotFound) column = -1;
        return column;
    }

    private String getAbsolutePath() {
        return System.getProperty("user.dir");
    }

}
