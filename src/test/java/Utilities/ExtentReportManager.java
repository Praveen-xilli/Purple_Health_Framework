package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Test_Base.Base_class;

public class ExtentReportManager implements ITestListener  {
	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
	String Reportname;
	
	
	 public void onStart(ITestContext context) {
		 /*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt = new Date();
			String currentdatetimestamp = df.format(dt);*/
		 String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 Reportname = "Test-Report-"+ timestamp+" .html";
		 sparkreport = new ExtentSparkReporter(".\\Reports\\"+ Reportname);
		 sparkreport.config().setDocumentTitle("Purple health Automation report");//Title of the report
		 sparkreport.config().setReportName("Purple health functional testing");//Name of the report
		 sparkreport.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkreport);
		 extent.setSystemInfo("Application", "Purple health care");
		 
		 String os = context.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating system", os);
		 
		 String browser = context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browsername", browser);
		 
		 List<String> includedgroups = context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedgroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups",includedgroups.toString());
		 }
	  }
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in the report
	  }

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,result.getName()+" Test method got successfully executed");
	  }

	  public void onTestFailure(ITestResult result) {
			test.log(Status.FAIL,result.getName()+" Test method got failed");
			test.log(Status.INFO,result.getThrowable().getMessage());
			try {
				String imagepath = new Base_class().Capturescreen(result.getName());
				test.addScreenCaptureFromPath(imagepath);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }

	  public void onTestSkipped(ITestResult result) {
		  test.log(Status.SKIP,result.getName()+" Test method got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
	  }
	  public void onFinish(ITestContext context) {
		  extent.flush();
		  
		  String pathofextentreport = System.getProperty("user.dir")+"\\reports\\"+Reportname;
		  File extentreport = new File(pathofextentreport);
		  try {
			  Desktop.getDesktop().browse(extentreport.toURI());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	  }

}
