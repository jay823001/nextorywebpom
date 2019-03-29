package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import generics.Utility;

public class CustomListener implements ITestListener{
	
	public void onTestFailure(ITestResult result) 
	{
		String folder=AutomationConstants.SNAP_PATH;
		Utility.getDesktopScreenshot(folder);
	}

	public void onTestStart(ITestResult result) 
	{
		
	}

	public void onTestSuccess(ITestResult result) 
	{
	
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
