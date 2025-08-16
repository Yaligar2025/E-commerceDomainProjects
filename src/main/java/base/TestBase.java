package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import Utils.WaitUtils;
import Utils.WebDriverFactory;

public class TestBase {
	public static ExtentTest logger;
	public static WebDriver driver;
	private static ThreadLocal<String> threadBrowser = new ThreadLocal<>();

	public static String getBrowser() {
		return threadBrowser.get();
	}

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		threadBrowser.set(browser);
		driver = WebDriverFactory.createDriver(browser);
		driver.manage().window().maximize();
		driver.get("https://bstackdemo.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		threadBrowser.remove();
	}

	protected void logWithScreenshot(String message) {

		String testName = message.replace(" ", "_");

		String browser = getBrowser();

		String path = WaitUtils.captureScreenshot(driver, testName, browser);

		if (logger != null) {

			logger.log(Status.INFO, message);

			logger.info("🖼️ Click to Open Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} else {

			System.out.println("⚠️ Logger is null. Screenshot saved at: " + path);

		}
	}

}