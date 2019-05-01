package com.topscorer.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.topscorer.common.CommonMethods;
import com.topscorer.pages.ForgotPage;
import com.topscorer.utilities.*;

public class ForgotPassword extends PageObjects  {
	
	
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
	public void TC01_To_verify_forgot_password_functionality(String projectURL) throws Exception {
		//SoftAssert softAssert = new SoftAssert();
		//seleniumUtil.openURL(projectURL);
		//seleniumUtil.pageLoadTime();
		//seleniumUtil.click(register.btnLoginRegister);
		//seleniumUtil.pageLoadTime();
		//seleniumUtil.click(register.btnEmail);
		//seleniumUtil.pageLoadTime();
		//commonMethods.userRegistration(strFileName);
		//Thread.sleep(5000);
		//String strGetCurrentPageURL = seleniumUtil.getCurrentPageURL();
		//softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),"Fail: Page is not redirect to LMS Dashboard.");
		//expectedUserName = CommonMethods.strFirstName + "  " + CommonMethods.txtLastName;
		//By username = By.xpath("//button[contains(.,'"+ expectedUserName +"')]");
		//String str_ddlToggle_userName = seleniumUtil.getText(username);
		//softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+",""), expectedUserName.replaceAll("\\s+",""), "Fail: User Name does't matched.");
		//softAssert.assertAll();
		
		SoftAssert softAssert = new SoftAssert(); seleniumUtil.openURL(projectURL);
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.click(forgotp.btnforgotpwd);
		seleniumUtil.pageLoadTime();
		seleniumUtil.enterText(forgotp.txtmobileno,"1075041527");
		//seleniumUtil.waitForElementVisibile(forgotp.drprole);
		By option2 = By.xpath("//*[@id='role']/option[2]");
		seleniumUtil.fluentWait(option2);
		seleniumUtil.click(forgotp.drprole);
		seleniumUtil.selectByIndex(forgotp.drprole,1);
		seleniumUtil.click(forgotp.btnnext);
		seleniumUtil.pageLoadTime();
		String actualMsg = driver.findElement(By.xpath("//font[@color='green']")).getText();
		//assertEquals(actualMsg, "Invalid One Time Password (OTP).");
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg,"Reset Password link sent to 1075041527.");
		
		
		
		
	}
	
	
	
	
}
