package BaseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	// Browser
	public static void browserLaunch(String value) {
		if (value.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			driver.manage().window().maximize();

		} else if (value.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notifications");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (value.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notification");
			driver = new EdgeDriver();
			driver.manage().window().maximize();

		} else {
			System.out.println("Invalid Browser");
		}

	}

	// Fluentwait locators
	public static void fillTextBoxByFW(String locName, String loc, String value) {

		FluentWait<WebDriver> FW = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		if (locName.equalsIgnoreCase("id")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.id(loc))).sendKeys(value);
		} else if (locName.equalsIgnoreCase("name")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.name(loc))).sendKeys(value);
		} else if (locName.equalsIgnoreCase("class")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);
		} else if (locName.equalsIgnoreCase("xpath")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc))).sendKeys(value);
		} else if (locName.equalsIgnoreCase("className")) {
			FW.until(ExpectedConditions.visibilityOfElementLocated(By.className(loc))).sendKeys(value);
		} else {
			System.out.println("Invalid Locators");
		}

	}

	/*
	 * public static WebElement locators(String value, String value1) { WebElement
	 * findElement = null; WebDriverWait wait = new WebDriverWait(driver, 100);
	 * 
	 * if (value.equalsIgnoreCase("Id")) {
	 * 
	 * findElement = driver.findElement(By.id(value1)); return findElement;
	 * 
	 * } else if (value.equalsIgnoreCase("Class")) { findElement =
	 * driver.findElement(By.className(value1)); return findElement;
	 * 
	 * } else if (value.equalsIgnoreCase("Name")) { findElement =
	 * driver.findElement(By.name(value1)); return findElement;
	 * 
	 * } else if (value.equalsIgnoreCase("xpath")) { findElement =
	 * driver.findElement(By.xpath(value1)); return findElement; }
	 * 
	 * else { System.out.println("Invalid locators"); } return findElement;
	 * 
	 * }
	 */

	public void fluWaitToFillText(WebElement element) {
		FluentWait<WebDriver> f = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		f.until(ExpectedConditions.visibilityOf(element));

	}

	// mdx
	public boolean fluWaitToFillTextBox(WebElement element, String text) {
		FluentWait<WebDriver> f = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		f.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		return true;

	}

	//
	public boolean fluentclick(WebElement wait) {
		FluentWait<WebDriver> fw = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);
		fw.until(ExpectedConditions.visibilityOf(wait)).click();
		return true;

	}
	
	private static void MouseOveraction() {
		WebElement ele = driver.findElement(By.partialLinkText("Change Source"));
		//Creating object of an Actions class
		Actions action = new Actions(driver);
		//Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();

	}

	// WebriverWait click

	/*
	 * public void webdriverwaitclick(By wait) { WebDriverWait webwait = new
	 * WebDriverWait(driver, 100);
	 * webwait.until(ExpectedConditions.visibilityOfElementLocated(wait)).click(); }
	 * 
	 * // WebriverWait sendkeys public void webdriverwaitsend(By wait, String
	 * sendkey) {
	 * 
	 * WebDriverWait webwait = new WebDriverWait(driver, 100);
	 * webwait.until(ExpectedConditions.visibilityOfElementLocated(wait)).sendKeys(
	 * sendkey);
	 * 
	 * }
	 */

	// Url
	public static void url(String url) {

		driver.get(url);
	}

	// maximize
	public static void max() {
		driver.manage().window().maximize();
	}

	// robot
	public static void Robot1() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	// Implicity wait
	public static void pageLoadWait() {
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}

	// send keys
	public static void enterText(WebElement findElement, String Value) {
		findElement.sendKeys(Value + Keys.ENTER);
	}

	// click
	public static WebElement click(WebElement a) {
		a.click();
		return a;

	}

	// current url
	public static String getcurrenturl() {

		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	// GetAttribute
	public static String Attribute(WebElement findElement) {
		String text1 = findElement.getAttribute("value");
		return text1;

	}

	// Gettext
	public static String AttributeText(WebElement findElement) {
		String text2 = findElement.getText();
		return text2;

	}

	// file uploaded
	public static void imageUploaded() throws Exception {

		String file = "";

		StringSelection selection = new StringSelection(file);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public static void textvaluesend() throws Exception {

		String file = "1:1 Appointment (In Person)";

		StringSelection selection = new StringSelection(file);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	// datadriven
	public static String excelRead(String filename, String sheetname, int rownum, int cellnum) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\RAJ\\eclipse-workspace\\MavenProjectForFrameExcel\\src\\test\\resources\\Excel\\" + filename
						+ ".xlsx");
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		String value = null;
		if (cellType == 1) {
			value = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			value = new SimpleDateFormat().format(cell.getDateCellValue());
		} else {
			value = String.valueOf((long) cell.getNumericCellValue());
		}
		return value;
	}

	public static void selectvisibilevalue(WebElement loc, String value) {

		Select select = new Select(loc);

		select.selectByValue(value);
	}

	public void scrollDown(int value) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + value + ")");

	}

	public void scrollup(int value) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + value + ")");

	}

	// scrollup and down
	public void scrollupdown() {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("(//div[contains(text(),' Characters left ')])[1]")));

	}

	public void scrolldown(By createticket) {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView()", driver.findElement(createticket));

	}

	public void scrollleft() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("document.querySelector(scroll).scrollLeft=1000");
	}

	public static void screenshot() throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File filesource = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("/home/bernath/NewEclipse/Projects/Edgehill/target");
		FileUtils.copyDirectory(filesource, dest);

	}

	public void datepicker() throws Exception {

		FluentWait<WebDriver> f = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);

		String month = "March";
		String year2 = "2023";
		String day2 = "5";

		f.until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate"))).click();
		// driver.findElement(By.id("startDate")).click();
		Thread.sleep(3000);

		while (true) {
			String month1 = driver.findElement(By.xpath("(//div[@class='xdsoft_label xdsoft_month'])[1]")).getText();
			System.out.println(month1);
			/*
			 * String month1 = f .until(ExpectedConditions .visibilityOf((WebElement)
			 * By.xpath("(//div[@class='xdsoft_label xdsoft_month'])[1]")))
			 * .getText().trim();
			 */

			String year1 = driver.findElement(By.xpath("(//div[@class='xdsoft_label xdsoft_year'])[1]")).getText();
			System.out.println(year1);
			/*
			 * String year1 = f .until(ExpectedConditions .visibilityOf((WebElement)
			 * By.xpath("(//div[@class='xdsoft_label xdsoft_year'])[1]")))
			 * .getText().trim();
			 */

			if (month1.equalsIgnoreCase(month) && year1.equalsIgnoreCase(year2))
				break;
			else
				// left arrow click
				/*
				 * f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				 * "(//button[@class='xdsoft_prev'])[1]"))) .click();
				 */
				// Right arrow click
				f.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='xdsoft_next'])[1]")))
						.click();
		}

		driver.findElement(By.xpath("//div[text()='" + day2 + "']")).click();
	}
 
	public static void test12() {
		
		String text = driver.findElement(By.xpath("//*[@class='p-lg']//child::div[1]")).getText();
		System.out.println("Test"+text);
		
	}
	

}
