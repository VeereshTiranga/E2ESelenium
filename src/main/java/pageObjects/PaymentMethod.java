package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class PaymentMethod extends AbstractComponent{
	
	WebDriver driver;
	
	public PaymentMethod(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input")
	WebElement enterCountry;
	
	@FindBy(css=".list-group button")
	List<WebElement> countryList;
	
	@FindBy(xpath="//a[normalize-space(text())='Place Order']")
	WebElement placeOrd;
	
	
	
	By countryBy = By.cssSelector("span");
			
	public void selectCountry(String country) {
		enterCountry.sendKeys(country);
		List<WebElement> countries = countryList;
		WebElement selectedCountry = countries.stream().filter(c->c.findElement(countryBy).getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		selectedCountry.click();
	}
	

	
	public ConfirmationPage placeOrder() {
		placeOrd.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	
	
}
