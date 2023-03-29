package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By allProducts = By.cssSelector(".mb-3");
	By productCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastContainerMsg = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	WebElement loadingIcon;

	public List<WebElement> getProducts() {
		visibilityOfElementLocated(allProducts);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addToCart(String productName) {
		getProductByName(productName).findElement(productCartButton).click();
		visibilityOfElementLocated(toastContainerMsg);
		invisibilityOf(loadingIcon);
	}

}
