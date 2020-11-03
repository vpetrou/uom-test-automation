package com.testing.bdd;

import com.testing.bdd.api.ContactAPI;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json", "pretty",
                "html:target/cucumber-html-report", "junit:target/cucumber-junit-report.xml"},
        features = {"src/test/resources/features/"},
        tags = "(@UI or @API) and not @Manual",
        glue = {"com.testing.bdd.stepdefs"})
public class RunCucumberTest {

    @BeforeClass
    public static void beforeAll() {
        ContactAPI.cleanup();
    }

    @AfterClass
    public static void afterAll() {
        ContactAPI.cleanup();
    }

}
