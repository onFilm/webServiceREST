package commonUtils.configUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesUtil {

    public static InputStream loadResource(String name) {
        InputStream in;
        try {
            in = Files.newInputStream(Paths.get(String.format("%s/src/main/resources/%s", System.getProperty("user.dir"), name)));
        } catch (IOException e) {
            // Couldn't load from disk, try in classpath ...
            if (ConfigLoader.class.getClassLoader().getResourceAsStream(String.format("/%s", name)) != null) {
                in = ConfigLoader.class.getClassLoader().getResourceAsStream(String.format("/%s", name));
            } else {
                in = PropertiesUtil.class.getResourceAsStream(String.format("/%s", name));
            }
        }
        if (in == null) {
            // Not found on disk or in class path
            throw new PropertyLoaderException("Failed to load resource: " + name);
        }
        return in;
    }

    public static Properties loadProperties(String name) {
        return loadProperties(loadResource(name));
    }

    public static Properties loadProperties(InputStream inputStream) {
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new PropertyLoaderException("Failed to load properties from input stream", e);
        }
        return prop;
    }
}
