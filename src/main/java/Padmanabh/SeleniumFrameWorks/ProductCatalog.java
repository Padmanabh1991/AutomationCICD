package Padmanabh.SeleniumFrameWorks;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	@FindBy(css=".ng-animating")
	WebElement invisible;
	
	
	
	By productsBy=By.cssSelector(".mb-3");
	By addcart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	
	public WebElement getProductName(String prodName)
	{
		WebElement prod= getProductsList().stream().filter(product->product
				.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String prodName) throws InterruptedException 
	{
		WebElement prod= getProductName(prodName);
		prod.findElement(addcart).click();
		
		waitForElementToAppear(toastMessage);
		
		waitForElementToDisappear(invisible);
		
		
	}
	
}
