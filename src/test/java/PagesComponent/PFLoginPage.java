package PagesComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UtilisComponent.Supporter;

public class PFLoginPage extends Supporter{
	
	public WebDriver driver;
	

    @FindBy(xpath="//input[@name='uid']")
    static WebElement UserPath;
    //WebElement element = driver.findelemenet(By.xpath"//*[@name='test'"]

    @FindBy(xpath="//input[@type='password']")
    static WebElement PassPath;
    

    @FindBy(xpath="//input[@type='submit']")
	static WebElement ButtonPath;
	
	public PFLoginPage()
	{
		this.driver=Supporter.driver;
		PageFactory.initElements(driver, this);
	}

	
	public static void PFvaildLoginPage(String Username, String Password) throws Exception
	{
		Assert.assertEquals(true, PFtypetext(UserPath, Username));		
		Assert.assertEquals(true,PFtypetext(PassPath, Password));
		Assert.assertEquals(true,PFclick(ButtonPath));
	}
	
}
