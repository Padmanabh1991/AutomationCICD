package Padmanabh.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Padmanabh.SeleniumFrameWorks.cartListPage;
import Padmanabh.SeleniumFrameWorks.orderPageList;

public class AbstractComponents {

	WebDriver driver;
	
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cartPage;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement clickorderPage;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public cartListPage goToCartPage()
	{
		cartPage.click();
		cartListPage cp=new cartListPage(driver);
		return cp;
	}
	
	public orderPageList goToOrderPage()
	{
		clickorderPage.click();
		orderPageList orp=new orderPageList(driver);
		return orp;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(2000);
	}
	
	
	public void windowsScroll()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,5000)");
	}
	
	
	
}
