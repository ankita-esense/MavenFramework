package com.topscorer.utilities;
import org.apache.log4j.Logger;

import com.topscorer.common.CommonMethods;
import com.topscorer.pages.*;

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
	public static ExcelUtils excelUtil = new ExcelUtils();

	// ****************
	// Page Objects
	// ****************
	public static Register register = new Register();
}
