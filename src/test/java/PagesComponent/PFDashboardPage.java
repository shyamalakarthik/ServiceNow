package PagesComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UtilisComponent.Supporter;

public class PFDashboardPage extends Supporter{
	
	WebDriver driver;
	

    @FindBy(xpath="//a[text()='New Customer']")
    static WebElement NewCustVisibilityPath;
    
    @FindBy(xpath="//a[text()='Edit Customer']")
    static WebElement EditCustVisibilityPath;
    
    @FindBy(xpath="//a[text()='Delete Customer']")
    static WebElement DeleteCustVisibilityPath;
    
    @FindBy(xpath="//a[text()='Change Password']")
    static WebElement ChangePassButtPath;
    
	
	public PFDashboardPage() {
		this.driver=Supporter.driver;
		PageFactory.initElements(driver, this);
	}

	
	public static void PFdashboardelementverifier() throws Exception
	{
		Assert.assertEquals(true,PFvisibilityOfElement(NewCustVisibilityPath));
		Assert.assertEquals(true,PFvisibilityOfElement(EditCustVisibilityPath));
		Assert.assertEquals(true,PFvisibilityOfElement(DeleteCustVisibilityPath));
	}
	
	public static void PFswitchtoChangePassScreen() throws Exception
	{
	
		Assert.assertEquals(true, PFclick(ChangePassButtPath));
		Thread.sleep(50000);
	}
}
