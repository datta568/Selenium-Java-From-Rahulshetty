package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Cart;
import pageObjects.LandingPage;
import pageObjects.OrderSuccessPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;

public class StepDefintion extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public OrderSuccessPage orderSuccessPage;

	@Given("I landed on the Ecommerce page")
	public void i_Landed_On_EcommercePage() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Login to application with username (.+) and password (.+)$")
	public void login_Application_With_Username_Password(String userName, String password) {
		productCatalouge = landingPage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_Add_Product_To_Cart(String productName) {
//		 List<WebElement> products = productCatalouge.getProducts();
		productCatalouge.addToCart(productName);
	}

	@And("^checkout (.+) from cart and submit the order$")
	public void checkout_Submit_Order(String productName) throws InterruptedException {
		Cart cart = productCatalouge.clickOnCartButton();
		cart.getCartItems();
//		Boolean prodcutVerification = cart.verifyCartItem(productName);
//		Assert.assertTrue(prodcutVerification);

		PaymentPage paymentPage = cart.clickOnCheckOut();
		paymentPage.inputCountry("Ind");
		paymentPage.selectCountryFromList("India");

		orderSuccessPage = paymentPage.placeOrder();
	}

	@Then("^\"([^\\\"]*)\" message will be displayed in the confirmation page$")
	public void verify_Confirmation_Message(String confMsg) {
		String successMsg = orderSuccessPage.verifySuccessMsg();
		Assert.assertEquals(successMsg, confMsg);
		closeApplication();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String errMsg) throws Throwable {
		Assert.assertEquals(errMsg, landingPage.getErrorMsg());
		closeApplication();
	}

}
