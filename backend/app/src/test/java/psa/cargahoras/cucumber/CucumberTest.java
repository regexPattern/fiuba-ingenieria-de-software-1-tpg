package psa.cargahoras.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "psa.cargahoras.cucumber",
    plugin = {"pretty", "html:build/reports/cucumber/test/index.html"})
public class CucumberTest {}
