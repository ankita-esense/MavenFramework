package com.topscorer.pages;

import org.openqa.selenium.By;

public class TimesPage {
	public By btnSubscribeNow  = By.id("mySubscription1");
	
	public By txtFirstName  = By.id("firstName");
	public By txtLastName  = By.id("lastName");
	public By txtEmail  = By.id("email");
	public By txtMobile  = By.name("phone");
	public By txtSchoolName  = By.id("schoolName");
	public By ddlLanguage  = By.id("language");
	public By rbtnEcopy  = By.xpath("//input[@value='E Copy']");
	public By txtAddress1  = By.id("address1");
	public By txtAddress2  = By.id("address2");
	public By txtLandmark  = By.id("landmark");
	public By ddlCountry  = By.id("countryid");
	public By ddlState  = By.id("stateid");
	public By ddlCity  = By.id("cityid");
	public By txtPincode  = By.id("pincode");
	public By chkTNC  = By.id("termsAndCondition");
	public By btnSubscribe  = By.id("btnSubscribe");
	public By lblThanksText = By.xpath("//div[@id='thanks_text_e']//h3");
}
