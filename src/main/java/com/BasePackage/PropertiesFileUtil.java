package com.BasePackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
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
     * @throws IOException If the file cannot be written.
     */
    public void saveProperties(Map<String, String> updates) throws IOException {
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        FileOutputStream outputStream = new FileOutputStream(fileName);
        properties.store(outputStream, "Updated by PropertiesFileUtil");
        outputStream.close();
    }

    /**
     * Utility method to update multiple properties in a single call.
     *
     * @param fileName The name of the properties file.
     * @param updates  A map of key-value pairs to be updated.
     */
    public static void updateProperties(String fileName, Map<String, String> updates) {
        try {
            PropertiesFileUtil util = new PropertiesFileUtil(fileName);
            util.saveProperties(updates);
            System.out.println("Properties updated successfully: " + updates);
        } catch (IOException e) {
            System.err.println("Error updating properties: " + e.getMessage());
        }
    }
    
    public static Properties ReadFromPropertiesFile(String path) throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\" + path);
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

    public static void main(String[] args) {
        // Example usage of updating multiple properties
        String fileName = "CoreHOUserCredentials_CoreUserManagement_HO_User_Creation.properties";
        
        Map<String, String> updates = Map.of(
            "Athul", "Hari",
            "Reshma", "Poyil",
            "Maneesha", "Pattanath"
        );

        updateProperties(fileName, updates);
    }
}
