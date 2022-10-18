package steps_definition;

import commonUtils.TestLogger;
import commonUtils.configUtils.ConfigLoader;
import commonUtils.context.GlobalVariables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BeforeAfter {

    private final GlobalVariables GlobalVariables;

    public BeforeAfter(GlobalVariables GlobalVariables) {
        this.GlobalVariables = GlobalVariables;
    }

    @After
    public static void terminate(Scenario scenario) {
        TestLogger.endTestCase(scenario.getName());
        if (TestLogger.getFailureStatus()) {
            throw new IllegalStateException("Error is observed. Please check the logs for this scenario");
        }
    }

    @Before
    public void setup(Scenario scenario) {
        new GlobalVariables();
        TestLogger.setTestCase(scenario);
        GlobalVariables.config = ConfigLoader.getInstance();
        TestLogger.addInfo(String.format("Thread id : %d and Scenario : %s%n", Thread.currentThread().getId(), scenario.getName()));
    }
}
