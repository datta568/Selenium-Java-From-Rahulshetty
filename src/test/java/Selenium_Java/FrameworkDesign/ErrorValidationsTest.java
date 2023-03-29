package Selenium_Java.FrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Cart;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" },retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		String userName = "datta567@gmail.com";
		String password = "Password123";

		landingPage.loginApplication(userName, password);

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());

	} 

	@Test(dependsOnMethods= {"loginErrorValidation"})
	public void cartErrorValidation() throws InterruptedException, IOException {

		String userName = "datta568@gmail.com";
		String password = "Password123";
		String productName = "ZARA COAT 3";

		ProductCatalouge productCatalouge = landingPage.loginApplication(userName, password);
		productCatalouge.getProducts();
		productCatalouge.getProductByName(productName);
		productCatalouge.addToCart(productName);

		Cart cart = productCatalouge.clickOnCartButton();
		cart.getCartItems();
		Boolean prodcutVerification = cart.verifyCartItem("ZARA COAT 33");
		Assert.assertFalse(prodcutVerification);

	}

}
