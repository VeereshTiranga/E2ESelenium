package cucumberStepdefinition;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.PaymentMethod;
import pageObjects.ProductCatalogue;


public class StepDefinition extends BaseTest{
	
	LandingPage landingPage;
	ProductCatalogue productCatalogue ;
	ConfirmationPage confirmationPage;
	String country ="India";
	
	@Given("I landed on the application")
	public void i_landed_on_the_application() throws Exception {
		landingPage = launchApplication();
	}
	
//	Given I logged in using userName <userName> and password <password>
	
	@Given("^I logged in using userName (.+) and password (.+)$")
	public void i_logged_in_using_user_name_rockybhai123_gmail_com_and_password_bhai(String userName , String password) {
		
		productCatalogue = landingPage.loginApplication(userName, password);
	}
	
	//When I added the product <productName> to cart
	@When("^I added the product (.+) to cart$")
	public void added_the_product_to_cart(String productName) throws Exception {
		
		List<WebElement> productsList = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}
	
//	And I checkout the product <productName>  and placed the order
	@When("^I checkout the product (.+)  and placed the order$")
	public void checkout_the_product_and_place_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean verification = cartPage.verifyProducts(productName);
		Assert.assertTrue(verification);
		PaymentMethod paymentMethod = cartPage.checkout();
		paymentMethod.selectCountry(this.country);
		confirmationPage= paymentMethod.placeOrder();
	}
	
	//Then "Thankyou for the order." message is displayed
	@Then("{string} message is displayed")
	public void message_is_displayed(String messageCon) {
		String message = confirmationPage.finalMessage();
		Assert.assertTrue(message.equalsIgnoreCase(messageCon));
		driver.close();
	}
	
	@Then("{string} error message is displayed")
	public void  error_message_is_displayed(String errorMessage) {
		Assert.assertEquals(landingPage.errorMessage(), errorMessage);
		driver.close();
	}
	

}
