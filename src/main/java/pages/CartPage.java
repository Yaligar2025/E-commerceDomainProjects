package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    
    WebDriver driver;

    // Locators
    private By cartCount = By.cssSelector(".bag__quantity");
    private By cartItems = By.cssSelector(".float-cart__shelf-container > div");
    private By removeButtons = By.cssSelector(".shelf-item__del");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get cart item count as displayed
    public String getCartItemCount() {
        return driver.findElement(cartCount).getText();
    }

    // Get total number of items in the cart
    public int getTotalItemsInCart() {
        return driver.findElements(cartItems).size();
    }

    // Remove the first item from the cart
    public void removeFirstItemFromCart() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
}
