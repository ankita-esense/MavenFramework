package com.topscorer.testcases;

import static org.testng.Assert.assertEquals;

import org.eclipse.jetty.util.Fields.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.server.handler.ClearElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.topscorer.common.CommonMethods;
import com.topscorer.utilities.PageObjects;

public class RegValidationCheck extends PageObjects {
	String strFileName = "./TestData/Registration.xlsx";
	String strSheetNameValidations = "Validations";
	String strSheetNameReg = "Register";
	String expectedUserName;

	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browserName) {
		seleniumUtil.openBrowser(browserName);
	}

	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			snapShot(System.getProperty("user.dir") + "\\Screenshots\\RegistrationValidations\\", result.getName());
			driver.close();
		}else {
			driver.close();
		}
	}

	@AfterSuite
	public void closeBrowser() {
		seleniumUtil.quitBrowser();
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void VerifyRequiredFieldsValidation(String projectURL) throws Exception {
		commonMethods.LoadEmailPage(projectURL);
		By[] listOfElements = {register.txtFirstName,register.txtMobile,register.txtPassword,register.txtConfirmPassword,register.ddlBoard,register.ddlGrade,register.chkTNC};
		commonMethods.CheckRequiredAttribute("required",listOfElements);
	}

	@Parameters({ "projectURL" })
	@Test
	public void VerifyRequiredFieldsValidationForTimesPage(String projectURL) throws Exception {
		commonMethods.openURL(projectURL+"times");
		seleniumUtil.pageLoadTime();
		fluentWait(timespage.btnSubscribeNow);
		seleniumUtil.click(timespage.btnSubscribeNow);
		Thread.sleep(5000);
		By[] listOfElements = {timespage.txtFirstName,timespage.txtEmail,timespage.ddlCity,timespage.ddlLanguage,
				timespage.ddlState,timespage.ddlCountry,timespage.txtMobile,timespage.txtPincode,timespage.txtSchoolName};
		commonMethods.CheckRequiredAttribute("required",listOfElements);
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void VerifyMinMaxLengthValidation(String projectURL) throws Exception {
		commonMethods.LoadEmailPage(projectURL);
		By[] listOfElements = {register.txtFirstName,register.txtMobile,register.txtPassword,register.txtConfirmPassword,register.ddlBoard,register.ddlGrade,register.chkTNC};
		commonMethods.CheckRequiredAttribute("minlength",listOfElements);
		commonMethods.CheckRequiredAttribute("maxlength",listOfElements);
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void VerifyValidMobileNumber(String projectURL) throws Exception {
		//TODO add confirm password validation code
		commonMethods.LoadEmailPage(projectURL);
		seleniumUtil.enterText(register.txtFirstName, "khushal");
		seleniumUtil.enterText(register.txtLastName, "Parikh");
		seleniumUtil.enterText(register.txtMobile, "asdf");
		seleniumUtil.enterText(register.txtPassword, "123456");
		seleniumUtil.enterText(register.txtConfirmPassword, "1235467");
		if (driver.findElement(register.ddlBoard).isDisplayed()) {
			String ddlBoard = excelUtil.getDataFromExcel(strFileName, strSheetNameReg, 1, 6);
			seleniumUtil.selectByText(register.ddlBoard, ddlBoard);
		} else {
			commonMethods.LogInfo("Board option is not available for this registration form.");
		}

		if (driver.findElement(register.ddlGrade).isDisplayed()) {
			String ddlGrade = excelUtil.getDataFromExcel(strFileName, strSheetNameReg, 1, 7);
			seleniumUtil.selectByText(register.ddlGrade, ddlGrade);
		} else {
			commonMethods.LogInfo("Grade option is not available for this registration form.");
		}
		seleniumUtil.click(register.chkTNC);
		seleniumUtil.click(register.btnRegister);
		Thread.sleep(2500);

		String actualMsg = driver.findElement(By.id("regPhone-error")).getText();

		assertEquals(actualMsg, "Please enter a valid mobile number");
	}

	@Parameters({ "projectURL" })
	@Test
	public void VerifyExistingNumberAndConfirmPasswordValidation(String projectURL) throws Exception {
		commonMethods.LoadEmailPage(projectURL);
		seleniumUtil.enterText(register.txtFirstName, "khushal");
		seleniumUtil.enterText(register.txtLastName, "Parikh");
		seleniumUtil.enterText(register.txtMobile, "2255554465");
		seleniumUtil.enterText(register.txtPassword, "123456");
		seleniumUtil.enterText(register.txtConfirmPassword, "1234567");
		seleniumUtil.click(register.chkTNC);
		
		String actualMsg = driver.findElement(By.id("confirmPassword-error")).getText();
		assertEquals(actualMsg, "Password and confirm password not match");
		
		seleniumUtil.clear(register.txtMobile);
		seleniumUtil.enterText(register.txtMobile, "7405267784");
		seleniumUtil.clear(register.txtConfirmPassword);
		seleniumUtil.enterText(register.txtConfirmPassword, "123456");
		
		if (driver.findElement(register.ddlBoard).isDisplayed()) {
			String ddlBoard = excelUtil.getDataFromExcel(strFileName, strSheetNameReg, 1, 6);
			seleniumUtil.selectByText(register.ddlBoard, ddlBoard);
		} else {
			commonMethods.LogInfo("Board option is not available for this registration form.");
		}

		if (driver.findElement(register.ddlGrade).isDisplayed()) {
			String ddlGrade = excelUtil.getDataFromExcel(strFileName, strSheetNameReg, 1, 7);
			seleniumUtil.selectByText(register.ddlGrade, ddlGrade);
		} else {
			commonMethods.LogInfo("Grade option is not available for this registration form.");
		}
		seleniumUtil.click(register.chkTNC);
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(register.btnRegister);
		Thread.sleep(2500);

		String alertText = getAlertText();		
		assertEquals(alertText, "You already have an account associated with this mobile number. Do you want to reset the password?");
		
		dismissAlert();
		
		actualMsg = driver.findElement(By.id("registerError")).getText();
		assertEquals(actualMsg, "7405267784 already registered account.");
	}
	
	@Parameters({ "projectURL" })
	@Test(priority=1)
	public void TermsAndConditionsPopup(String projectURL) throws InterruptedException {
		commonMethods.LoadEmailPage(projectURL);
		seleniumUtil.click(register.lnkTerms);
		Thread.sleep(2000);
		By divTerms = By.id("terms");
		if(!isElementDisplayed(divTerms)) {
			Assert.fail("Terms And Conditions Popup Does not appered.");
		}
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void VerifyOtpValidations(String projectURL) throws Exception {
		commonMethods.LoadEmailPage(projectURL);
		commonMethods.fillInputFields(strFileName);
		Thread.sleep(2500);
		seleniumUtil.click(register.btnOtp);
		Thread.sleep(2500);
		String actualMsg = driver.findElement(By.id("verifyMobileError")).getText();
		assertEquals(actualMsg, "The OTP field is required.");
		seleniumUtil.enterText(register.txtOtp, "123456");
		seleniumUtil.click(register.btnOtp);
		Thread.sleep(2500);
		actualMsg = driver.findElement(By.id("verifyMobileError")).getText();
		assertEquals(actualMsg, "Invalid One Time Password (OTP).");
		
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void VerifyResendOtpFunctionality(String projectURL) throws Exception {
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
		commonMethods.userRegistrationForResendFunctionality(strFileName);
		Thread.sleep(5000);
	}
	
}
