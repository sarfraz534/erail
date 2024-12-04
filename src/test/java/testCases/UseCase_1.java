package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

//use of this file: handling the steps for UseCase_1
public class UseCase_1 extends BaseClass {
  @Test()
  @Parameters({"fromstation","month_year","date"})
  public void bookTicket(String fromstation, String month_year,String date)  { 
	  try {
		  HomePage hp= new HomePage(driver);
		  
		  //Step3: Clear the data from “From” field.  
		  //Step 4: Insert data “DEL” in the field to open the drop down.  
		  hp.fromStation(fromstation);
	   
		  //Step 5: Select the station at 4th position in the dropdown & print it.  
		  String[] actual_Station=hp.select4thStation();
		  
		  //Step 7: write it into an excel file  
		  hp.writtinginexcelfile(actual_Station);
		  
		  //Step 7: compare with the expected excel file
		  hp.compare();
		  
		  //opening calender
		  hp.click_calendar();
		  
		  //Step 8: Select date in “Sort on Date”.
		  hp.click_date(month_year,date);
		  
	  }catch(Exception e) {
		  Assert.fail("test fail : " + e.getMessage());
	  }
	  	  	 
  }  
}
