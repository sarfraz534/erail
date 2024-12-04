package pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

// use of this file : finding locators and declaring methods of UseCase_1
public class HomePage extends BasePage{

	//Constructor 
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//--------------WebElements Locators--------------------------------
	
	//Locator for From Station Field
	@FindBy(xpath="//input[@id='txtStationFrom']")
	WebElement txt_fromStation;
	
	//locator for list of station name when searched "DEL" in from station
	@FindAll(@FindBy(xpath="//div[contains(@title,'De') or contains(@title,'del')]"))
	List<WebElement> list_Station;
	
	
	//Locator for calendar
	@FindBy(xpath="//input[@title='Select Departure date for availability']")
	WebElement calendar;
	
	//locator containing all the dates month and year visible to the user
	@FindAll(@FindBy(xpath="(//table[@class='Month'])//tr/td"))
	List<WebElement> month_year;
	
	//---------------Action method----------------------------------------
	
	//method to clear station from station field and enter "DEL"
	public void fromStation(String fromStationName) {
		txt_fromStation.clear();                              //clearing field
		txt_fromStation.sendKeys(fromStationName);            //passing "DEL" 
	}
	
    //method to print and click 4rth item
	public String[] select4thStation() throws IOException {
		//printing 4rth element of the list 
		// as indexing start from 0 we need to get 4rth element therefore index for that element is "3"
		System.out.println("4rth station name with suffix "+list_Station.get(3).getText());
		
		//Step 7:
		//getting data from the drop down list
		String[] actual_station=getting_data_from_DropDown(); //calling method
		
		//clicking the item 
		list_Station.get(3).click();
		return actual_station;
	 }
	
	// method to get the items from dropdown
	public String[] getting_data_from_DropDown() {
		int size=list_Station.size();                   //size of dropdown locator elements
		String[] list_of_station=new String[size];      //initialize variable to store only station name without short name
		
		for(int i=0;i<size;i++) {                       //for loop to split and store required data
			String[] lines = list_Station.get(i).getText().split("\\R");	 //spliting the string where '\n occurs		\\R is a regular expression used to specify '\n'	
			list_of_station[i]=lines[0]	;                                    //storing the first value i.e. station name in our array
		}	
		return list_of_station; //indexing start from zero i.e 4th station
	}
	
	//writing in Excel file 
	//Parameters: sation_actual --> contains string array fetch for drop down
	public void writtinginexcelfile(String[] station_actual) throws IOException {
		String path=".\\testData\\Erail_Actual_Data.xlsx";          //path where i want to create Excel file
		ExcelUtility wb=new ExcelUtility(path);                     //object instantiate for excel file 
		wb.setCellData("Sheet1", 0, 0,"Actual_station" );           //setting the title for the column
		for(int i=0;i<station_actual.length;i++) {                  //inserting data in excel file by iterating the array
			wb.setCellData("Sheet1", i+1, 0,station_actual[i] );
		}
	}
	
	//comparing two excel file
	//1. number of rows same or not
	//2. number of column is same or not 
	//3. data at same cell is same or not
	public void compare() throws IOException {
		System.out.println("-------------------------COMPARING EXCEL SHEET ------------------------");
	    String file1 = ".\\testData\\Erail_Actual_Data.xlsx";              // path for actual data excel sheet taken from drop down
        String file2 = ".\\testData\\Erail_Expected_Data.xlsx";            // path for excpected data excel sheet

        ExcelUtility workbook1 = new ExcelUtility(file1);                  // object for file 1
        ExcelUtility workbook2 = new ExcelUtility(file2);                  // object for file 2

        
        // finding number of rows and column in Actual data
        int totalrows1=workbook1.getRowCount("Sheet1");	
		int totalcols1=workbook1.getCellCount("Sheet1",1);
		
		//finding number of rows and column in expected data
		int totalrows2=workbook1.getRowCount("Sheet1");	
		int totalcols2=workbook2.getCellCount("Sheet1",1);
		
		//comparing rows
        if (totalrows1 != totalrows2) {
            System.out.println("Row count mismatch");
        }else {
        	System.out.println("Row count match");
        }
        
        //comparing columns
        if (totalcols1 != totalcols2) {
            System.out.println("Column count mismatch");
        }else {
        	System.out.println("Column count match");
        }
        
        //comparing data at each cell
        for(int i=0;i<totalrows1;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols1;j++)  //0    i is rows j is col
			{
				String d1= workbook1.getCellData("Sheet1",i, j);  //getting cell data at index [i][j]
				String d2= workbook2.getCellData("Sheet1",i, j); 
				if(!d1.equals(d2)) {
					System.out.println("Cell value mismatch in row " + (i + 1) + ", column " + (j + 1));
				}else {
					System.out.println("Cell value match in row " + (i + 1) + ", column " + (j + 1));
				}
			}
		}
    }
	
	//method to open calender
	public void click_calendar() {
		calendar.click();
	}
	
	//method to click desired date
	public void click_date(String mon_yr, String date)
	{
		boolean flag=false; //need of flag: first we check month-Year afterthat we check date
		for(int i=0;i<month_year.size();i++)     
		{
			if(month_year.get(i).getText().equals(mon_yr)) {  //matching moth_year
				flag=true;
			}
			if(flag==true && month_year.get(i).getText().equals(date)) {  //if matched month_year than match date
				month_year.get(i).click(); //clicking the date
				break;
			}
		}
	}


	

}
