package com.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	
	private WebDriver driver;
	private SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_HHmmss");  
	private Date date= new Date();
	 private static String reportFilepath = System.getProperty("user.dir") + "\\ScreenShots\\";
     private static String reportFileLocation =  reportFilepath;
	public ScreenShot(WebDriver driver)
	{
		this.driver=driver;
	}

	public File takeScreenShot(String fileName, String device) throws IOException
	{
		String filPath = getScreenShotPath(reportFilepath);
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(filPath+fileName+"_"+device+"_"+formatter.format(date)+".png");
		FileHandler.copy(SrcFile, DestFile);
		return DestFile;
	}
	
	public File takeScreenShot(String fileName) throws IOException
	{
		String filPath = getScreenShotPath(reportFilepath);
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(filPath+fileName+"_"+formatter.format(date)+".png");
		FileHandler.copy(SrcFile, DestFile);
		return DestFile;
	}
	
	 private static String getScreenShotPath (String path) {
	    	File testDirectory = new File(path);
	        if (!testDirectory.exists()) {
	        	if (testDirectory.mkdir()) {
	                System.out.println("Directory: " + path + " is created!" );
	                return reportFileLocation;
	            } else {
	                System.out.println("Failed to create directory: " + path);
	                return System.getProperty("user.dir");
	            }
	        } else {
	            System.out.println("Directory already exists: " + path);
	        }
			return reportFileLocation;
	    }
}
