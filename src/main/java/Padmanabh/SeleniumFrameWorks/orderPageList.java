package Padmanabh.SeleniumFrameWorks;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Padmanabh.AbstractComponent.AbstractComponents;

public class orderPageList extends AbstractComponents {

	WebDriver driver;
	public orderPageList(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderList;

	

	public boolean verifyOrderList(String prodName)
	{
		boolean flag=orderList.stream().anyMatch(orderList->orderList.getText().equalsIgnoreCase(prodName));
		return flag;
	
	}
	
}
