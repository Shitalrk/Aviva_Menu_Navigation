package runners;
import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features", glue="stepDefinitions")
public class TestRunner {

}
