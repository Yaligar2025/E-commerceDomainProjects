package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    private WebDriver driver;

    // Locators
    public By checkoutButton = By.cssSelector(".buy-btn");
    private By firstName = By.id("firstNameInput");
    private By lastName = By.id("lastNameInput");
    private By address = By.id("addressLine1Input");
    private By state = By.id("provinceInput");
    private By postalCode = By.id("postCodeInput");
    private By submitButton = By.id("checkout-shipping-continue");
    private By orderConfirmation = By.id("confirmation-message");
    public static By cartButton = By.xpath("//span[@class='bag bag--float-cartclosed']");
    public By continueShoppingButton = By.xpath("//div[@class='buy-btn']");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Click checkout button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Fill shipping form and submit
    public void fillShippingFormAndSubmit(String fname, String lname, String addr, String st, String pin) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(state).sendKeys(st);
        driver.findElement(postalCode).sendKeys(pin);
        driver.findElement(submitButton).click();
    }

    // Verify order confirmation
    public boolean isOrderConfirmed() {
        WebElement confirmation = driver.findElement(orderConfirmation);
        return confirmation.isDisplayed();
    }
}
