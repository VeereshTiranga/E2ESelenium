package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	OrdersPage ordersPage;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css="tr[class='ng-star-inserted'] td:nth-of-type(2)")
	List<WebElement> orders;
	


	public boolean verifyProductsFromOrders(String productName) {
		goToOrdersPage();
		boolean match = orders.stream().anyMatch(orderedProduct->orderedProduct.getText().equals(productName));
		return match;
		
	}
	

}
