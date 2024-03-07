package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"html:target/cucumber.html", "json:target/report.json"},
            features = "src/test/resources/features",
            glue = "steps",
            tags = "@bankaccount",
            dryRun = true// make it true if u wanna run dry to get implementation for steps
    )
    public class CukesRunner {

}
