package com.topscorer.pages;

import org.openqa.selenium.By;

public class CoursePage {

	public By tab_practiceTest = By.xpath("//a[@href='#practice-test' and @data-toggle='tab']");
	public By tab_chapters = By.xpath("//a[@href='#chapters' and @data-toggle='tab']");
	public By tab_analytics = By.xpath("//a[@href='#analytics' and @data-toggle='tab']");
	public By tab_discuss = By.xpath("//a[@href='#discuss' and @data-toggle='tab']");
	public By lbl_CourseName = By.xpath("//div[@class='media']//h4[@class='media-heading']");
	public By list_chapters  = By.xpath("//div[@id='chapters']//div[@class='dash-my-cou-web']//div[@class='form-group']");
	public By btn_StudyNow_chapters_1= By.xpath("(//div[@id='chapters']//div[@class='dash-my-cou-web']//div[@class='form-group']//button[text()='Study'])[1]");
	public By btn_back_arrow = By.xpath("//div[@class='sub-back-arrow']//a[contains(@href,'lms')]");
	public By list_btn_Study_Now = By.xpath("//div[@id='chapters']//div[@class='dash-my-cou-web']//div[@class='form-group']//button[text()='Study']");
	public By btn_Go_To_Marketplace = By.xpath("//a[contains(text(),'Go To Marketplace')]");
	public By lbl_Subject_Name = By.className("media-heading");
	public By lbl_video_count_chapters_1 =  By.xpath("(//div[@id='chapters']//div[@class='dash-my-cou-web']//div[@class='form-group']//ul[@class='data-lessons']//li[@class='po-markup']/a)[1]");
	public By btn_ok = By.xpath("//button[text()='OK']");
	public By btn_start_new_practice_test = By.xpath("//div[@id='dpt-completed']//button[text()='Start New Practice Test']");
	public By btn_resume = By.xpath("//div[@class='dash-my-cou-web']//a[text()='Resume']");
	public By lbl_1st_test_inprogress = By.xpath("(//div[@id='dpt-inprogress']//div[@class='dash-my-cou-web']//h4[@class='media-heading']/b)[1]");
	public By tab_inProgress = By.xpath("//a[contains(@href,'-inprogress') and @data-toggle='tab']");
	public By tab_practice_test = By.xpath("//a[@href='#practiceTest' and @data-toggle='tab']");
	public By lbl_testCount_1 = By.xpath("(//div[@class='dash-my-cou-web']//ul//a)[2]");
	public By lbl_chapter_name_1 = By.xpath("(//div[@class='dash-my-cou-web']//h4)[1]");
	public By btn_resume_inprogress = By.xpath("//div[@id='tests-inprogress']//div[@class='dash-my-cou-web']//button[text()='Resume']");
	
}