package com.topscorer.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import com.topscorer.utilities.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonMethods extends PageObjects {
	public static String txtEmail;
	public static String txtPassword;
	public static String strFirstName;
	public static String txtLastName;
	public static String txtMobile;
	
	public void userRegistration(String strFileName) throws Exception {

		// User Registration
		// String strFileName = "./TestData/Registration.xlsx";
		String strSheetName = "Register";
		strFirstName = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 1);
		txtLastName = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 2);

		txtEmail = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 3);
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		//txtEmail = txtEmail + "_" + formatter.format(date) + "@mail.com";
		txtMobile = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 4);
		txtPassword = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 5);
		int mobileNumber = seleniumUtil.generateMobile();
		txtMobile = String.valueOf(mobileNumber);
		//LogInfo("New user: " + txtEmail);
		LogInfo("Password: " + txtPassword);
		LogInfo("User Mobile: " + txtMobile);

		String ddlBoard = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 6);
		String ddlGrade = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 7);

		seleniumUtil.enterText(register.txtFirstName, strFirstName);
		seleniumUtil.enterText(register.txtLastName, txtLastName);
		//seleniumUtil.enterText(register.txtEmail, txtEmail);
		seleniumUtil.enterText(register.txtMobile, txtMobile);
		seleniumUtil.enterText(register.txtPassword, txtPassword);
		seleniumUtil.enterText(register.txtConfirmPassword, txtPassword);

		if (driver.findElement(register.ddlBoard).isDisplayed()) {
			seleniumUtil.selectByText(register.ddlBoard, ddlBoard);
		} else {
			commonMethods.LogInfo("Board option is not available for this registration form.");
		}

		if (driver.findElement(register.ddlGrade).isDisplayed()) {
			seleniumUtil.selectByText(register.ddlGrade, ddlGrade);
		} else {
			commonMethods.LogInfo("Grade option is not available for this registration form.");
		}
		seleniumUtil.click(register.chkTNC);
		seleniumUtil.click(register.btnRegister);
		String otp = getOtp();
		seleniumUtil.enterText(register.txtOtp, otp);
		seleniumUtil.click(register.btnOtp);
		Thread.sleep(2500);
		try {
			Alert alert = driver.switchTo().alert();
			String alertMessage = driver.switchTo().alert().getText();
			commonMethods.LogInfo("Alert Message: " + alertMessage);
			alert.accept();
		} catch (Exception e) {
			commonMethods.LogInfo("No Alert required.");
		}
		seleniumUtil.pageLoadTime();
	}

	public void LogInfo(String strMessage) {
		log.info("***************************");
		log.info(strMessage);
		log.info("***************************");
	}
	
	public String getOtp() throws InterruptedException {
		Thread.sleep(2500);
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.get("https://www.topscorer.co.in/qa-2018/lms/ajax/printData");
	    String jsonbody = driver.findElement(By.xpath("/html/body")).getText();
	    System.out.println("otp: "+jsonbody.substring(92, 98));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    return jsonbody.substring(92, 98);
	}
}