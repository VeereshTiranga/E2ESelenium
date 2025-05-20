package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProds;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOut;
	
	
	public List<WebElement>  getAddedProductsListFromCart() {
		List<WebElement> cartProducts = cartProds;
		return cartProducts;
	}

	public boolean verifyProducts(String productName) {
		boolean match = getAddedProductsListFromCart().stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
		
	}
	
	public PaymentMethod checkout() {
		checkOut.click();
		PaymentMethod paymentMethod = new PaymentMethod(driver);
		return paymentMethod;
	}
}
