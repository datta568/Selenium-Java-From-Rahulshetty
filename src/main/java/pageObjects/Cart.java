package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
	WebDriver driver;

	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;

	@FindBy(css = ".totalRow .btn")
	WebElement checkOut;

	public List<WebElement> getCartItems() {
		return cartItems;
	}

	public boolean verifyCartItem(String productName) {
		Boolean prodcutFlag = getCartItems().stream()
				.anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		return prodcutFlag;
	}

	public PaymentPage clickOnCheckOut() {
		checkOut.click();
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}
}
