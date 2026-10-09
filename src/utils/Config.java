package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class Config {

    private static final Properties properties = new Properties();
    private static final Path configDirectory = Paths.get(System.getProperty("user.dir"), "Temp");

    private static final Path configFile = configDirectory.resolve("config.properties");

    static {
        load();
    }

    public static void load() {

        try (InputStream input = Files.newInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            save();
            //throw new RuntimeException("Could not load configuration file: " + configFile, e);
        }
    }

    public static void save() {
        try (OutputStream output = Files.newOutputStream(configFile)) {
            properties.setProperty("ip", properties.getProperty("ip", "localhost"));
            properties.setProperty("ticketPrinterName", properties.getProperty("ticketPrinterName", "Microsoft Print"));
            properties.setProperty("ticketCount", properties.getProperty("ticketCount", "0"));
            properties.setProperty("ticketWarning", properties.getProperty("ticketWarning", "500"));
            properties.setProperty("orderDifference", properties.getProperty("orderDifference", "15"));
            properties.setProperty("isPrintTicket", properties.getProperty("isPrintTicket", "True"));
            properties.store(output, "Application Configuration");
            load();
        } catch (IOException e) {
            throw new RuntimeException("Could not save configuration", e);
        }
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(
                    properties.getProperty(key)
            );
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(
                properties.getProperty(
                        key,
                        String.valueOf(defaultValue)
                )
        );
    }
}
