package commonUtils.configUtils;

import commonUtils.JsonParser;
import commonUtils.constants.Environment;
import org.json.simple.JSONObject;

import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader configLoader;
    private final Properties properties;
    private final JSONObject userDetails;

    private ConfigLoader() {
        properties = loadConfigFile();
        userDetails = loadUserDetailsJson();
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public Properties loadConfigFile() {
        Properties properties;
        String environment = System.getProperty("environment", Environment.QA);
        switch (environment) {
            case "prod": {
                properties = PropertiesUtil.loadProperties("prod_config.properties");
                break;
            }
            case "int": {
                properties = PropertiesUtil.loadProperties("int_config.properties");
                break;
            }
            case "qa": {
                properties = PropertiesUtil.loadProperties("qa_config.properties");
                break;
            }
            default:
                throw new IllegalStateException("Invalid environment : " + environment);
        }
        return properties;
    }

    /**
     * Pass key to get value from Properties file
     *
     * @param key
     * @return String
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public JSONObject loadUserDetailsJson() {
        return JsonParser.jsonParser("user_details.json");
    }

    public String getUserName(String user) {
        return (String) ((JSONObject) userDetails.get(user)).get("username");
    }

    public String getPassword(String user) {
        return (String) ((JSONObject) userDetails.get(user)).get("password");
    }
}

