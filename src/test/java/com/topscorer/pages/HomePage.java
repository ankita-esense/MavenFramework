package com.topscorer.pages;

import org.openqa.selenium.By;

public class HomePage {

	public By btnLoginRegister = By.id("loginBtn");
	//public By tab_cbsc = By.xpath("//a[@href='#cbse']");
	public By tab_cbsc_em = By.xpath("//a[@href='#cbse-em']");
	public By lnk_grade_1 = By.xpath("(//div[@id='cbse-em']//ul[@class='grade-select']//li)[1]//input");
	public By btn_continue_cbsc_em = By.xpath("//div[@id='cbse-em']//button[contains(text(),'Continue')]");
	public By lbl_cartVal = By.xpath("//span[@class='cartVal badge']");
	public By img_cart = By.xpath("//li[@class='cart']//a[contains(@href,'/cart')]");
	public By img_logo = By.id("logo");
}
