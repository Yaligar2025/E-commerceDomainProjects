package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utils.WaitUtils;

public class LoginPage {
    
    WebDriver driver;

    // Locators
    public By signInLink = By.id("signin");
    public By userNameDropdown = By.xpath("//div[@id='username']//div[contains(@class,'css-tlfecz-indicatorContainer')]");
    public By passwordDropdown = By.xpath("//div[@id='password']//div[contains(@class,'css-tlfecz-indicatorContainer')]");
    public By usernameFieldLocator = By.id("username");
    public By loggedInUserName = By.xpath("//span[@class='username']");
    private By loginButton = By.xpath("//button[@id='login-btn']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Perform login
    public void login(String username, String password) {
        // Wait for and click username dropdown
        WaitUtils.waitForVisibility(driver, userNameDropdown, 15);
        driver.findElement(userNameDropdown).click();

        // Select username
        By usernameOption = By.xpath("//div[@id='username']//div[text()='" + username + "']");
        WaitUtils.waitForVisibility(driver, usernameOption, 10);
        driver.findElement(usernameOption).click();

        // Wait for and click password dropdown
        WaitUtils.waitForVisibility(driver, passwordDropdown, 15);
        driver.findElement(passwordDropdown).click();

        // Select password
        By passwordOption = By.xpath("//div[@id='password']//div[text()='" + password + "']");
        WaitUtils.waitForVisibility(driver, passwordOption, 10);
        driver.findElement(passwordOption).click();

        // Click login
        WaitUtils.waitForVisibility(driver, loginButton, 10);
        driver.findElement(loginButton).click();
    }

    // Verify if login was successful
    public boolean isLoginSuccessful() {
        WaitUtils.waitForVisibility(driver, loggedInUserName, 10);
        return driver.findElement(loggedInUserName).isDisplayed();
    }
}
