package DataTestComponents;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import Prasanna.resource.ExtentReporterNG;


public class ListenersTest extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread is(ErrorValidationTest)->test
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//screenshot code
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		//screenshot code, Attach to report
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		//screenshot code
		extent.flush();
		
	}
	
	


}