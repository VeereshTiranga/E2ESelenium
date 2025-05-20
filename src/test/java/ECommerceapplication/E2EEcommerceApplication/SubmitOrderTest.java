package ECommerceapplication.E2EEcommerceApplication;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrdersPage;
import pageObjects.PaymentMethod;
import pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName;


//checking for github webhook
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> data) throws IOException, InterruptedException {

		// productName = "ZARA COAT 3";
		String country = "India";

	
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("email"), data.get("password"));

		List<WebElement> productsList = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(data.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean verification = cartPage.verifyProducts(data.get("productName"));
		Assert.assertTrue(verification);
		PaymentMethod paymentMethod = cartPage.checkout();
		paymentMethod.selectCountry(country);
		ConfirmationPage confirmationPage = paymentMethod.placeOrder();
		String message = confirmationPage.finalMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

		
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("rockybhai123@gmail.com", "Bhai@123");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductsFromOrders("ZARA COAT 3"));
	}



	// data provider with hashmap from json file
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getDataFromJson(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\data.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// data provider with hashmap
//	@DataProvider
//	public Object[][] getData() {
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "rockybhai123@gmail.com");
//		map.put("password", "Bhai@123");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("email", "vt123@gmail.com");
//		map2.put("password", "Vt@123456");
//		map2.put("productName", "ADIDAS ORIGINAL");
//		
//	    return new Object[][] {{map},{map2}};
//	}

	// data provider with object
//	@DataProvider
//	public Object[][] getData() {
//		return new Object [][] {{"rockybhai123@gmail.com" , "Bhai@123", "ZARA COAT 3"}, {"vt123@gmail.com","Vt@123456","ADIDAS ORIGINAL"}};
//	}

}
