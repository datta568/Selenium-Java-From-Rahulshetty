package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "stepDefinitions", monochrome = true, plugin = {
		"html:reports/cucumber.html" }, tags="@regression")
public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
