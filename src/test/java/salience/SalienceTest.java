package salience;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
glue = {"dreamersweekend.salience.stepDefinitions","salience"},
features = "src/main/resources",
plugin = {"pretty", "html:target/cucumber-html-report"})
public class SalienceTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
