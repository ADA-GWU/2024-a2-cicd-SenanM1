package az.edu.ada.wm2.Assignment1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Setup WebDriverManager to manage the ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configure Chrome to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");

        // Initialize the ChromeDriver with options
        driver = new ChromeDriver(options);
    }

    @Test
    public void testPageTitle() {
        // Navigate to your application URL
        driver.get("http://localhost:8080");
        // Assert the title of the page
        assertEquals("Home page", driver.getTitle());
    }

    @Test
    public void testNavigation() {
        driver.get("http://localhost:8080");
        WebElement navLink = driver.findElement(By.className("pet-owner"));
        navLink.click();

        // Verify the new URL or page content.
        assertEquals("http://localhost:8080/owner/page/1", driver.getCurrentUrl());
    }

    @Test
    public void testFormSubmission() {
        driver.get("http://localhost:8080/owner/new");
        WebElement firstnameInput = driver.findElement(By.id("firstName"));
        firstnameInput.sendKeys("Test FirstName");

        WebElement lastnameInput = driver.findElement(By.id("firstName"));
        lastnameInput.sendKeys("Test Lastname");

        WebElement petsInput = driver.findElement(By.id("pets"));
        petsInput.sendKeys("1,3");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        // Use WebDriverWait to wait for the URL to change if necessary
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("http://localhost:8080/owner/page/1"));

        assertEquals("http://localhost:8080/owner/page/1", driver.getCurrentUrl());


    }



    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
