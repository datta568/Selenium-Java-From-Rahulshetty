package Selenium_Java.FrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Cart;
import pageObjects.OrderSuccessPage;
import pageObjects.OrdersPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> data) throws InterruptedException, IOException {

		String countryCode = "Ind";
		String countryName = "India";

		ProductCatalouge productCatalouge = landingPage.loginApplication(data.get("userEmail"), data.get("password"));
		productCatalouge.getProducts();
		productCatalouge.getProductByName(data.get("productName"));
		productCatalouge.addToCart(data.get("productName"));

		Cart cart = productCatalouge.clickOnCartButton();
		cart.getCartItems();
		Boolean prodcutVerification = cart.verifyCartItem(data.get("productName"));
		Assert.assertTrue(prodcutVerification);

		PaymentPage paymentPage = cart.clickOnCheckOut();
		paymentPage.inputCountry(countryCode);
		paymentPage.selectCountryFromList(countryName);

		OrderSuccessPage orderSuccessPage = paymentPage.placeOrder();
		String successMsg = orderSuccessPage.verifySuccessMsg();
		Assert.assertEquals(successMsg, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void verifyOrderHistory(HashMap<String, String> data) throws InterruptedException {
		ProductCatalouge productCatalouge = landingPage.loginApplication(data.get("userEmail"), data.get("password"));
		OrdersPage ordersPage = productCatalouge.clickOnOrdersButton();
		Assert.assertTrue(ordersPage.verifyOrder(data.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		**********METHOD 1**********
//		Object[][] obj = new Object[2][2];
//		obj[0][0] = "datta568@gmail.com";
//		obj[0][1] = "Password123";
//		obj[1][0] = "datta568@gmail.com";
//		obj[1][1] = "Password123";

//		**********METHOD 2**********
//		return new Object[][] { { "datta568@gmail.com", "Password123", "IPHONE 13 PRO" },
//				{ "datta568@gmail.com", "Password123", "ZARA COAT 3" } };

//		**********METHOD 3**********
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("userEmail", "datta568@gmail.com");
//		map.put("password", "Password123");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("userEmail", "datta568@gmail.com");
//		map1.put("password", "Password123");
//		map1.put("productName", "IPHONE 13 PRO");
//		
//		return new Object[][] { { map }, { map1 } };

//		**********METHOD 4**********

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\PurchaseData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
