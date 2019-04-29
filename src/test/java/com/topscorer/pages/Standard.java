package com.topscorer.pages;

import org.openqa.selenium.By;

public class Standard {

	public By lblEnglish_cbse = By.xpath("//label[@class='cbse-lbl']/following-sibling::ul//a[text()='English']");
	public By btn_try_coursePack_1 = By.xpath("(//ul[@class='pack-bt']//a[contains(text(),'TRY')])[1]");
	public By btn_buy_standardCombo = By.xpath("//div[contains(@class,'shortview')]//div//span[text()='Standard Combo']//parent::a//parent::div//following-sibling::ul//a[contains(text(),'BUY')]");
	public By btn_buy_mathematics = By.xpath("(//a[contains(.,'BUY')])[1]");
	public By online_buyModal = By.xpath("//div[@id='buyModal' and @aria-hidden='false']//h3[contains(text(),'ONLINE')]");
	public By usb_buyModal = By.xpath("//div[@id='buyModal' and @aria-hidden='false']//h3[contains(text(),'USB')]");
	public By sd_buyModal = By.xpath("//div[@id='buyModal' and @aria-hidden='false']//h3[contains(text(),'SD')]");
}
