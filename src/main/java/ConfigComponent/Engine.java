package ConfigComponent;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import UtilisComponent.Supporter;

public class Engine extends Supporter {

@BeforeMethod
  @Parameters({"testcasename","browsername"})
  public static void beforeMethod(String testcasename,String browsername) throws Exception {
	
	  String url=getpropvalue("URL");		
	  test=Report.createTest(testcasename);
	  launchbrowser(browsername);
	  openurl(url);
  }

	@AfterMethod
	public static void afterMethod() throws Exception {
		browserclose();
		extentreportfinisher();
	}

	@BeforeSuite
	public static void beforeSuite() {
		extentreportinitiate();
	}

	@AfterSuite
	public static void afterSuite() {
		extentreportfinisher();
	}

}
