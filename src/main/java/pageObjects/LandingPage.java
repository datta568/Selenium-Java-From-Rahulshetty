package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id ="userEmail")
	WebElement userEmail;
	
	@FindBy(id ="userPassword")
	WebElement userPassword;
	
	@FindBy(id ="login")
	WebElement submit;
	
	@FindBy(css="[class*=ng-trigger-flyInOut]")
	WebElement errorMsg;
	
	public ProductCatalouge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalouge pc = new ProductCatalouge(driver);
		return pc;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMsg() {
		visibilityOfElementLocated(errorMsg);
		return errorMsg.getText();
	}

}
