package Selenium_Java.FrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest {

	@Test
	public void standAloneTest() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));		
		String productName = "ZARA COAT 3";

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("datta568@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password123");
		driver.findElement(By.id("login")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));

		WebElement item = items.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean checkItem = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(checkItem);

		driver.findElement(By.cssSelector(".totalRow .btn")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement desiredCountry = countryList.stream()
				.filter(country -> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		desiredCountry.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
		driver.close();
		
	}

}
