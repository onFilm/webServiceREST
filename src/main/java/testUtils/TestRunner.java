package testUtils;

import io.cucumber.core.cli.Main;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        glue = {"commonutils", "steps_definition"},
        plugin = {"pretty", "json:reports/cucumber-report.json", "junit:reports/cucumber-junit.xml"},
        features = "src/main/resources/",
        tags = "@test")
public class TestRunner extends AbstractTestNGCucumberTests {

    public static void main(String[] args) {
        System.setProperty("CUCUMBER_PUBLISH_ENABLED", "true");
        String tags = System.getProperty("tags", "@test");
        System.out.println("Tags: " + tags);
        Main.main(
                "--plugin", "pretty",
                "--plugin", "html:reports/index.html",
                "--glue", "steps_definition",
                "--glue", "commonutils",
                "--tags", tags,
                "--monochrome",
                "classpath:features");
    }

    @DataProvider
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}