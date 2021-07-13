package PagesComponent;

import org.testng.Assert;

import ConfigComponent.Engine;
import UtilisComponent.Supporter;

public class LoginPage extends Supporter{
	static String UserPath = "//input[@name='uid']";
	static String PassPath = "//input[@type='password']";
	static String ButtonPath = "//input[@type='submit']";
	


	public static void vaildLoginPage(String Username, String Password) throws Exception
	{
		Assert.assertEquals(true, typetext(getpropvalue("USERNAME"), Username));		
		Assert.assertEquals(true,typetext(getExceldata("Loginpage", 1, 0), Password));
		Assert.assertEquals(true,click(ButtonPath));
	}
	
	
	public static void invaildLoginPage(String Username, String Password) throws Exception
	{
		Assert.assertEquals(true,typetext(UserPath, Username));		
		Assert.assertEquals(true,typetext(PassPath, Password));
		Assert.assertEquals(true,click(ButtonPath));
		Thread.sleep(2000);
		Assert.assertEquals(true,alerthandlewithmessage("User or Password is not valid"));
		Thread.sleep(2000);
	}
}
