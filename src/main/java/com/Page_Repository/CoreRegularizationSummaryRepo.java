package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreRegularizationSummaryRepo {
	
	public static By dashboardbutton = By.xpath("//button[@title='Dashboard']");
	public static By RegularizationSummary = By.xpath("//a[@title='Regularization Summary']");
	public static By SelectCallCentredropdown = By.xpath("//button[text()='Search']//preceding-sibling::div");
	public static By searchbutton = By.xpath("//button[text()='Search']");
	public static By validationMessage = By.xpath("//p[text()='Please Select CallCentre']");
	public static By Regularizationsummaryreport = By.xpath("//tr[@class='rz-datatable-even  ']//parent::tbody");
	public static By downloadbtn = By.xpath("//h6[contains(text(),'REGULARIZATION SUMMARY')]//following-sibling::i");
	public static By downloadedMessage = By.xpath("//p[text()='File downloaded successfully']");
	public static By TOTAL_AC_RECEIVEDcount = By.xpath("//tbody//tr//td[2]");
	public static By downloadbtn2 = By.xpath("(//span[@class='rz-cell-data'])[7]");
	public static By Dashboard = By.xpath("//span[text()='Dashboard']");
	public static By RegularizationSumary = By.xpath("//a[text()='Regularization Summary']");
	public static By nextbutton = By.xpath("//span[@class='rz-paginator-icon rzi rzi-caret-right']");
	public static By pagescount = By.xpath("//span[@class='rz-paginator-pages']//a");
}
