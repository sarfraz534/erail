package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HRMDashborad;
import pageObjects.HRMLoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

//use of this file: to handling the steps for UseCase_2
public class UseCase_2 extends BaseClass {
	@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class)
	public void veryfyHRMLogin(String username, String password, String exp) {
		try {
			HRMLoginPage hlp = new HRMLoginPage(driver);
			hlp.setUsername(username);
			hlp.setPassword(password);
			hlp.clickLogin();

			HRMDashborad hd = new HRMDashborad(driver);
			Boolean target_page = hd.isDashboardPageDisplay();
			if (exp.equalsIgnoreCase("valid")) {
				if (target_page == true) {
					hd.clickUserDropdown();
					hd.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("invalid")) {
				if (target_page == true) {
					hd.clickUserDropdown();
					hd.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}

			}
		} catch (Exception e) {
			Assert.fail("test validation fail : " + e.getMessage());
		}
	}

}
