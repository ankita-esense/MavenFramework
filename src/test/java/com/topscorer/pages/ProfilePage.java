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
}
