package com.orange;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com.orange.steps"},
    plugin = {
        "pretty",
        "json:target/cucumber-reports/Cucumber.json",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "junit:target/cucumber-reports/Cucumber.xml"
    },
    monochrome = true
)
public class RunCucumberTest {
}

