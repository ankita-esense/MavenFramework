package com.topscorer.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {
	public static WebDriver driver;

	@SuppressWarnings("deprecation")
	public void openBrowser(String browserName) {

		if (browserName.equals("edge")) {

			System.setProperty("webdriver.edge.driver", "drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.plugins", 1);
			prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			// Enable Flash for this site
			prefs.put("PluginsAllowedForUrls", "https://arlo.netgear.com");
			options.setExperimentalOption("prefs", prefs);
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void setBrowserDimension(int width, int hight) {

		System.out.println("Set browser dimension: " + width + "x" + hight);
		Dimension d = new Dimension(width, hight);
		driver.manage().window().setSize(d);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void openURL(String url) {
		System.out.println("Open url: " + url);
		driver.get(url);
		implicitLoadTime();
	}

	public void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void implicitLoadTime() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public void pageLoadTime() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	public List<WebElement> findWebElementListByXpath(By locator) {
		return driver.findElements(locator);
	}

	public List<WebElement> findWebElementListByXpath(String individualNpi) {
		return driver.findElements(By.xpath(individualNpi));
	}

	public void waitForElement(int milliSeconds) throws InterruptedException {
		Thread.sleep(milliSeconds);
	}

	public void mouseHover(By locatormouseHover) {
		WebElement mouseHover = driver.findElement(locatormouseHover);
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).build().perform();
	}

	public void mouseHoverAndClick(By locator) {
		WebElement mouseHover = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).click().build().perform();
		highlightElement(mouseHover);
	}

	public void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementVisibile(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElemenToBeClickableAndClick(By locator) {
		waitForElementClickable(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		webElement.click();
	}

	public void fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}

	public void selectByText(By locator, String visibleText) {
		waitForElementVisibile(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		Select sel = new Select(webElement);
		sel.selectByVisibleText(visibleText);
	}

	public void selectByValue(By locator, String value) {
		waitForElementVisibile(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		Select sel = new Select(webElement);
		sel.selectByValue(value);
	}

	public void selectByIndex(By locator, int index) {
		waitForElementVisibile(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		Select sel = new Select(webElement);
		sel.selectByIndex(index);
	}

	public String getSystemProperties(String propName) {
		return System.getProperty(propName);
	}

	public void tabKeyboard() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).perform();
	}

	public void switchToIFrame(String frameID) {
		driver.switchTo().frame(frameID);
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public void scrollDownJavaScript() {
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void highlightElement(WebElement webElement) {
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("arguments[0].style.border='2px solid red'", webElement);
	}

	public void scrollUPJavaScript() {
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollByXYPosition(String Xposition, String Yposition) {
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("window.scrollTo(" + Xposition + "," + Yposition + ")");
	}

	public void scrollToElementBy(By webElement) {
		WebElement ele = driver.findElement(webElement);
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void resetZoom() {
		JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("document.body.style.zoom = '100%'");
	}

	public WebElement findByXpath(By locator) {
		waitForElementPresent(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		return webElement;

	}

	public void click(By locator) {
		waitForElementVisibile(locator);
		WebElement webElement = findByXpath(locator);
		highlightElement(webElement);
		webElement.click();
	}
	
	public void doubleClick(By locator) {
		Actions action = new Actions(driver);
		waitForElementClickable(locator);
		WebElement webElement = findByXpath(locator);
		highlightElement(webElement);
		action.doubleClick(webElement).perform();
		
		}

	public void clickByJavaScriptExecutor(By locator) {
		waitForElementVisibile(locator);
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webElement);
	}

	public void clickByJavaScriptExecutor(WebElement webElement) {
		highlightElement(webElement);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webElement);
	}

	public void keyPress(Keys prKeys) {
		driver.findElement(By.tagName("body")).sendKeys(prKeys);
	}

	public void clear(By locator) {
		waitForElementPresent(locator);
		WebElement webElement = findByXpath(locator);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		highlightElement(webElement);
		webElement.clear();
	}

	public void enterText(By locator, String webElementValue) {
		waitForElementVisibile(locator);
		WebElement webElement = findByXpath(locator);
		try {
			Thread.sleep(1500);
			highlightElement(webElement);
			webElement.clear();
			webElement.sendKeys(webElementValue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void dragAndDrop(By from_locator, By to_locator) {
		WebElement From = driver.findElement(from_locator);
		WebElement To = driver.findElement(to_locator);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(From, To).build().perform();
	}

	public void snapShot(String path,String screenShotName) throws Exception {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
		Date date = new Date();
		String dt = formatter.format(date);
		FileUtils.copyFile(scrFile, new File(path + screenShotName + " " + dt + ".jpg"));

	}

	public String date() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String dt = formatter.format(date);
		return dt;
	}

	public boolean isElementDisplayed(By locator) {
		boolean isDisplayed = false;
		WebElement element;
		try {
			element = driver.findElement(locator);
			isDisplayed = element.isDisplayed();
			if (isDisplayed == true) {
				highlightElement(element);
				isDisplayed = true;
			} else {
				isDisplayed = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDisplayed;
	}

	// Get methods
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		String strAlert = alert.getText();
		return strAlert;
	}

	public String getAttributeValue(By locator, String Attribute) {
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		String strAttributeValue = webElement.getAttribute(Attribute);
		return strAttributeValue;
	}

	public String getElementCSSValue(By locator, String CssProperty) {
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		String strCssValue = webElement.getCssValue(CssProperty);
		return strCssValue;
	}

	public String getCurrentPageURL() {
		String strCurrentUrl = driver.getCurrentUrl();
		return strCurrentUrl;
	}

	public String getDropdownSelectedValue(By locator) {
		waitForElementVisibile(locator);
		Select webElement = new Select(driver.findElement(locator));
		String strDropdownValue = webElement.getFirstSelectedOption().getText().trim();
		return strDropdownValue;
	}

	public String getPageSource(String fileLocation) {

		// fileLocation= C:\\Users\\ujpatel\\Desktop\\

		String strPageSource = driver.getPageSource();

		try {
			FileWriter writer = new FileWriter(fileLocation + "PageSource.txt", true);
			writer.write(strPageSource);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strPageSource;
	}

	public String removeString(By locator) {
		String str = getText(locator);
		String strNew = str.replaceAll("([A-Za-z])", "").trim();
		return strNew;
	}

	public String removeString(WebElement element) {
		String str = element.getText();
		String strNew = str.replaceAll("([A-Za-z])", "").trim();
		return strNew;
	}

	public String getText(By locator) {
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		String strText = webElement.getText().trim();
		return strText;
	}

	public String getOnlyString(String strText) {
		// String str= getText(locator);
		strText = strText.replaceAll("[^-?A-Za-z ]+", "").trim();
		System.out.println("**********");
		System.out.print("Fianl String:" + strText);
		System.out.println("\n**********");
		return strText;
	}

	public Integer getOnlyDigits(By locator) {
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		String strText = webElement.getText().trim();
		strText = strText.replaceAll("[^-?0-9]+", "");
		int getDigits = Integer.parseInt(strText);
		System.out.println("**********");
		System.out.print("Fianl Digits:" + getDigits);
		System.out.println("\n**********");
		return getDigits;
	}

	public String getTableRowData(By locator, int cell_nos, int row_nos) {
		// Table raw and cell start with 1
		WebElement webElement = driver.findElement(locator);
		WebElement webElementCell = webElement.findElement(By.xpath(".//tr[" + cell_nos + "]"));
		WebElement webElementRow = null;
		try {
			webElementRow = webElementCell.findElement(By.xpath(".//td[" + row_nos + "]"));
		} catch (Exception e) {
			webElementRow = webElementCell.findElement(By.xpath(".//th[" + row_nos + "]"));
		}
		highlightElement(webElementRow);
		String strTableRowData = webElementRow.getText().trim();
		return strTableRowData;
	}

	public String getPageTitle() {

		String strPageTitle = driver.getTitle();
		return strPageTitle;
	}

	public String getValue(By locator) {
		WebElement webElement = driver.findElement(locator);
		highlightElement(webElement);
		String strValue = webElement.getAttribute("value");
		return strValue;
	}

	public void switchToWindow() {
		String currentWindowHandle = driver.getWindowHandle();
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String window : windowHandles) {
			if (window != currentWindowHandle) {
				driver.switchTo().window(window);
			}
		}
	}

	public void swithToDefaultWindow() {
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		if (windowHandles.size() > 1) {
			driver.close();
			driver.switchTo().defaultContent();
		}
	}

	public String getBrowserDetails() {

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String os = cap.getPlatform().toString();
		String v = cap.getVersion().toString();
		String getBrowserDetails = "Browser: " + browserName + " " + v + ", Operating System: " + os;
		return getBrowserDetails;

	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public int generatePin() throws Exception {
		// 6 digit random number
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());

		int num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
			num = generator.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999) {
				throw new Exception("Unable to generate PIN at this time..");
			}
		}
		return num;
	}

	public int generateMobile() throws Exception {
		// 10 digit random number
		int m = (int) Math.pow(10, 10 - 1);
		return m + new Random().nextInt(9 * m);
	}

	public Integer getElementCounts(By locator) throws Exception {
		List<WebElement> element = driver.findElements(locator);
		return element.size();
	} 

}
