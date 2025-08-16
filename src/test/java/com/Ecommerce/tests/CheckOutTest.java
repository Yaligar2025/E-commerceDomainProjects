package com.Ecommerce.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import Utils.WaitUtils;

public class CheckOutTest extends TestBase {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void initPagesAndLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        driver.findElement(loginPage.signInLink).click();
        loginPage.login("demouser", "testingisfun99");
        WaitUtils.waitForVisibility(driver, loginPage.usernameFieldLocator, 10);
    }

    @Test(priority = 1)
    public void placeOrderWithValidDetails() throws InterruptedException {
        productPage.addFirstProductToCart();
        WaitUtils.waitForCartCount(driver, 1);

        checkoutPage.clickCheckout();
        checkoutPage.fillShippingFormAndSubmit(
                "Chandu", "Kola", "MVP", "AP", "530017"
        );

        Thread.sleep(2000);
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order confirmation not displayed!");
        logWithScreenshot("Order Confirmed");
    }

    @Test(priority = 2)
    public void checkoutWithoutItemsNegativeTest() throws InterruptedException {
        driver.findElement(checkoutPage.cartButton).click();
        Thread.sleep(3000);

        String emptyCartMessage = driver.findElement(
                By.xpath("//p[@class='shelf-empty']")
        ).getText();

        List<WebElement> productsInCart = driver.findElements(
                By.xpath("//div[@class='float-cart__shelfcontainer']//div[@class='shelf-item']")
        );

        if (productsInCart.isEmpty() && emptyCartMessage.contains("Add some products in the bag")) {
            Assert.assertTrue(
                    emptyCartMessage.contains("Add some products in the bag"),
                    "Empty cart message not displayed correctly!"
            );
            logWithScreenshot("Cart is empty and message is displaying as - Add some products in the bag.");
        } else {
            Assert.fail("Either the cart is not empty or the expected empty cart message was not found.");
        }
    }
}
