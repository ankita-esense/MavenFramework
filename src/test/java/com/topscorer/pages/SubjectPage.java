package com.topscorer.pages;

import org.openqa.selenium.By;

public class SubjectPage {
	
	public By btn_buy_for = By.xpath("//button[@class='buyOnline']");
	public By btn_ok = By.xpath("//button[text()='OK']");
	public By lbl_reviews = By.xpath("//div[@class='rating' and contains(text(),'Review')]");
	
}
