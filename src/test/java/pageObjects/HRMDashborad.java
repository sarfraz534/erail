package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// use of this file: for finding locators and making methods for HRMDashbord for target page of UseCase_2
public class HRMDashborad extends BasePage{

	//constructor
	public HRMDashborad(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//---------------WebElement locators---------------------
	//locator for text Dashboard (target page)
	@FindBy(xpath="//h6[text()='Dashboard']")
	WebElement text_Dashboard;
	
	//locator for user_dropdown (target page)
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']")
	WebElement click_userdropdown;
	
	//locator for logout button of user_dropdown (target page)
	@FindBy(xpath="//a[text()='Logout']")
	WebElement click_logout;
	
	//------------------------Action methods----------------------------------
	//method to check if dashboard displayed or not
	public boolean isDashboardPageDisplay() {
		try {
			return(text_Dashboard.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	//method to click user dropdown
	public void clickUserDropdown() {
		click_userdropdown.click();
	}
	
	//method to logout from the dashboard
	public void clickLogout() {
		click_logout.click();
	}
	
}
