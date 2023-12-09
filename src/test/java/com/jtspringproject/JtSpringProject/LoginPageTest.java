package com.jtspringproject.JtSpringProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPageTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8080/";

    @Before
    public void setUp() {
        // Set up the WebDriver (in this example, using Chrome)
        System.setProperty("webdriver.chrome.driver", "D:\\sem 5\\sqe project\\Proj\\JtProject\\src\\test\\java\\com\\jtspringproject\\JtSpringProject\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        // Open the login page
        driver.get(BASE_URL);

        // Locate the username and password fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Input valid username and password
        usernameField.sendKeys("lisa");
        passwordField.sendKeys("765");

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        loginButton.click();

        // Add assertions or validations based on the expected behavior after login
        // For example, you can check if a specific element is present on the next page.

        // Sleep added to see the result (for demonstration purposes; not recommended in real tests)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
