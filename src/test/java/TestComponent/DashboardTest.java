package TestComponent;

import org.testng.Assert;
import org.testng.annotations.Test;

import ConfigComponent.Engine;
import PagesComponent.DashboardPage;
import PagesComponent.LoginPage;
import PagesComponent.PFDashboardPage;
import PagesComponent.PFLoginPage;

public class DashboardTest extends Engine
{
	@Test
	public static void dashscreen() throws Exception
	{
		try {
			LoginPage login = new LoginPage();
			login.vaildLoginPage("mngr337244", "YgAnusy");
			DashboardPage Dashboard = new DashboardPage();
			Dashboard.dashboardelementverifier();
			Dashboard.switchtoChangePassScreen();
		} catch (Exception | AssertionError e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	
	
	@Test
	public static void PFdashscreen() throws Exception
	{
		try {
			PFLoginPage login = new PFLoginPage();
			login.PFvaildLoginPage("mngr337244", "YgAnusy");
			PFDashboardPage Dashboard = new PFDashboardPage();
			Dashboard.PFdashboardelementverifier();
			Dashboard.PFswitchtoChangePassScreen();
		} catch (Exception | AssertionError e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
}
