package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> ordersName;

	public boolean verifyOrder(String productName) throws InterruptedException {
		visibilityOfElementLocated(ordersName);		
		return ordersName.stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
	}
}
