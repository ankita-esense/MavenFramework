package com.topscorer.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.topscorer.utilities.PageObjects;

public class TS05_My_Orders extends PageObjects {

	String strFileName_myOrders = "./TestData/My Orders.xlsx";
	String strSheetName_myOrders = "my_orders";
	String data[];
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
			snapShot(System.getProperty("user.dir")+"\\Screenshots\\MyOrders\\",result.getName());
		}else {
			driver.close();
		}
	}

	@Test
	public void TC01_To_verify_my_orders_tab_in_my_profile() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String strBoard = excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1, 1);
		String strMedium = excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1, 2);
		By tab_bord = By.xpath("//a[@href='#" + strBoard + "']");
		By tab_bord_medium = By.xpath("//a[@href='#" + strBoard + "-" + strMedium + "']");
		seleniumUtil.mouseHover(tab_bord);
		seleniumUtil.click(tab_bord);
		//seleniumUtil.click(tab_bord_medium);
		By lnk_grade_1 = By
				.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
		By btn_continue = By
				.xpath("//div[starts-with(@id,'cbse-')]//button");
		scrollToElementBy(tab_bord);
		seleniumUtil.click(lnk_grade_1);
		//scrollToElementBy(btn_continue);
		seleniumUtil.click(btn_continue);
		Thread.sleep(3000);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.clickByJavaScriptExecutor(standard.btn_buy_standardCombo);
		seleniumUtil.click(standard.online_buyModal);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(cartPage.btn_Proceed_To_Payment);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(checkoutPage.btn_Register);
		Thread.sleep(2500);
		data = commonMethods.userRegistration_buy_modal();

		Thread.sleep(2500);

		commonMethods.communication();
		Thread.sleep(2500);
		String strPromoCode = excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1, 3);

		seleniumUtil.enterText(checkoutPage.txt_promoCode, strPromoCode);
		seleniumUtil.click(checkoutPage.btn_apply);

		boolean btnOK = seleniumUtil.isElementDisplayed(checkoutPage.btn_ok);
		if (btnOK == true) {
			seleniumUtil.clickByJavaScriptExecutor(checkoutPage.btn_ok);
		}
		Thread.sleep(3000);
		seleniumUtil.click(checkoutPage.btn_Proceed_to_Pay_Securely);
		seleniumUtil.implicitLoadTime();

		boolean tabMyOrder = seleniumUtil.isElementDisplayed(profilepage.tab_active_MyOrders);

		softAssert.assertEquals(tabMyOrder, true,
				"After applying the promo code, page is not redirected to My orders page.");

		for (int i = 4; i <= 6; i++) {
			String expected_product_title = excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1,
					i);
			String actual_product_title = seleniumUtil.getText(profilepage.lbl_order_product);
			softAssert.assertTrue(actual_product_title.contains(expected_product_title),
					expected_product_title + " is not matched in product title label.");
		}

		softAssert.assertTrue(seleniumUtil.isElementDisplayed(profilepage.btn_study_now),
				"Study Now button is not appear.");

		String strPackageType = seleniumUtil.getText(profilepage.lbl_packageType);
		softAssert.assertTrue(
				strPackageType.contains(excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1, 7)),
				" Package Type dose not matched.");

		String strOrderNumber = seleniumUtil.getText(profilepage.lbl_orderNumber);
		log.info(strOrderNumber);
		String arrOfstrOrderNumber[] = strOrderNumber.split(":");
		strOrderNumber = arrOfstrOrderNumber[1].trim();
		log.info(strOrderNumber);
		softAssert.assertTrue(
				strOrderNumber
						.startsWith(excelUtil.getDataFromExcel(strFileName_myOrders, strSheetName_myOrders, 1, 8)),
				" Order No. is not start with TSW.");
		softAssert.assertAll();
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC02_To_verify_study_now_button_on_my_order_page(String projectURL) throws Exception {
		commonMethods.userLogin(data[0], data[1]);
		seleniumUtil.click(dashboard.ddlToggle_userName);
		seleniumUtil.click(dashboard.lnk_myProfile);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(profilepage.tab_MyOrders);
		Thread.sleep(1500);
		seleniumUtil.clickByJavaScriptExecutor(profilepage.btn_study_now);
		String strGetCurrentURL = seleniumUtil.getCurrentPageURL();
		Assert.assertTrue(strGetCurrentURL.contains("lms/dashboard"),
				"Page is not redirect to LMS page after click on Study Now button.");
	}

	@Parameters({ "projectURL" })
	@Test
	public void TC03_To_verify_rate_and_review_button_on_my_order_page(String projectURL) throws Exception {
		commonMethods.userLogin(data[0], data[1]);
		seleniumUtil.click(dashboard.ddlToggle_userName);
		seleniumUtil.click(dashboard.lnk_myProfile);
		seleniumUtil.implicitLoadTime();
		seleniumUtil.click(profilepage.tab_MyOrders);
		Thread.sleep(1500);
		seleniumUtil.click(profilepage.btn_rate_n_review);
		seleniumUtil.implicitLoadTime();
		Assert.assertTrue(seleniumUtil.isElementDisplayed(subjectPage.lbl_reviews),
				"Page is not redirect to Rate and Review dialog page.");

	}

}
