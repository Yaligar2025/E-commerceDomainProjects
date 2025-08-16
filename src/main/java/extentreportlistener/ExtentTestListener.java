package extentreportlistener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import base.TestBase;
import Utils.WaitUtils;

public class ExtentTestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extent;
	private static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Suite", suite.getName());

	}

	@Override
	public void onFinish(ISuite suite) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		TestBase.logger = test;

		String browser = TestBase.getBrowser();

		test.info("Browser Used: " + browser);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.fail("Test failed: " + result.getThrowable());

		WebDriver driver = TestBase.driver;

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String screenshotName = result.getName() + "_" + timestamp;

		String browser = TestBase.getBrowser();

		String filePath = WaitUtils.captureScreenshot(driver, screenshotName, browser);

		test.fail("📸 Screenshot on failure", MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip("Test skipped");
	}

}