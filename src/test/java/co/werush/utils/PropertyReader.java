package co.werush.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    private PropertyReader() {
        properties = new Properties();
        String rootPath = Objects.requireNonNull(getClass().getClassLoader().getResource("application.properties")).getPath();
        try {
            properties.load(new FileInputStream(rootPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Property file not found");
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read");
        }
    }

    public static Properties applicationProperties() {
        if (properties == null) {
            new PropertyReader();
        }
        return properties;
    }
}
