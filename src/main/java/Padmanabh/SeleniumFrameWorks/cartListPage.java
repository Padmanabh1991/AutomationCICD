package Padmanabh.SeleniumFrameWorks;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class cartListPage extends AbstractComponents {

	WebDriver driver;
	public cartListPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> cartlists=driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css=".cartSection h3")
	List<WebElement> cartlist;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css=".totalRow button")
	WebElement checkout;

	public boolean cartList(String prodName)
	{
		boolean flag=cartlist.stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(prodName));
		return flag;
	
	}
	
	public checkOutPage checkoutbtn()
	{
		checkout.click();
		checkOutPage ckoutp= new checkOutPage(driver);
		return ckoutp;
	}
	
	
	
	
	





}
