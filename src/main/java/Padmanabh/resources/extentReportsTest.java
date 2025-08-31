package Padmanabh.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportsTest {

	public static  ExtentReports getExtentReports()
	{
		String path=".//Reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
}
