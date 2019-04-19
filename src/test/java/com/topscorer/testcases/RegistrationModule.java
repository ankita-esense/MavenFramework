package com.topscorer.testcases;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.topscorer.common.CommonMethods;
import com.topscorer.utilities.*;
public class RegistrationModule extends PageObjects {
	
	String strFileName = "./TestData/Registration.xlsx";
	String strSheetName = "Register";
	String expectedUserName;
	
	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browserName) {
		seleniumUtil.openBrowser(browserName);
	}
	
	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()){
			snapShot(System.getProperty("user.dir")+"\\Screenshots\\Registration\\",result.getName());
		}else {
			driver.close();
		}
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_student_registrationusing_SignUP_functionality(String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
		commonMethods.userRegistration(strFileName);
		Thread.sleep(5000);
		String strGetCurrentPageURL = seleniumUtil.getCurrentPageURL();
		softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),"Fail: Page is not redirect to LMS Dashboard.");
		expectedUserName = CommonMethods.strFirstName + "  " + CommonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'"+ expectedUserName +"')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+",""), expectedUserName.replaceAll("\\s+",""), "Fail: User Name does't matched.");
		softAssert.assertAll();
	}
}
