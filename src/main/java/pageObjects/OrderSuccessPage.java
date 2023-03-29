package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSuccessPage {

	WebDriver driver;

	public OrderSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmMsg;

	public String verifySuccessMsg() {
		String successMsg = confirmMsg.getText();
		return successMsg;
//		Assert.assertEquals(successMsg, "THANKYOU FOR THE ORDER.");
	}
}
