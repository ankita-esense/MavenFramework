package com.topscorer.pages;

import org.openqa.selenium.By;

public class ProfilePage {

	public By ddlPrimary =  By.xpath("//*[@id='top_nav']//button[contains(@class,'btn btn-primary dropdown-toggle')]");
	public By lnkProfile = By.xpath("//a[contains(.,'My Profile')]");
	public By lnkChangeMobile = By.xpath("//a[contains(.,'Change Mobile')]");
	public By txtMobileNumber = By.xpath("(//input[contains(@id,'phone')])[2]");
	public By btnSubmit = By.id("modal-btn-update");
	public By txtOtp = By.id("otp");
	public By btnVerifyMobileNumber = By.id("modal-btn-verify");
	public By btnOK = By.id("modal-btn-change-mobile");
	public By tab_active_MyOrders = By.xpath("//li[@class='active']//a[@href='#courses' and @data-toggle='tab']");
	public By tab_MyOrders = By.xpath("//a[@href='#courses' and @data-toggle='tab']");
	public By lbl_order_product = By.xpath("//div[@class='order-product']/span");
	public By btn_study_now = By.xpath("//a[text()='Study Now']");
	public By lbl_packageType = By.xpath("(//div[@class='order-data'])[4]");
	public By lbl_orderNumber = By.xpath("//div[contains(text(),'ORDER') and @class='order-data']");
	public By btn_rate_n_review = By.xpath("(//div[@class='rating-order'])[1]");
	
}
