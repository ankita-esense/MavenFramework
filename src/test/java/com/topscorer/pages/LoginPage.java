package com.topscorer.pages;

import org.openqa.selenium.By;

public class LoginPage {
	public By txtEmail = By.id("emailId");
	public By btnNext = By.id("btnNext");
	public By txtPassword = By.name("password");
	public By btnLogin = By.id("btnLogin");
	public By lblselectrole = By.xpath("//h3[contains(text(),'Select your role')]");
}
