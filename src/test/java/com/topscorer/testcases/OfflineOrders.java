package com.topscorer.testcases;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.topscorer.utilities.PageObjects;

public class OfflineOrders extends PageObjects {
	String strFileName_BuyModal = "./TestData/Buy Modal.xlsx";
	String strSheetName_off_line = "Off-line Orders";

	@Parameters({ "browserName", "projectURL" })
	@BeforeMethod
	public void setUp(String browserName, String projectURL) throws Exception {
		seleniumUtil.openBrowser(browserName);
		seleniumUtil.openURL(projectURL);
		seleniumUtil.implicitLoadTime();
	}

	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			snapShot(System.getProperty("user.dir")+"\\Screenshots\\OfflineOrdersQA\\",result.getName());
		}else {
			driver.close();
		}
	}

	@Test
	public void TC01_To_verify_offline_orders_using_new_registration() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 1);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 2);
		String strGrade = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 3);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		// seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		By btn_continue = By.xpath("//div[starts-with(@id,'cbse-')]//button");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.sd_buyModal);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(checkoutPage.btn_Register);
		Thread.sleep(2500);
		String getddlBoardValue = seleniumUtil.getDropdownSelectedValue(register.ddlBoard);
		String getddlGradeValue = seleniumUtil.getDropdownSelectedValue(register.ddlGrade);

		softAssert.assertEquals(getddlBoardValue.toLowerCase().contains(strBoard.toLowerCase()), true,
				"On registration, board is not selected as per MP selection.");
		softAssert.assertEquals(getddlGradeValue, strGrade,
				"On registration, grade is not selected as per MP selection.");

		commonMethods.userRegistration_buy_modal();

		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 4);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
		Thread.sleep(3000);
		// String strGetPromoCodeMsg =
		// seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
		// String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal,
		// strSheetName_online, 1, 5);
		//
		// softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
		// "'Promo code has been successfully applied' message does not matched.");
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.click(checkoutPage.btn_ok);
		}
		Thread.sleep(3000);
		seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);

		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Cash_On_Delivery),
				"Cash on delivery payment option is not appear after clicking on payment securely.");
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Online_Payment_through_CCAvenue),
				"Online Payment through CCAvenue payment option is not appear after clicking on payment securely.");

		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.rbtn_Cash_On_Delivery);

		seleniumUtil.click(checkoutPage.btn_Generate_OTP);

		Thread.sleep(10000);
		fluentWait(checkoutPage.txt_OTP);
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.txt_OTP),
				"OTP text box is not appear after clicking on Generate OTP.");
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.btn_Confirm_Order),
				"Confirm Orde button is not appear after clicking on Generate OTP.");
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_off_line_order_in_existing_user_using_coupons(String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 6);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 7);
		commonMethods.userLogin(strUserEmailID, strUserPassword);
		Thread.sleep(3500);
		commonMethods.emptyCart(projectURL);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonMethods.userLoggedOut();
		seleniumUtil.openURL(projectURL);
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 8);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 9);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.usb_buyModal);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2500);
		seleniumUtil.doubleClick(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		commonMethods.userLogin_at_checkOutPage(strUserEmailID, strUserPassword);
		Thread.sleep(3500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 10);
		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
		Thread.sleep(1500);
		// String strGetPromoCodeMsg =
		// seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
		// String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal,
		// strSheetName_online, 1, 11);
		//
		// softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
		// "'Promo code has been successfully applied' message does not matched.");

		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_Proceed_to_Pay_Securely);
		Thread.sleep(1500);
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Cash_On_Delivery),
				"Cash on delivery payment option is not appear after clicking on payment securely.");
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Online_Payment_through_CCAvenue),
				"Online Payment through CCAvenue payment option is not appear after clicking on payment securely.");

		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.rbtn_Online_Payment_through_CCAvenue);
		Thread.sleep(1500);
		String strBeforeGetCurrentUrl = driver.getCurrentUrl();
		driver.switchTo().frame("paymentFrame");
		boolean tabPaymentInfo = seleniumUtil.isElementDisplayed(checkoutPage.tab_paymentinformation);
		softAssert.assertEquals(tabPaymentInfo, true,
				"By clicking on Online Payment through CCAvenue, Paymentinformation tab is not open under payment option.");
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.tab_netBanking);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.rbtn_SBI);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_MakePayment_netBnk);
		seleniumUtil.switchToWindow();
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2500);
		softAssert.assertNotEquals(strBeforeGetCurrentUrl, driver.getCurrentUrl(),
				"Page is not redirect from merchant login page.");
		seleniumUtil.switchToDefaultFrame();
		driver.switchTo().defaultContent();
		softAssert.assertAll();

	}

	@Test
	public void TC03_To_verify_offline_order_using_the_new_registration_without_coupns() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 12);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 13);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		// seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		By btn_continue = By.xpath("//div[starts-with(@id,'cbse-')]//button");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.usb_buyModal);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(checkoutPage.btn_Register);
		Thread.sleep(2500);
		commonMethods.userRegistration_buy_modal();
		Thread.sleep(2500);
		commonMethods.communication();
		Thread.sleep(2500);

		seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);

		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Cash_On_Delivery),
				"Cash on delivery payment option is not appear after clicking on payment securely.");
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.rbtn_Online_Payment_through_CCAvenue),
				"Online Payment through CCAvenue payment option is not appear after clicking on payment securely.");
		Thread.sleep(1000);
		fluentWait(checkoutPage.rbtn_Cash_On_Delivery);
		seleniumUtil.click(checkoutPage.rbtn_Cash_On_Delivery);
		Thread.sleep(5000);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_Generate_OTP);
		Thread.sleep(5000);
		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.txt_OTP),
				"OTP text box is not appear after clicking on Generate OTP.");

		softAssert.assertTrue(seleniumUtil.isElementDisplayed(checkoutPage.btn_Confirm_Order),
				"Confirm Orde button is not appear after clicking on Generate OTP.");
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC04_To_verify_offline_orders_using_existing_registration_without_applying_any_coupons(
			String projectURL) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 16);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 17);

		commonMethods.userLogin(strUserEmailID, strUserPassword);
		Thread.sleep(3500);
		commonMethods.emptyCart(projectURL);
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 18);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_off_line, 1, 19);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.sd_buyModal);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(3500);
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(1500);
		seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.rbtn_Online_Payment_through_CCAvenue);
		Thread.sleep(1500);
		String strBeforeGetCurrentUrl = driver.getCurrentUrl();
		driver.switchTo().frame("paymentFrame");
		boolean tabPaymentInfo = seleniumUtil.isElementDisplayed(checkoutPage.tab_paymentinformation);
		softAssert.assertEquals(tabPaymentInfo, true,
				"By clicking on Online Payment through CCAvenue, Paymentinformation tab is not open under payment option.");

		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.tab_netBanking);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.rbtn_SBI);
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_MakePayment_netBnk);
		Thread.sleep(2500);
		seleniumUtil.switchToWindow();
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2500);
		softAssert.assertNotEquals(strBeforeGetCurrentUrl, driver.getCurrentUrl(),
				"Page is not redirect from merchant login page.");
		seleniumUtil.switchToDefaultFrame();
		softAssert.assertAll();
	}
}
