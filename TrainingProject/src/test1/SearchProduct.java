package test1;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


	public class SearchProduct {

	    public static void main(String[] args) throws InterruptedException {

	    	// Setup ChromeDriver automatically
	        WebDriverManager.chromedriver().setup();

	        WebDriver driver = new ChromeDriver();

	        driver.get("https://www.amazon.in");
	        driver.manage().window().maximize();

	        // Explicit wait
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        // Wait for search box using your DOM ID
	        WebElement searchBox = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))
	        );

	        // Enter search text
	        searchBox.sendKeys("laptop");

	        // Click search button
	        driver.findElement(By.cssSelector("#nav-search-submit-button")).click();

	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));

	     // Wait for product titles (h2) to be visible
	     wait1.until(ExpectedConditions.visibilityOfElementLocated(
	         By.cssSelector("h2.a-size-medium span")
	     ));

	        // Click first product
	        WebElement firstProduct = driver.findElement(
	                By.cssSelector("h2.a-size-medium span")
	        );
	        firstProduct.click();

	        // Switch to new tab
	        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	        driver.switchTo().window(tabs.get(1));

	        // Wait for product title
	        WebElement productTitle = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.id("productTitle"))
	        );

	        String title = productTitle.getText();

	        // Verification
	        if (title.toLowerCase().contains("laptop")) {
	            System.out.println("✅ Test Passed");
	        } else {
	            System.out.println("❌ Test Failed");
	        }

	        driver.quit();
	    }
	}