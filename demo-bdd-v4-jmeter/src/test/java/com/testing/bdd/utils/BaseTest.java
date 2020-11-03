package com.testing.bdd.utils;

import com.testing.bdd.api.ContactAPI;
import com.testing.bdd.pages.Contact;
import com.testing.bdd.pages.ContactList;
import com.testing.bdd.pages.Menu;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseTest {

    WebDriver driver;

    public static WebDriverWait wait;
    public static Element element;

    public static ContactList contactList;
    public static Contact contact;
    public static Menu menu;

    public static ContactAPI contactAPI = new ContactAPI();

    public static Response response;

    public static String URL;

    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", getAbsolutePath() + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);

        element = new Element(driver);

        contactList = new ContactList(driver);
        contact = new Contact(driver);
        menu = new Menu(driver);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void openApplication() {
        System.out.println("Demo Application is opened.");
        driver.get(URL);
        waitForLoad();
    }

    public static void setURL() {
        URL = System.getProperty("app.url");
        if (URL == null || URL.isEmpty()) {
            URL = "http://localhost:7001";
        }
    }

    private String getAbsolutePath() {
        return System.getProperty("user.dir");
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

}
