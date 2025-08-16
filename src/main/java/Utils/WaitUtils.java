package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 70;

    /**
     * Wait until the element is visible and return it
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Overloaded method with default timeout
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Wait until the element is clickable and return it
     */
    public static WebElement waitForClickable(WebDriver driver, By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait until cart count matches expected value
     */
    public static void waitForCartCount(WebDriver driver, int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        By cartCount = By.cssSelector(".bag__quantity");
        wait.until(ExpectedConditions.presenceOfElementLocated(cartCount));
        wait.until(ExpectedConditions.textToBe(cartCount, String.valueOf(expectedCount)));
    }

    /**
     * Capture screenshot and return file path
     */
    public static String captureScreenshot(WebDriver driver, String testName, String browser) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderPath = "Screenshots";
        String filePath = folderPath + File.separator + browser + "_" + testName + "_" + timestamp + ".png";

        // Create folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(filePath);

        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
