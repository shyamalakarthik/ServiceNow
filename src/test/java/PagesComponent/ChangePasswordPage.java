package PagesComponent;

import org.testng.Assert;

import UtilisComponent.Supporter;

public class ChangePasswordPage extends Supporter{
	
	static String OldPassPath = "//input[@name='oldpassword']";
	static String ConfirmPassPath = "//input[@name='newpassword']";
	static String NewPassPath = "//input[@name='confirmpassword']";
	static String ResetButtPath = "//input[@value='Reset']";
	
	public static void ChangePassword(String OldVal, String NewVal, String ConfirmVal) throws Exception
	{
		Assert.assertEquals(true, typetext(OldPassPath, OldVal));
		Assert.assertEquals(true, typetext(ConfirmPassPath, NewVal));
		Assert.assertEquals(true, typetext(NewPassPath, ConfirmVal));
	}
	
	public static void ResetButtonClick() throws Exception
	{
		Assert.assertEquals(true, click(ResetButtPath));
	}

}
