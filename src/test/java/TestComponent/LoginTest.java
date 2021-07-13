package TestComponent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ConfigComponent.Engine;
import PagesComponent.LoginPage;

public class LoginTest extends Engine{

	@Test
	@Parameters({"username","password"})
	public  void screen1(String username, String password) throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.vaildLoginPage(username, password);
		} catch (Exception | AssertionError e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	//ob.screen1(uname,pwd);
	
	//@Test
	public static void screen2() throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.invaildLoginPage("mngr27483", "pAgYveq");
		} catch (Exception | AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	//@Test
	public static void screen3() throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.invaildLoginPage("mngr274832", "pAgYve");
		} catch (Exception | AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	//@Test
	public static void screen4() throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.invaildLoginPage("mngr27483", "pAgYve");
		} catch (Exception | AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}
