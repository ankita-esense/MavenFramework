package com.topscorer.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
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

	public static boolean isOffers = false;
	
	@Parameters({ "browserName" })
	@BeforeMethod
	public void setUp(String browserName) {
		seleniumUtil.openBrowser(browserName);
	}

	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			snapShot(System.getProperty("user.dir") + "\\Screenshots\\Registration\\", result.getName());
			driver.close();
		} else {
			driver.close();
		}
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC01_To_verify_student_registrationusing_SignUP_functionality(String projectURL) throws Exception {
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
		softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),
				"Fail: Page is not redirect to LMS Dashboard.");
		expectedUserName = CommonMethods.strFirstName + "  " + CommonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'" + expectedUserName + "')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+", ""), expectedUserName.replaceAll("\\s+", ""),
				"Fail: User Name does't matched.");
		By boardMedium = By.xpath("//*[@id='free']/div[1]/div[1]/div/div/div[2]/h6[1]");
		String boardMediumtext = seleniumUtil.getText(boardMedium);
		if (!(boardMediumtext.toLowerCase().contains(commonMethods.ddlBoard.substring(0, 7).toLowerCase())
				&& boardMediumtext.toLowerCase().contains(commonMethods.ddlGrade.toLowerCase()))) {
			Assert.fail("board-medium does not matched!");
		}
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC06_To_verify_LMS_page_after_registration(String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();

		seleniumUtil.openURL(projectURL);
		seleniumUtil.implicitLoadTime();
		System.out.println(commonMethods.txtMobile);
		commonMethods.userLogin(commonMethods.txtMobile, commonMethods.txtPassword);
		Thread.sleep(5000);
		String strGetCurrentPageURL = seleniumUtil.getCurrentPageURL();
		softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),
				"Fail: Page is not redirect to LMS Dashboard.");
		expectedUserName = commonMethods.strFirstName + "  " + commonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'" + expectedUserName + "')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+", ""), expectedUserName.replaceAll("\\s+", ""),
				"Fail: User Name does't matched.");
		softAssert.assertAll();

	}

	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_student_registration_functionality_through_offers_page(String projectURL)
			throws Exception {
		SoftAssert softAssert = new SoftAssert();
		seleniumUtil.openURL(projectURL + "offers");
		seleniumUtil.implicitLoadTime();
		waitForElementClickable(offersPage.tab_cbse);
		seleniumUtil.click(offersPage.tab_cbse);
		scrollToElementBy(offersPage.tab_cbse);
		// seleniumUtil.click(offersPage.lnkEnglishMedium);
		waitForElementClickable(offersPage.lblGrade_1);
		seleniumUtil.click(offersPage.lblGrade_1);
		seleniumUtil.enterText(offersPage.txtCoupon, "QAP18");
		seleniumUtil.click(offersPage.btnRegister_cbse);
		Thread.sleep(2500);
		seleniumUtil.implicitLoadTime();
		isOffers= true;
		commonMethods.userRegistration(strFileName);
		Thread.sleep(10000);
		isOffers= false;		
		expectedUserName = CommonMethods.strFirstName + "  " + CommonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'" + expectedUserName + "')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+", ""), expectedUserName.replaceAll("\\s+", ""),
				"Fail: User Name does't matched.");
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC03_To_verify_student_registration_functionality_through_JEE(String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();

		seleniumUtil.openURL(projectURL + "jee-main");
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnGetStarted);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
		commonMethods.userRegistration(strFileName);
		Thread.sleep(5000);
		String strGetCurrentPageURL = seleniumUtil.getCurrentPageURL();
		softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),
				"Fail: Page is not redirect to LMS Dashboard.");
		expectedUserName = commonMethods.strFirstName + "  " + commonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'" + expectedUserName + "')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+", ""), expectedUserName.replaceAll("\\s+", ""),
				"Fail: User Name does't matched.");
		softAssert.assertAll();
	}

	/*
	 * @Parameters({ "projectURL" })
	 * 
	 * @Test public void
	 * TC04_To_verify_student_registration_functionality_through_Times(String
	 * projectURL) throws Exception { seleniumUtil.openURL(projectURL + "Times");
	 * seleniumUtil.pageLoadTime(); fluentWait(timespage.btnSubscribeNow);
	 * seleniumUtil.click(timespage.btnSubscribeNow); Thread.sleep(5000);
	 * seleniumUtil.enterText(timespage.txtFirstName, "Khushal");
	 * seleniumUtil.enterText(timespage.txtLastName, "Parikh"); SimpleDateFormat
	 * formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); Date date = new Date();
	 * String txtEmail = "automation" + "_" + formatter.format(date) + "@mail.com";
	 * seleniumUtil.enterText(timespage.txtEmail, txtEmail);
	 * seleniumUtil.enterText(timespage.txtMobile,
	 * String.valueOf(seleniumUtil.generateMobile()));
	 * seleniumUtil.enterText(timespage.txtSchoolName, "Test School");
	 * seleniumUtil.selectByText(timespage.ddlLanguage, "English");
	 * seleniumUtil.click(timespage.rbtnEcopy);
	 * seleniumUtil.enterText(timespage.txtAddress1, "Test Address 1");
	 * seleniumUtil.enterText(timespage.txtAddress2, "Test Address 2");
	 * seleniumUtil.enterText(timespage.txtLandmark, "Test Landmark");
	 * seleniumUtil.selectByText(timespage.ddlCountry, "India"); Thread.sleep(2000);
	 * seleniumUtil.selectByText(timespage.ddlState, "Gujarat"); Thread.sleep(2000);
	 * seleniumUtil.selectByText(timespage.ddlCity, "Ahmedabad");
	 * seleniumUtil.enterText(timespage.txtPincode, "380061");
	 * seleniumUtil.click(timespage.chkTNC);
	 * seleniumUtil.click(timespage.btnSubscribe);
	 * seleniumUtil.waitForElementVisibile(timespage.lblThanksText); String
	 * strGetThanksMsg = seleniumUtil.getText(timespage.lblThanksText);
	 * Assert.assertEquals(strGetThanksMsg,
	 * "Thanks For Your Interest In TIMES NEWSLETTER",
	 * "Thanks For Your Interest In TIMES NEWSLETTER lable not found"); }
	 */

	@Parameters({ "projectURL" })
	@Test
	public void TC05_To_verify_student_registratio_nfunctionality_through_10_standard(String projectURL)
			throws Exception {
		SoftAssert softAssert = new SoftAssert();
		System.out.println(projectURL);
		seleniumUtil.openURL(projectURL + "10/");
		seleniumUtil.pageLoadTime();
		seleniumUtil.scrollToElementBy(register.lblEnglish_cbse);
		seleniumUtil.clickByJavaScriptExecutor(register.lblEnglish_cbse);
		seleniumUtil.click(register.btn_try_coursePack_1);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
		commonMethods.userRegistration(strFileName);
		Thread.sleep(5000);
		seleniumUtil.implicitLoadTime();
		String strGetCurrentPageURL = seleniumUtil.getCurrentPageURL();
		softAssert.assertTrue(strGetCurrentPageURL.contains("lms/dashboard"),
				"Fail: Page is not redirect to LMS Dashboard.");
		expectedUserName = commonMethods.strFirstName + "  " + commonMethods.txtLastName;
		By username = By.xpath("//button[contains(.,'" + expectedUserName + "')]");
		String str_ddlToggle_userName = seleniumUtil.getText(username);
		softAssert.assertEquals(str_ddlToggle_userName.replaceAll("\\s+", ""), expectedUserName.replaceAll("\\s+", ""),
				"Fail: User Name does't matched.");
		By boardMedium = By.xpath("//*[@id='free']/div[1]/div[1]/div/div/div[2]/h6[1]");
		String boardMediumtext = seleniumUtil.getText(boardMedium);
		if (!(boardMediumtext.toLowerCase().contains(commonMethods.ddlBoard.substring(0, 7).toLowerCase())
				&& boardMediumtext.toLowerCase().contains(commonMethods.ddlGrade.toLowerCase()))) {
			Assert.fail("board-medium does not matched!");
		}
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC_07_ChangeMobileFunctionality(String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		seleniumUtil.openURL(projectURL);
		System.out.println(projectURL);
		seleniumUtil.click(register.btnLoginRegister);
		seleniumUtil.pageLoadTime();
		seleniumUtil.click(register.btnEmail);
		seleniumUtil.pageLoadTime();
		commonMethods.userRegistration(strFileName);
		Thread.sleep(5000);
		seleniumUtil.click(profilepage.ddlPrimary);
		seleniumUtil.click(profilepage.lnkProfile);
		pageLoadTime();
		Thread.sleep(2500);
		seleniumUtil.click(profilepage.lnkChangeMobile);
		Thread.sleep(5000);
		int mobileNumber = seleniumUtil.generateMobile();
		String txtMobile = String.valueOf(mobileNumber);
		seleniumUtil.enterText(profilepage.txtMobileNumber, txtMobile);
		seleniumUtil.click(profilepage.btnSubmit);
		Thread.sleep(2500);
		String otp = commonMethods.getOtp();
		seleniumUtil.enterText(profilepage.txtOtp, otp);
		seleniumUtil.click(profilepage.btnVerifyMobileNumber);
		Thread.sleep(2500);
		seleniumUtil.click(profilepage.btnOK);
		Thread.sleep(2500);
	}

}
