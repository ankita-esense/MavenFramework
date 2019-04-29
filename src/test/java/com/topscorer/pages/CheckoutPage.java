package com.topscorer.pages;

import org.openqa.selenium.By;

public class CheckoutPage {

	public By btn_Register = By.xpath("//a[text()='Register']");
	public By txt_promoCode = By.xpath("//input[@id='coupon']");
	public By btn_apply = By.id("btnCoupon");
	public By lbl_pramocode_msg = By.xpath("//div[@class='bootbox-body']");
	public By lbl_grand_total_price = By.xpath("//div[contains(text(),'Grand Total')]/following-sibling::div");
	public By lbl_order_value_price = By.xpath("//div[contains(text(),'Order Value')]/following-sibling::div");
	public By lbl_discounts_price = By.xpath("//div[contains(text(),'Discounts')]/following-sibling::div");
	public By lbl_shipping_price = By.xpath("//div[contains(text(),'Shipping')]/following-sibling::div");
	public By btn_Proceed_to_Pay_Securely = By.xpath("//button[contains(text(),'Proceed to Pay Securely')]");
	public By btn_ok = By.xpath("//button[text()='OK']");
	public By tab_paymentinformation = By.xpath("//div[@id='paymentinformation']");
	public By lbl_popup_title = By.xpath("//div[@class='bootbox-body']");
	public By rbtn_Cash_On_Delivery = By.xpath("//ul[@class='p_options']//li[text()='Cash On Delivery']/input");
	public By rbtn_Online_Payment_through_CCAvenue = By.xpath("//ul[@class='p_options']//li[text()='Online Payment through CCAvenue']/input");
	public By btn_Generate_OTP = By.xpath("//button[contains(.,'Generate OTP') and contains(@id,'makepayment')]");
	public By txt_OTP = By.xpath("//input[@id='verifyotp']");
	public By btn_Confirm_Order = By.xpath("//button[@id='placeorder']");
	public By btn_login = By.xpath("//a[text()='Login']");
	// NEW ADDRESS
	public By btn_add_address = By.xpath("//ul//li[@data-target='#add_address']//img");
	public By txt_full_name = By.id("person");
	public By txt_address_1 = By.id("line_one");
	public By txt_address_2 = By.id("line_two");
	public By txt_zip = By.id("zip");
	public By txt_phone = By.id("phone");
	public By ddl_country = By.id("country_id");
	public By ddl_state = By.id("state_id");
	public By ddl_city = By.id("city_id");
	public By btn_save_add = By.xpath("//div[@id='add_address']//input[@value='Save']");
	
	// Payment
	public By tab_netBanking = By.xpath("//li[@role='tab']/div[@id='OPTNBK']");
	public By rbtn_SBI = By.id("State Bank of India");
	public By btn_MakePayment_netBnk = By.xpath("(//a[@id='SubmitBillShip'])[3]");

}
