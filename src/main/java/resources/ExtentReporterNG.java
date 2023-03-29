package resources;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	@Test
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter esr = new ExtentSparkReporter(path);
		esr.config().setDocumentTitle("Framework Design Results");
		esr.config().setReportName("Web Automation Results");
		
		ExtentReports er = new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tester", "Manohar");
		return er;
	}

}
