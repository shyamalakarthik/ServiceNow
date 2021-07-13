package PagesComponent;

import org.testng.Assert;

import UtilisComponent.Supporter;

public class DashboardPage extends Supporter{
	static String NewCustVisibilityPath = "//a[text()='New Customer']";
	static String EditCustVisibilityPath = "//a[text()='Edit Customer']";
	static String DeleteCustVisibilityPath = "//a[text()='Delete Customer']";
	static String ChangePassButtPath = "//a[text()='Change Password']"; 
	
	public static void dashboardelementverifier() throws Exception
	{
		Assert.assertEquals(true,visibilityOfElement(NewCustVisibilityPath));
		Assert.assertEquals(true,visibilityOfElement(EditCustVisibilityPath));
		Assert.assertEquals(true,visibilityOfElement(DeleteCustVisibilityPath));
	}
	
	public static void switchtoChangePassScreen() throws Exception
	{
		Assert.assertEquals(true, click(getExceldata("Dashboardpage", 1, 0)));
	}

}
