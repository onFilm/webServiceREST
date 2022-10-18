package commonUtils;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.Scenario;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestLogger {
    static final ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> LoggerFactory.getLogger(TestLogger.class));
    private static final String CURRENT_SCENARIO = "currentScenario";
    static ThreadLocal<Map<String, Object>> storeCurrentScenario = ThreadLocal.withInitial(ConcurrentHashMap::new);
    private static final ThreadLocal<Boolean> failure = ThreadLocal.withInitial(() -> false);

    public static void addInfo(String message) {
        Scenario scenario = getCurrentScenario();
        if (scenario != null) {
            try {
                scenario.log(message);
            } catch (Exception e) {
                // ignore
            }
        }
    }

    public static void addError(String message) {
        Scenario scenario = getCurrentScenario();
        if (scenario != null) {
            try {
                scenario.log(message);
                failure.set(true);
            } catch (Exception e) {
                // ignore
            }
        }
    }

    public static void setTestCase(Scenario scenario) {
        storeCurrentScenario.get().put(CURRENT_SCENARIO, scenario);
        failure.set(false);
    }

    public static Scenario getCurrentScenario() {
        return (Scenario) storeCurrentScenario.get().get(CURRENT_SCENARIO);
    }

    public static void endTestCase(String scenarioName) {
        logger.get().info(() -> scenarioName + " execution completed");
        storeCurrentScenario.get().clear();
    }

    public static boolean getFailureStatus() {
        return failure.get();
    }
}
