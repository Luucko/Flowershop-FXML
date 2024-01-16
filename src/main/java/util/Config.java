package util;

import util.exceptions.FlowershopException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    private static final Config INSTANCE = new Config();

    private static final String PATH = "/config/config.properties";
    private final Properties properties = new Properties();

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = getClass().getResourceAsStream(PATH)) {
            properties.load(is);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to read config from properties file.", ex);
            throw new FlowershopException("Unable to load application configuration.");
        }
    }

    public String readSetting(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String readSetting(String key) {
        return readSetting(key, null);
    }

    public void writeSetting(String key, String value) {
        properties.setProperty(key, value);
        savePropertiesFile();
    }

    private void savePropertiesFile() {
        try (OutputStream fos = new FileOutputStream(PATH)) {
            properties.store(fos, "");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to write config to properties file.", ex);
            throw new FlowershopException("Unable to store application configuration.");
        }
    }


}
