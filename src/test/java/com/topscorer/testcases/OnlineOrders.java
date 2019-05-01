package com.topscorer.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PauseAction;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.topscorer.utilities.PageObjects;

public class OnlineOrders extends PageObjects {

	String strFileName_BuyModal = "./TestData/Buy Modal.xlsx";
	String strSheetName_online = "online";
	String[] cred,cred2;
	/*
	 * Skip test case of 6th, because of we have not any course it has 2000 price
	 * 
	 */

	@Parameters({ "browserName", "projectURL" })
	@BeforeMethod
	public void setUp(String browserName, String projectURL) throws Exception {
		seleniumUtil.openBrowser(browserName);
		seleniumUtil.openURL(projectURL);
		seleniumUtil.implicitLoadTime();
	}

	@AfterMethod
	public void postCondition(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()){
			snapShot(System.getProperty("user.dir")+"\\Screenshots\\OnlineOrders\\",result.getName());
		}else {
			driver.close();
		}
	}

	@Test
	public void TC01_To_verify_order_using_new_registration_having_percentage_coupon() throws IOException, Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 1);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 2);
		String strGrade = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 3);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		//seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		By btn_continue = By
				.xpath("//div[starts-with(@id,'cbse-')]//button");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
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

		cred = commonMethods.userRegistration_buy_modal();
		
		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 4);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 5);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		int G_total_price = seleniumUtil.getOnlyDigits(checkoutPage.lbl_grand_total_price);

		if (G_total_price <= 0)

		{
			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			seleniumUtil.implicitLoadTime();
		} else {

			commonMethods.LogInfo("Grand total price is greater than to 0. :" + G_total_price);

		}

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		softAssert.assertAll();

	}

	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_order_using_existing_registration(String projectURL) throws IOException, Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 6);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 7);

		commonMethods.userLogin(strUserEmailID, strUserPassword);
		Thread.sleep(3500);
		commonMethods.emptyCart(projectURL);
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 8);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 9);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.click(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		Thread.sleep(2500);
		if (seleniumUtil.isElementDisplayed(coursePage.btn_ok) == true) {
			seleniumUtil.clickByJavaScriptExecutor(coursePage.btn_ok);
		}
		seleniumUtil.implicitLoadTime();
		Thread.sleep(1500);
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 11);
		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
		Thread.sleep(1500);
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 11);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");

		String orderValuelPrice = seleniumUtil.getText(checkoutPage.lbl_order_value_price);
		String grandToatlPrice = seleniumUtil.getText(checkoutPage.lbl_grand_total_price);

		softAssert.assertNotSame(orderValuelPrice, grandToatlPrice,
				"After applying the promo code, it's not displaied amount in order summary.");
		seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_Proceed_to_Pay_Securely);

		Thread.sleep(2500);

		boolean btnOK1 = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK1 == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		boolean btnOK2 = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK2 == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}
		
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
		driver.switchTo().defaultContent();
		softAssert.assertAll();
	}

	@Test
	public void TC03_To_verify_buy_from_course_details_with_new_registration() throws Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 12);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 13);
		String strGrade = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 14);
		String strCourseName = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 15);

		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		//seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		By btn_continue = By
				.xpath("//div[starts-with(@id,'cbse-')]//button");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(3000);
		seleniumUtil.clickByJavaScriptExecutor(
				By.xpath("//div[contains(@class,'shortview')]//a/span[contains(text(),'" + strCourseName + "')]"));
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(subjectPage.btn_buy_for);
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

		cred2=	commonMethods.userRegistration_buy_modal();

		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 16);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 17);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		int G_total_price = seleniumUtil.getOnlyDigits(checkoutPage.lbl_grand_total_price);

		if (G_total_price <= 0)

		{
			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			seleniumUtil.implicitLoadTime();
		} else {

			commonMethods.LogInfo("Grand total price is greater than to 0. :" + G_total_price);

		}

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		softAssert.assertAll();

	}

	@Parameters({ "projectURL" })
	@Test
	public void TC04_To_verify_buy_from_course_details_with_existing_user(String projectURL)
			throws IOException, Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 18);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 19);
		System.out.println("cred2[0], cred2[1] "+cred2[0]+" "+ cred2[1]);
		commonMethods.userLogin(cred2[0], cred2[1]);
		Thread.sleep(3500);
		commonMethods.emptyCart(projectURL);
		seleniumUtil.click(dashboard.icon_bag);
		seleniumUtil.implicitLoadTime();

		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 20);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 21);
		String strCourseName = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 22);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.clickByJavaScriptExecutor(
				By.xpath("//div[contains(@class,'shortview')]//a/span[contains(text(),'" + strCourseName + "')]"));
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(subjectPage.btn_buy_for);
		//seleniumUtil.click(subjectPage.btn_ok);
		//seleniumUtil.implicitLoadTime();
		Thread.sleep(5000);
		seleniumUtil.doubleClick(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 23);
		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 24);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");

		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		int G_total_price = seleniumUtil.getOnlyDigits(checkoutPage.lbl_grand_total_price);

		if (G_total_price <= 0)

		{
			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			seleniumUtil.implicitLoadTime();
		} else {

			commonMethods.LogInfo("Grand total price is greater than to 0. :" + G_total_price);

		}

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		softAssert.assertAll();

	}

	@Test
	public void TC05_To_verify_order_using_new_registration_having_fixed_amount_coupon() throws Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 25);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 26);
		String strGrade = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 27);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[2]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][1]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[1]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(checkoutPage.btn_Register);
		Thread.sleep(2500);
		String getddlBoardValue = seleniumUtil.getDropdownSelectedValue(register.ddlBoard);
		String getddlGradeValue = seleniumUtil.getDropdownSelectedValue(register.ddlGrade);

		System.out.println("getddlBoardValue : "+getddlBoardValue);
		
		strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 3, 25);

		System.out.println("strBoard : "+strBoard);
		
		softAssert.assertEquals(getddlBoardValue.contains(strBoard), true,
				"On registration, board is not selected as per MP selection.");
		softAssert.assertEquals(getddlGradeValue, strGrade,
				"On registration, grade is not selected as per MP selection.");

		commonMethods.userRegistration_buy_modal();

		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 28);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 29);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		int G_total_price = seleniumUtil.getOnlyDigits(checkoutPage.lbl_grand_total_price);

		if (G_total_price <= 0)

		{
			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			seleniumUtil.implicitLoadTime();
		} else {

			commonMethods.LogInfo("Grand total price is greater than to 0. :" + G_total_price);

		}

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		softAssert.assertAll();

	}

	@Test
	public void TC07_To_verify_order_using_new_registration_having_fixed_cart_value_coupon() throws Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 36);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 37);
		String strGrade = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 38);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(checkoutPage.btn_Register);
		Thread.sleep(2500);
		String getddlBoardValue = seleniumUtil.getDropdownSelectedValue(register.ddlBoard);
		String getddlGradeValue = seleniumUtil.getDropdownSelectedValue(register.ddlGrade);

		strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 3, 36);

		softAssert.assertEquals(getddlBoardValue.contains(strBoard), true,
				"On registration, board is not selected as per MP selection.");
		softAssert.assertEquals(getddlGradeValue, strGrade,
				"On registration, grade is not selected as per MP selection.");

		commonMethods.userRegistration_buy_modal();

		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 39);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 40);
//		
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");
		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}

		int G_total_price = seleniumUtil.getOnlyDigits(checkoutPage.lbl_grand_total_price);

		if (G_total_price <= 0)

		{
			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			seleniumUtil.implicitLoadTime();
		} else {

			commonMethods.LogInfo("Grand total price is greater than to 0. :" + G_total_price);

		}

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		softAssert.assertAll();

	}

	@Parameters({ "projectURL" })
	@Test
	public void TC08_To_verify_order_using_existing_registration(String projectURL) throws IOException, Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 41);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 42);
		System.out.println("cred[0], cred[1] "+cred[0]+" "+ cred[1]);
		commonMethods.userLogin(cred[0], cred[1]);
		Thread.sleep(3500);
		commonMethods.emptyCart(projectURL);

		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 43);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 44);

		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		Thread.sleep(1500);
		if (seleniumUtil.isElementDisplayed(coursePage.btn_ok) == true) {
			seleniumUtil.clickByJavaScriptExecutor(coursePage.btn_ok);
		}
		seleniumUtil.implicitLoadTime();
		Thread.sleep(3500);
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(1500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 45);
		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
		Thread.sleep(1500);
//		String strGetPromoCodeMsg = seleniumUtil.getText(checkoutPage.lbl_pramocode_msg);
//		String strPromoCodeMsg = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 46);
//		softAssert.assertEquals(strPromoCodeMsg, strGetPromoCodeMsg,
//				"'Promo code has been successfully applied' message does not matched.");
		// String getGrandTotalPrice =
		// seleniumUtil.getText(checkoutPage.lbl_grand_total_price);
		
		String strGrandTotal = commonMethods.getPrice(checkoutPage.lbl_grand_total_price);
		float grandTotal = Float.parseFloat(strGrandTotal);

		if (grandTotal == 1) {

			seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
			Thread.sleep(2500);

			boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
			if (btnOK == true) {
				seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
			}

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

		} else {

			softAssert.assertEquals(grandTotal, 1,
					"After applying the promo code, it's not displaied amount in order summary.");
		}

		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test(dependsOnMethods={"TC01_To_verify_order_using_new_registration_having_percentage_coupon"})
	public void TC09_To_verify_fixed_cart_value_coupon_for_order_having_more_than_one_product(String projectURL)
			throws IOException, Exception {
		log.info("Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+"...");
		SoftAssert softAssert = new SoftAssert();
		String strUserEmailID = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 47);
		String strUserPassword = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 48);
		System.out.println("cred[0], cred[1] "+cred[0]+" "+ cred[1]);
		commonMethods.userLogin(cred[0], cred[1]);
		Thread.sleep(4000);
		commonMethods.emptyCart(projectURL);
		seleniumUtil.implicitLoadTime();
		String strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 49);
		String strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 50);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("(//a[starts-with(@href,'#gseb')])[3]");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(1000);
		seleniumUtil.mouseHover(tab_bord);
		Thread.sleep(1000);
		seleniumUtil.click(tab_bord);
		seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'gseb-')][2]/ul/li)[2]");
		By btn_continue = By
				.xpath("(//div[starts-with(@id,'gseb-')]//button)[2]");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(2000);
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		Thread.sleep(3000);
		if (seleniumUtil.isElementDisplayed(coursePage.btn_ok) == true) {
			seleniumUtil.clickByJavaScriptExecutor(coursePage.btn_ok);
		}
		seleniumUtil.implicitLoadTime();
		Thread.sleep(5000);
		// ------------------------------------	
		seleniumUtil.openURL(projectURL+"home");
		seleniumUtil.implicitLoadTime();
		Thread.sleep(5000);
		System.out.println("reloaded url : "+driver.getCurrentUrl());
		strBoard = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 51);
		strMedium = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 52);
		tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		waitForElementVisibile(By.xpath("//a[@href='#" + strBoard + "']"));
		Thread.sleep(3000);
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		//seleniumUtil.click(tab_bord_medium);
		lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		btn_continue = By
				.xpath("//div[starts-with(@id,'cbse-')]//button");
		seleniumUtil.click(lnk_grade_1);
		seleniumUtil.click(btn_continue);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(3000);
		waitForElementClickable(standard.btn_buy_mathematics);
		seleniumUtil.click(standard.btn_buy_mathematics);
		Thread.sleep(3000);
		if (seleniumUtil.isElementDisplayed(coursePage.btn_ok) == true) {
			seleniumUtil.clickByJavaScriptExecutor(coursePage.btn_ok);
		}
		// seleniumUtil.clickByJavaScriptExecutor(standard.online_buyModal);
		Thread.sleep(3000);
		if (seleniumUtil.isElementDisplayed(coursePage.btn_ok) == true) {
			seleniumUtil.clickByJavaScriptExecutor(coursePage.btn_ok);
		}
		seleniumUtil.implicitLoadTime();
		Thread.sleep(4000);
		fluentWait(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.clickByJavaScriptExecutor(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		Thread.sleep(4000);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_BuyModal, strSheetName_online, 1, 53);
		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);
		/*boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}*/
		Thread.sleep(4000);
		fluentWait(checkoutPage.lbl_popup_title);
		System.out.println("popup text : "+seleniumUtil.getText(checkoutPage.lbl_popup_title));
		if(!seleniumUtil.getText(checkoutPage.lbl_popup_title).equals("Standard Combo ONLINE is already Purchased. Got to Cart Page to remove it.")) 
		{
		softAssert.assertEquals(seleniumUtil.getText(checkoutPage.lbl_popup_title),
				"Given promoCode is valid only on single product.",
				"By clicking on proceed to payment, Paymentinformation tab is not open under payment option.");
		softAssert.assertAll();
		}
		
	}
}
