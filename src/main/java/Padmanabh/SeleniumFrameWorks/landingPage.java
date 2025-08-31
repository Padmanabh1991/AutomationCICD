package Padmanabh.SeleniumFrameWorks;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class landingPage extends AbstractComponents {

	WebDriver driver;
	
	public landingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//		WebElement email1=driver.findElement(By.id("userEmail"));
	
	
	@FindBy(id="userEmail")
	WebElement email;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement pwd;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	
	//div[aria-label='Incorrect email or password.']
	////div[@aria-label='Incorrect email or password.']
	//div[aria-label='Incorrect email or password.']
	
	//ng-tns-c4-7 toast-message ng-star-inserted
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	public ProductCatalog getLogin(String mail,String password)
	{
		email.sendKeys(mail);		
		pwd.sendKeys(password);
		submit.click();	
		ProductCatalog pc=new ProductCatalog(driver);
		return pc;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	
	
	
}
