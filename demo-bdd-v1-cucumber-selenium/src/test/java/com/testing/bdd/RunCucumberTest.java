package com.testing.bdd;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json", "pretty",
                "html:target/cucumber-html-report", "junit:target/cucumber-junit-report.xml"},
        features = {"src/test/resources/features/"},
        tags = "(@UI or @API) and not @Manual",
        glue = {"com.testing.bdd.stepdefs"})
public class RunCucumberTest {
}
