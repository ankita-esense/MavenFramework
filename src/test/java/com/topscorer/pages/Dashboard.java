package com.topscorer.pages;

import org.openqa.selenium.By;

public class Dashboard {

	public By ddlToggle_userName = By.xpath("//*[@id='top_nav']/li[4]/div/button | //*[@id='dropdownMenu1']");
	public By tab_MyCourses = By.xpath("//ul[@id='dboardTabs']//a[@href='#my-courses' and @data-toggle='tab']");
	public By tab_MyBookmarks = By.xpath("//ul[@id='dboardTabs']//a[@href='#my-bookmarks' and @data-toggle='tab']");
	public By tab_PastCourses = By.xpath("//ul[@id='dboardTabs']//a[@href='#past-courses' and @data-toggle='tab']");
	public By tab_PaidCourses = By.xpath("//div[@id='my-courses']//a[@href='#paid' and @data-toggle='tab']");
	public By tab_FreeCourses = By.xpath("//div[@id='my-courses']//a[@href='#free' and @data-toggle='tab']");
	public By lbl_list_CoursesBoradMedium = By.xpath("//div[@id='paid']//div[@class='dash-my-cou-web']//h4[@class='media-heading']/following-sibling::h6[1]");
	public By btn_list_paid_STUDY_NOW = By.xpath("//div[@id='paid']//div[@class='dash-my-cou-web']//button[text()='STUDY NOW']");
	public By btn_paid_STUDY_NOW_1 = By.xpath("(//div[@id='paid']//div[@class='dash-my-cou-web']//button[text()='STUDY NOW'])[1]");
	public By lbl_paid_CourseName_1 = By.xpath("(//div[@id='paid']//div[@class='dash-my-cou-web']//h4[@class='media-heading'])[1]");
	public By lbl_name_chapters_1_bm = By.xpath("(//div[@id='my-bookmarks']//div[contains(@class,'bmdiv')])[1]//h4[@class='media-heading']");
	public By lst_free_courses = By.xpath("//div[@id='free']//div[@class='dash-my-cou-web']//div[@class='row form-group']");
	public By btn_free_STUDY_NOW_1 = By.xpath("(//div[@id='free']//div[@class='dash-my-cou-web']//button[text()='STUDY NOW'])[1]");
	public By lbl_CoursesBoradMedium = By.tagName("h6");
	public By lbl_Subject_Name = By.className("media-heading");
	public By btn_Study_Now = By.xpath("//div[contains(@id,'free')]//button[text()='STUDY NOW']");
	public By icon_bag = By.xpath("//a[contains(@href,'mp/home')]");
	public By lnk_myProfile = By.partialLinkText("My Profile");
	public By lnk_logout = By.partialLinkText("Logout");
	public By lbl_testCount_1 = By.xpath("(//li[contains(text(),'Tests')])[1]");
}
