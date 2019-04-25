package com.topscorer.pages;

import org.openqa.selenium.By;

public class Register {
	//from Login Page
	public By btnLoginRegister = By.id("loginBtn");
	//From Home Page
	public By btnEmail = By.xpath("//a[@class='email-reg_action' and text()='Mobile']");
	//From Dashboard Page
	public By ddlToggle_userName = By.xpath("//button[@id='dropdownMenu1' or contains(@class,'dropdown-toggle')]");
	//From Jee Page
	public By btnGetStarted = By.xpath("//button[text()='Get Started']");
	//From Standards Page
	public By lblEnglish_cbse = By.xpath("//label[@class='cbse-lbl']/following-sibling::ul//a[text()='English']");
	public By btn_try_coursePack_1 = By.xpath("(//ul[@class='pack-bt']//a[contains(text(),'TRY')])[1]");
	//From Register Page
	public By txtFirstName = By.name("firstName");
	public By txtLastName = By.name("lastName");
	public By txtMobile = By.name("phone");
	public By txtPassword = By.xpath("//input[@id='regPassword'] | //input[@id='password']");
	public By txtConfirmPassword = By.xpath("//form[@id='newRegister']//input[@name='confirmPassword'] | //input[@id='confirmPassword']");
	public By ddlBoard = By.name("board");
	public By ddlGrade = By.name("standard");
	public By chkTNC = By.id("termsAndCondition");
	public By btnRegister = By.xpath("//button[text()='Register']");
	public By lnkTerms = By.id("term_condition");
	
	//From Otp Page
	public By txtOtp = By.id("otp");
	public By btnOtp = By.id("btnVerifyMobile");
	
	
}
