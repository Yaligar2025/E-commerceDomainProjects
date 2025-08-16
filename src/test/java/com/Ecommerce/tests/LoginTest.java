package com.Ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ConfigReader;
import base.TestBase;
import pages.LoginPage;

public class LoginTest extends TestBase {

    String sheetName = "login";

    @Test(dataProvider = "LoginData")
    public void loginTestCases(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        // Click sign-in link
        driver.findElement(loginPage.signInLink).click();

        // Perform login
        loginPage.login(username, password);

        // Verify login success
        Assert.assertTrue(
            loginPage.isLoginSuccessful(),
            "Login failed with username: '" + username + "' and password: '" + password + "'"
        );

        Thread.sleep(1000); // Optional: Replace with proper explicit wait
        logWithScreenshot("Signin Successful with valid credentials.");
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws Exception {
        return ConfigReader.getTestData(sheetName);
    }
}
