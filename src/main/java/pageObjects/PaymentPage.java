package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(css = ".ta-results button")
	List<WebElement> countryList;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;

	By countryListLocator = By.cssSelector(".ta-results button");

	public void inputCountry(String countryName) {
		selectCountry.sendKeys(countryName);
	}

	public void selectCountryFromList(String countryName) {

		visibilityOfElementLocated(countryListLocator);
		WebElement desiredCountry = countryList.stream()
				.filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		desiredCountry.click();
	}
	
	public OrderSuccessPage placeOrder() throws InterruptedException {
		scrollWebPage(0, 700);
		placeOrder.click();
		OrderSuccessPage osp = new OrderSuccessPage(driver);
		return osp;
	}

}
