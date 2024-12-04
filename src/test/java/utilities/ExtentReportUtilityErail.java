package utilities;

import java.awt.Desktop;
	import java.io.File;
	import java.io.IOException;
	//import java.net.URL;
	import java.net.URL;

	//Extent report 5.x...//version

	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;

	import org.apache.commons.mail.DefaultAuthenticator;
	import org.apache.commons.mail.ImageHtmlEmail;
	import org.apache.commons.mail.resolver.DataSourceUrlResolver;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	import testBase.BaseClass;

	//use of this file: To make Extend Reports for use case 1
	public class ExtentReportUtilityErail implements ITestListener {
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;

		String repName;

		public void onStart(ITestContext testContext) {
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
			repName = "Test-Report-Erail-" + timeStamp + ".html";
			sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

			sparkReporter.config().setDocumentTitle("Erail Automation Report"); // Title of report
			sparkReporter.config().setReportName("Erail Functional Testing"); // name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Erail");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environemnt", "QA");
			
		}

		public void onTestSuccess(ITestResult result) {
		
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); // to display groups in report
			test.log(Status.PASS,result.getName()+" got successfully executed");
			
		}

		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL,result.getName()+" got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());	
		}

		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+" got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}

		public void onFinish(ITestContext testContext) {
			
			extent.flush();
			
			String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}	 
		}

}



