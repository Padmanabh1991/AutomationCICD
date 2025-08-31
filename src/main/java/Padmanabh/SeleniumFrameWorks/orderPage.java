package Padmanabh.SeleniumFrameWorks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class orderPage extends AbstractComponents {
	
	WebDriver driver;
	public orderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	//String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String getOrderMessage()
	{
		String ordermsg=message.getText();
		return ordermsg;
	}
}
