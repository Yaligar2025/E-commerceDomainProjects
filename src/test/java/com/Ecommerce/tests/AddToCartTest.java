package com.Ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import Utils.WaitUtils;

public class AddToCartTest extends TestBase {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void initPagesAndLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        driver.findElement(loginPage.signInLink).click();
        loginPage.login("demouser", "testingisfun99");
        WaitUtils.waitForVisibility(driver, loginPage.usernameFieldLocator, 10);
    }

    @Test(priority = 1)
    public void addSingleItemToCartTest() throws InterruptedException {
        productPage.addFirstProductToCart();
        WaitUtils.waitForCartCount(driver, 1);

        Assert.assertEquals(
            Integer.parseInt(cartPage.getCartItemCount()),
            1,
            "Cart count mismatch after adding one item."
        );

        logWithScreenshot("Single item added to cart");
    }

    @Test(priority = 2)
    public void addMultipleItemsToCartTest() throws InterruptedException {
        productPage.addMultipleProductsToCart(3);
        WaitUtils.waitForCartCount(driver, 3);

        Assert.assertEquals(
            Integer.parseInt(cartPage.getCartItemCount()),
            3,
            "Cart count mismatch after adding multiple items."
        );

        logWithScreenshot("Multiple items added to cart");
    }

    @Test(priority = 3)
    public void removeItemFromCartTest() throws InterruptedException {
        productPage.addMultipleProductsToCart(2);
        WaitUtils.waitForCartCount(driver, 2);

        int before = cartPage.getTotalItemsInCart();
        logWithScreenshot("2 items added to cart");

        cartPage.removeFirstItemFromCart();
        WaitUtils.waitForCartCount(driver, before - 1);

        logWithScreenshot("1 item removed from the cart");

        int after = cartPage.getTotalItemsInCart();
        Assert.assertEquals(after, before - 1, "Cart count mismatch after removing an item.");

        logWithScreenshot("1 item left in the cart");
    }
}
