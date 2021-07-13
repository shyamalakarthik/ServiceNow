package UtilisComponent;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Supporter {
	public static WebDriver driver;
	public static String Userdirectory;
	public static ExtentHtmlReporter html;
	public static ExtentReports Report;
	public static ExtentTest test;

	
	public static String getpropvalue(String key)
	{
		String value = null;
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream(Userdirectory+"//input.properties");
			prop.load(ip);
			value=prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getExceldata(String sheetname,int row,int col)throws Exception

	{
		String celldata = null;
		try {
			String FilePath = Userdirectory+"//inputdata.xlsx";
			FileInputStream fis=new FileInputStream(FilePath);
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(sheetname);
			DataFormatter formatter = new DataFormatter();
			Cell cellformat= sh.getRow(row).getCell(col);
			celldata= formatter.formatCellValue(cellformat); 
			System.out.println(celldata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		return celldata;
		

	}
	
	public static void extentreportinitiate() {
		Userdirectory = System.getProperty("user.dir");
		String dateStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HHmm").format(Calendar.getInstance().getTime());
		html = new ExtentHtmlReporter(Userdirectory + "\\Report\\" + dateStamp + "\\TestResult_" + timeStamp + ".html");
		Report = new ExtentReports();
		Report.attachReporter(html);
	}

	public static void extentreportfinisher() {
		Report.flush();
	}

	public static boolean launchbrowser(String Browsername) throws Exception, Exception {
		boolean flag = false;
		try {

			if (Browsername.equalsIgnoreCase("chrome")) {
				System.out.println("Chome Browser");
				System.setProperty("webdriver.chrome.driver", Userdirectory + "\\Drivers\\\\chromedriver.exe");
				ChromeOptions coptions = new ChromeOptions();
				coptions.addArguments("--start-maximized");
				coptions.addArguments("disable-extensions");
				driver = new ChromeDriver(coptions);
			} else if (Browsername.equalsIgnoreCase("firefox")) {
				System.out.println("Firefox Browser");
				System.setProperty("webdriver.gecko.driver", Userdirectory + "\\Drivers\\geckodriver.exe");
				FirefoxOptions foptions = new FirefoxOptions();
				foptions.addPreference("browser.download.folderList", 1);
				foptions.addPreference("browser.download.manager.alertOnEXEOpen", false);
				foptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
				foptions.addPreference("browser.download.manager.showWhenStarting", false);
				foptions.addPreference("browser.download.manager.focusWhenStarting", false);
				foptions.addPreference("browser.download.useDownloadDir", true);
				foptions.addPreference("browser.helperApps.alwaysAsk.force", false);
				foptions.addPreference("browser.download.manager.alertOnEXEOpen", false);
				foptions.addPreference("browser.download.manager.closeWhenDone", true);
				foptions.addPreference("browser.download.manager.showAlertOnComplete", false);
				foptions.addPreference("browser.download.manager.useWindow", false);
				foptions.addPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
				foptions.addPreference("pdfjs.disabled", true);
				driver = new FirefoxDriver(foptions);
			} else if (Browsername.equalsIgnoreCase("edge")) {
				System.out.println("Edge Browser");
				System.setProperty("webdriver.edge.driver", Userdirectory + "\\Drivers\\msedgedriver.exe");
				driver = new EdgeDriver();
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			test.pass("Browser launched and Implicit wait applied");
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Browser not launched" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean openurl(String URL) throws Exception {
		boolean flag = false;
		try {
			driver.get(URL);
			test.pass("URL loaded succesfully : " + URL);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("URL not loaded" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean typetext(String elementPath, String input) throws Exception {
		boolean flag=false;
		try {
			System.out.println(elementPath);
			if (visibilityOfElement(elementPath)) {
				WebElement typeelement = driver.findElement(By.xpath(elementPath));
				typeelement.sendKeys(input);
				test.pass(input + " is Typed in :" + elementPath);
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Not typed" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));

		}
		return flag;
	}
	
	
	
	public static boolean PFtypetext(WebElement elementPath, String input) throws Exception {
		boolean flag=false;
		try {
			System.out.println(elementPath);
			if (PFvisibilityOfElement(elementPath)) {
				elementPath.sendKeys(input);
				test.pass(input + " is Typed in :");
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Not typed" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));

		}
		return flag;
	}

	public static boolean upload(String elementPath, String file) throws Exception {
		boolean flag = false;
		String filepath = Userdirectory + "\\Attachment\\" + file;
		try {
			if (presenceOfElement(elementPath)) {
				WebElement typeelement = driver.findElement(By.xpath(elementPath));
				typeelement.sendKeys(filepath);
				test.pass(" File is Uploaded in :" + elementPath);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("File not uploaded" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean visibilityOfElement(String elementPath) throws Exception {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
			test.pass("Element is Visible and Located : " + elementPath);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			// String Filename=takeSnapShot("Visibilityerror.png");
			// test.fail("Element is not
			// Visble"+e.getMessage()).addScreenCaptureFromPath(takeSnapShot("Visibilityerror.png"));
			test.fail("Element is not Visble" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));

		}
		return flag;
	}
	
	public static boolean PFvisibilityOfElement(WebElement elementPath) throws Exception {
		boolean flag = false;
		System.out.println(elementPath);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfAllElements(elementPath));
			test.pass("Element is Visible and Located : ");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			// String Filename=takeSnapShot("Visibilityerror.png");
			// test.fail("Element is not
			// Visble"+e.getMessage()).addScreenCaptureFromPath(takeSnapShot("Visibilityerror.png"));
			test.fail("Element is not Visble" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));

		}
		return flag;
	}

	public static String convertscrtobase(String FilePath) throws Exception {
		String Base64StringofScreenshot = null;
		try {
			File src = new File(FilePath);
			byte[] fileContent = FileUtils.readFileToByteArray(src);
			Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Base64StringofScreenshot;

	}

	public static boolean alerthandlewithmessage(String message) throws Exception {
		boolean flag = false;
		try {
			String alertmessage = driver.switchTo().alert().getText();
			if (alertmessage.equalsIgnoreCase(message)) {
				driver.switchTo().alert().accept();
				test.pass("Alert Handled");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Alert not handled" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}

		return flag;
	}

	public static boolean presenceOfElement(String elementPath) throws Exception {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
			test.pass("Element is Visible and Located : " + elementPath);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Element is not visible" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}

		return flag;
	}

	public static boolean  click(String elementPath) throws Exception {
		boolean flag=false;
		try {
			if (visibilityOfElement(elementPath)) {
				WebElement clickelement = driver.findElement(By.xpath(elementPath));
				clickelement.click();
				test.pass("Element is clicked :" + elementPath);
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Element is not clicked" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}
	
	
	public static boolean  PFclick(WebElement elementPath) throws Exception {
		boolean flag=false;
		System.out.println(elementPath);
		try {
			if (PFvisibilityOfElement(elementPath)) {
				elementPath.click();			
				test.pass("Element is clicked ");
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Element is not clicked" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean selectdropdown(String elementPath, String value) throws Exception {
		boolean flag = false;
		try {
			if (visibilityOfElement(elementPath)) {
				WebElement dropelement = driver.findElement(By.xpath(elementPath));
				Select drpbox = new Select(dropelement);
				drpbox.selectByVisibleText(value);
				test.pass("Dropdown value : " + value + " is selected");
				flag = true;
			}
		} catch (Exception e) {

			e.printStackTrace();
			test.fail("Dropdown not selected" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean draganddrop(String fromelementpath, String toelemntpath) throws Exception {
		boolean flag = false;
		try {
			if (visibilityOfElement(fromelementpath) && visibilityOfElement(toelemntpath)) {

				WebElement fromelement = driver.findElement(By.xpath(fromelementpath));
				WebElement toelement = driver.findElement(By.xpath(toelemntpath));
				Actions action = new Actions(driver);
				action.dragAndDrop(fromelement, toelement).build().perform();
				test.pass("Drag and Drop Done");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Drag and drop not done" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean scroll(String elementPath) throws Exception {
		boolean flag = false;
		try {
			if (presenceOfElement(elementPath)) {
				JavascriptExecutor je = (JavascriptExecutor) driver;
				WebElement scrollelement = driver.findElement(By.xpath(elementPath));
				je.executeScript("arguments[0].scrollIntoView(true);", scrollelement);
				test.pass("Scroll to Element:" + elementPath);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Scroll not done" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean framein(String elementPath) throws Exception {
		boolean flag = false;
		try {
			if (presenceOfElement(elementPath)) {
				WebElement frameElement = driver.findElement(By.xpath(elementPath));
				driver.switchTo().frame(frameElement);
				test.pass("Frame found");
				flag = true;
			}
		} catch (Exception e) {

			e.printStackTrace();
			test.fail("Frame not found" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean frameout() throws Exception {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			test.pass("Frame out done");
			flag = true;
		} catch (Exception e) {

			e.printStackTrace();
			test.fail("Frame out not done"+e.getMessage()).addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean doubleclick(String elementpath) throws Exception {
		boolean flag = false;
		try {
			if (visibilityOfElement(elementpath)) {

				WebElement clickelement = driver.findElement(By.xpath(elementpath));
				Actions action = new Actions(driver);
				action.doubleClick(clickelement).build().perform();
				test.pass("Double click Done");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Double click not done"+e.getMessage()).addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static boolean browserclose() throws Exception {
		boolean flag = false;
		try {
			driver.quit();
			test.pass("Browser Close");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Browser not closed" + e.getMessage())
					.addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

	public static void isFileDownloaded(String downloadPath, String fileName) throws InterruptedException {
		boolean flag = false;
		File dir;
		for (int j = 1; j <= 10; j++) {
			dir = new File(downloadPath);
			File[] dir_contents = dir.listFiles();

			for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().equals(fileName)) {
					flag = true;
					break;
				}

			}
			if (flag) {
				break;
			}
			Thread.sleep(60000);
		}
	}

	public static String takeSnapShot(String file) throws Exception {

		String fileWithPath = Userdirectory + "\\Screenshot\\" + file;
		try {
			// Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			// Move image file to new destination
			File DestFile = new File(fileWithPath);
			// Copy file at destinationa
			FileUtils.copyFile(SrcFile, DestFile);
			test.pass("Image file created");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Screenshot error"+e.getMessage()).addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return fileWithPath;
	}

	public static boolean fullscreenshot(String file) throws Exception {
		boolean flag = false;
		try {
			String filepath = Userdirectory + "\\Screenshot\\" + file;
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg", new File(filepath));
			test.pass("Fullscreenshot taken");
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			test.fail("Full screenshot not taken"+e.getMessage()).addScreenCaptureFromBase64String(convertscrtobase(takeSnapShot("Error.png")));
		}
		return flag;
	}

}
