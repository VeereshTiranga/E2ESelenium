package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\firstReport.html");
		reporter.config().setReportName("WEBAutomation Results");
		reporter.config().setDocumentTitle("Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name: ", "Veeresh Tiranga");
		return extent;
		
		
	}
}
