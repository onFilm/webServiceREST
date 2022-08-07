package commonUtils.configUtils;

import commonUtils.constants.Environment;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("environment", Environment.QA);
        switch (env) {
            case "qa": {
                    try {
                        properties = PropertiesUtil.loadProperties(getClass().getResourceAsStream("/qa_config.properties"));
                    }catch (Exception e) {
                        throw new IllegalStateException("Failed to load properties from Classpath");
                    }
                }
                break;
            case "prod": {
                    try {
                        properties = PropertiesUtil.loadProperties(getClass().getResourceAsStream("/prod_config.properties"));
                    }catch (Exception e) {
                        throw new IllegalStateException("Failed to load properties from Classpath");
                    }
                break;
            }
            default: throw new IllegalStateException("Invalid environment : " + env + ", please pass either qa or prod");
        }
    }
    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
