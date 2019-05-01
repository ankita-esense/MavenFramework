package com.topscorer.testcases;

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

import okhttp3.internal.cache.DiskLruCache.Snapshot;


public class loginCheck extends PageObjects {
	
	
	//private static final UtilityMethods seleniumUtil = null;

	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browserName) {
		seleniumUtil.openBrowser(browserName);
	}

	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()){
			
			snapShot(System.getProperty("user.dir")+"\\Screenshots\\Login\\",result.getName());
		}else {
			
			driver.close();
		}
	}
	
	@Parameters({ "projectURL" })
	@Test
	public void TC01_To_verify_login_functionality(String projectURL)
	{
		SoftAssert softAssert = new SoftAssert(); 
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.enterText(loginpage.txtEmail,"1329752357");
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(loginpage.btnNext);
		seleniumUtil.enterText(loginpage.txtPassword,"admin@123");
		seleniumUtil.click(loginpage.btnLogin);
		
	}
	
	
	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_login_having_multipleRole_functionality(String projectURL)
	{
		SoftAssert softAssert = new SoftAssert(); 
		seleniumUtil.openURL(projectURL);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.enterText(loginpage.txtEmail,"1075041527");
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(loginpage.btnNext);
		seleniumUtil.enterText(loginpage.txtPassword,"admin@123");
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(loginpage.btnLogin);
		seleniumUtil.pageLoadTime();
		String strgetrolemsg = seleniumUtil.getText(loginpage.lblselectrole);
		Assert.assertEquals(strgetrolemsg, "SELECT YOUR ROLE");
		
	}
	
}
