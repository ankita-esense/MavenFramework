package com.topscorer.pages;

import org.openqa.selenium.By;

public class OffersPage {

	
	public By txtFirstName = By.id("firstName");
	public By txtLastName = By.id("lastName");
	public By txtEmail = By.id("email");
	public By txtPassword = By.id("password");
	public By txtConfirmPassword = By.id("confirmPassword");
	public By txtMobile = By.id("mobile");
	public By chkTNC = By.id("termsAndCondition");
	public By btnRegister = By.id("btnPromRegister");
	
	public By tab_cbse = By.xpath("//ul[@id='board']//a[@href='#cbse']");
	public By lnkEnglishMedium = By.xpath("//a[@href='#cbse-em']");
	public By lblGrade_1 = By.xpath("(//div[starts-with(@id,'cbse-')]/ul/li)[1]");
	public By txtCoupon = By.xpath("//div[contains(@id,'cbse')]//input[contains(@name,'coupon')]");
	public By btnRegister_cbse = By.xpath("//*[@id='cbse']//div/button");
	public By formRegisterOTP = By.id("verifyRegisterOTP");

}
