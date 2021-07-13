package TestComponent;

import org.testng.annotations.Test;

import ConfigComponent.Engine;
import PagesComponent.ChangePasswordPage;
import PagesComponent.DashboardPage;
import PagesComponent.LoginPage;
import junit.framework.Assert;

public class ChangePasswordTest extends Engine {
	
	@Test
	public static void ChangePasswordScreen() throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.vaildLoginPage("mngr274832", "pAgYveq");
			DashboardPage DashPage = new DashboardPage();
			DashPage.switchtoChangePassScreen();
			ChangePasswordPage ChangePwd = new ChangePasswordPage();
			ChangePwd.ChangePassword("mngr274832", "Satheh", "Satheh");
			ChangePwd.ResetButtonClick();
			Thread.sleep(5000);
		} catch (Exception | AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
