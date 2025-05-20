package ECommerceapplication.E2EEcommerceApplication;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.PaymentMethod;
import pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest{


        @Test(groups= {"Error"})
        public void loginErrorValidation() throws IOException, InterruptedException {
        

		landingPage.loginApplication("rockybhai123@gmail.com", "Bhai@1243");
		Assert.assertEquals(landingPage.errorMessage(), "Incorrect1 email or password.");

	}
        @Test(retryAnalyzer=Retry.class)
        public void productErrorValidation() throws IOException, InterruptedException {
        
		String productName = "ZARA COAT 3";
		String country = "India";

		ProductCatalogue productCatalogue = landingPage.loginApplication("rockybhai123@gmail.com", "Bhai@123");
			
		List<WebElement> productsList = productCatalogue.getProductsList();	
		productCatalogue.addProductToCart(productName);
	    CartPage cartPage = productCatalogue.goToCartPage();	
		boolean verification = cartPage.verifyProducts("Zara coat 33");	
	    Assert.assertFalse(verification);
		
	}

	

		

}
