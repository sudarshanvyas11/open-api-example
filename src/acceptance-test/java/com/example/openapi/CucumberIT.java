package com.example.openapi;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/acceptance-test/resources",
        glue = "com.example.openapi"
)
public class CucumberIT {
}
