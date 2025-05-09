package com.BasePackage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class SeleniumLogToFile {

    private static Logger logger = Logger.getLogger(SeleniumLogToFile.class.getName());
    private static PrintStream originalOut = System.out;
    private static PrintStream originalErr = System.err;

    public static void startLogging() {
        try {
        	System.out.println("SeleniumLogToFile logging started...");
            // Timestamp for file name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String logDir = "logs";
            new File(logDir).mkdirs(); // Create directory if needed
            String logFilePath = logDir + "/Console_log_" + timestamp + ".txt";

            // Set up PrintStream for System.out and System.err
            PrintStream fileOut = new PrintStream(new FileOutputStream(logFilePath, false));
            System.setOut(fileOut);
            System.setErr(fileOut);

            // Setup Java Logger to also log into the same file
            LogManager.getLogManager().reset(); // Remove default handlers
            logger.setLevel(Level.ALL);

            FileHandler fileHandler = new FileHandler(logFilePath, true); // append = true to share with System.out
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Log initial message
            logger.info("========== Selenium Test Log Started ==========");
            System.out.println("========== Console Log Started ==========");

        } catch (Exception e) {
            originalErr.println("Logging setup failed:");
            e.printStackTrace(originalErr);
        }
    }

    // Custom logging methods
    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }

    // Optional: restore original output
    public static void stopLogging() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}