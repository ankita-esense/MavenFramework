package com.topscorer.common;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;

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

	public void userLogin(String userEmail, String userPassword) throws Exception {

		if (seleniumUtil.isElementDisplayed(register.btnLoginRegister) == true) {

			seleniumUtil.click(register.btnLoginRegister);
			seleniumUtil.implicitLoadTime();
			seleniumUtil.enterText(loginpage.txtEmail, userEmail);
			seleniumUtil.click(loginpage.btnNext);
			seleniumUtil.enterText(loginpage.txtPassword, userPassword);
			seleniumUtil.click(loginpage.btnLogin);
			Thread.sleep(2500);
			try {
				Alert alert = driver.switchTo().alert();
				String alertMessage = driver.switchTo().alert().getText();
				commonMethods.LogInfo("Alert Message: " + alertMessage);
				alert.accept();
			} catch (Exception e) {
				commonMethods.LogInfo("No Alert required.");
			}
			seleniumUtil.implicitLoadTime();
		} else {
			log.info("User is logged In");
		}
	}
	
	public String userRegistration(String strFileName) throws Exception {
		// User Registration
		// String strFileName = "./TestData/Registration.xlsx";
		String txtMobile = fillInputFields(strFileName);
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
		return txtMobile;
	}

	public String fillInputFields(String strFileName) throws Exception {
		String strSheetName = "Register";
		strFirstName = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 1);
		txtLastName = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 2);

		txtEmail = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 3);
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		// txtEmail = txtEmail + "_" + formatter.format(date) + "@mail.com";
		txtMobile = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 4);
		txtPassword = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 5);
		int mobileNumber = seleniumUtil.generateMobile();
		txtMobile = String.valueOf(mobileNumber);
		// LogInfo("New user: " + txtEmail);
		LogInfo("Password: " + txtPassword);
		LogInfo("User Mobile: " + txtMobile);

		String ddlBoard = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 6);
		String ddlGrade = excelUtil.getDataFromExcel(strFileName, strSheetName, 1, 7);

		seleniumUtil.enterText(register.txtFirstName, strFirstName);
		seleniumUtil.enterText(register.txtLastName, txtLastName);
		// seleniumUtil.enterText(register.txtEmail, txtEmail);
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
		return txtMobile;
	}

	public void LogInfo(String strMessage) {
		log.info("***************************");
		log.info(strMessage);
		log.info("***************************");
	}

	public String getOtp() throws InterruptedException {
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.get("https://www.topscorer.co.in/qa-2018/lms/ajax/printData");
		String jsonbody = driver.findElement(By.xpath("/html/body")).getText();
		
		Pattern p = Pattern.compile("otp\"\\:([0-9]*)");
        Matcher m = p.matcher(jsonbody);
        while(m.find()) {
            if(m.group().length()!=0) {
            	jsonbody = m.group();
            	break;
            }
        }
		
		System.out.println("otp: " + jsonbody);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		return jsonbody.substring(5, 11);
	}

	public void LoadEmailPage(String projectURL) {
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
	}

	public void CheckRequiredAttribute(String atr, By[] listOfElements) {
		for (int i = 0; i < listOfElements.length; i++)
			if (atr == "required") {
				if (driver.findElement(listOfElements[i]).getAttribute(atr) == null) {
					System.out.println(atr + " validation is missing from " + listOfElements[i] + " element.");
					Assert.fail(atr + " validation is missing from " + listOfElements[i] + " element.");
				} else {
					java.lang.reflect.Field[] x = listOfElements.getClass().getDeclaredFields();
					System.out.println(atr + " validation is available in " + listOfElements[i] + " element.");
				}
			}
			else if(i==0||i==2||i==3){
				if (driver.findElement(listOfElements[i]).getAttribute(atr) == null) {
					System.out.println(atr + " validation is missing from " + listOfElements[i] + " element.");
					Assert.fail(atr + " validation is missing from " + listOfElements[i] + " element.");
				} else {
					java.lang.reflect.Field[] x = listOfElements.getClass().getDeclaredFields();
					System.out.println(atr + " validation is available in " + listOfElements[i] + " element.");
				}
			}
	}
}
