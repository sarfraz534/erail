package testBase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

//use of this file:common file for both usecase for opening and closing the URL
public class BaseClass {
 public static WebDriver driver;

  @BeforeClass
  @Parameters({"url"})
  public void setUp(String url) throws IOException {
	  
	 driver= new ChromeDriver();
	 driver.manage().deleteAllCookies();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	 driver.get(url);
	 driver.manage().window().maximize();
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }
}
