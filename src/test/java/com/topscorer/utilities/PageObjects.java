package com.topscorer.utilities;
import org.apache.log4j.Logger;
import com.topscorer.common.CommonMethods;
import com.topscorer.pages.*;
import com.topscorer.testcases.ForgotPassword;

public class PageObjects extends UtilityMethods {
	public static final Logger log = Logger.getLogger(PageObjects.class.getName());

	// ****************
	// Common Objects
	// ****************
	public static CommonMethods commonMethods = new CommonMethods();

	// ****************
	// Utilities Objects
	// ****************

	public static UtilityMethods seleniumUtil = new UtilityMethods();
	public static ForgotPassword forgotpwd = new ForgotPassword();
	public static ExcelUtils excelUtil = new ExcelUtils();

	// ****************
	// Page Objects
	// ****************
	public static Register register = new Register();
	public static ForgotPage forgotp = new ForgotPage();
	public static LoginPage loginpage = new LoginPage();
	public static TimesPage timespage = new TimesPage();
	public static ProfilePage profilepage = new ProfilePage();
	public static OffersPage offersPage = new OffersPage();
	public static CartPage cartPage = new CartPage();
	public static Dashboard dashboard = new Dashboard();
	public static Standard standard = new Standard();
	public static CoursePage coursePage = new CoursePage();
	public static HomePage homePage = new HomePage();
	//public static StudyPage studyPage = new StudyPage();
	public static CheckoutPage checkoutPage = new CheckoutPage();
	public static SubjectPage subjectPage = new SubjectPage();
	
	//public static PracticeTest practiceTest = new PracticeTest();
	//public static QuizPage quizPage = new QuizPage();
	//public static Analytics analytics = new Analytics();
	
}
