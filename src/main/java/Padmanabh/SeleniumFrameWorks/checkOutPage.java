package Padmanabh.SeleniumFrameWorks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class checkOutPage extends AbstractComponents {

	WebDriver driver;
	public checkOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".form-group input")
	WebElement country;
	
	//driver.findElement(By.cssSelector(".list-group-item:nth-child(3)")).click();
	
	@FindBy(css=".list-group-item:nth-child(3)")
	WebElement countryName;
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	@FindBy(css=(".action__submit"))
	WebElement placeorder;
	
	
	
	public void countryBox(String name)
	{
		country.sendKeys(name);
	}
	
	public void selectCountryName()
	{
		
		countryName.click();
	}
	
	public orderPage gotoPlaceOrderPage() throws InterruptedException 
	{
		Thread.sleep(2000);
		placeorder.click();
		
		orderPage op=new orderPage(driver);
		return op;
		
		
	}
	
	
}
