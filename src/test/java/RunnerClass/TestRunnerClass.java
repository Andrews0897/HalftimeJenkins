package RunnerClass;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resource/FeatureFiles/login.feature",glue="StepDefinition", dryRun = false, monochrome = true)
public class TestRunnerClass {
	

}
