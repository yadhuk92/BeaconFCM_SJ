package com.BasePackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtil {

    private final String fileName;
    private final Properties properties;

    /**
     * Constructor to initialize the utility class.
     *
     * @param fileName The name of the properties file.
     * @throws IOException If the file cannot be loaded.
     */
    public PropertiesFileUtil(String fileName) throws IOException {
        this.fileName = "./src/test/resources/" + fileName;
        this.properties = new Properties();
        loadProperties();
    }

    /**
     * Loads the properties file into memory.
     *
     * @throws IOException If the file cannot be read.
     */
    private void loadProperties() throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);
        properties.load(inputStream);
        inputStream.close();
    }

    /**
     * Retrieves a property value by key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if not found.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Saves the updated properties back to the file.
     *
     * @param key   The property key.
     * @param value The property value.
     * @throws IOException If the file cannot be written.
     */
    public void saveProperty(String key, String value) throws IOException {
        properties.setProperty(key, value);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        properties.store(outputStream, "Updated by PropertiesFileUtil");
        outputStream.close();
    }

    /**
     * Utility method to update a property in a single call.
     *
     * @param fileName The name of the properties file.
     * @param key      The property key.
     * @param value    The property value.
     */
    public static void updateProperty(String fileName, String key, String value) {
        try {
            PropertiesFileUtil util = new PropertiesFileUtil(fileName);
            util.saveProperty(key, value);
            System.out.println("Property updated successfully: " + key + " = " + value);
        } catch (IOException e) {
            System.err.println("Error updating property: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage of the single-call utility method
        String fileName = "CoreHOUserCredentials_CoreUserManagement_HO_User_Creation.properties";
        String key = "Athul";
        String value = "Hari";

        updateProperty(fileName, key, value);
    }
}
