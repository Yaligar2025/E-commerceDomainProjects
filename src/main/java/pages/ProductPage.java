package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {
    
    WebDriver driver;

    // Locator for all "Add to Cart" buttons
    private By addToCartButtons = By.cssSelector(".shelf-item__buy-btn");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add the first product to the cart
    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    // Add multiple products to the cart
    public void addMultipleProductsToCart(int count) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        System.out.println("Number of add-to-cart buttons: " + buttons.size());
        
        for (int i = 0; i < count && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }
}
