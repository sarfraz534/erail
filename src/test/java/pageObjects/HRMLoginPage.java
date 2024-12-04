package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//use of this file: to valid user credentials
public class HRMLoginPage extends BasePage {

	//constructors 
	public HRMLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// --------------------WebElements---------------------------
	//locator for username field
	@FindBy(xpath = "//input[@name='username']")
	WebElement text_username;
	
	//locator for password field
	@FindBy(xpath = "//input[@name='password']")
	WebElement text_password;
	
	//locator for submit button
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btn_login;


	//------------------ Action methods-------------------------------
	
	//method for setting the user name
	public void setUsername(String uname) {
		text_username.sendKeys(uname);
	}

	//method for setting password
	public void setPassword(String pwd) {
		text_password.sendKeys(pwd);
	}

	//method for login
	public void clickLogin() {
		btn_login.click();
	}
}
