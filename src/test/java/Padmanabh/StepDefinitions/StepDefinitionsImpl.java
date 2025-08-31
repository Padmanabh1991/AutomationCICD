package Padmanabh.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Padmanabh.SeleniumFrameWorks.ProductCatalog;
import Padmanabh.SeleniumFrameWorks.cartListPage;
import Padmanabh.SeleniumFrameWorks.checkOutPage;
import Padmanabh.SeleniumFrameWorks.landingPage;
import Padmanabh.SeleniumFrameWorks.orderPage;
import SeleniumFrameWorks.TestComponents.BaseTests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImpl extends BaseTests {

	public landingPage lp;
	public ProductCatalog pc;
	public cartListPage cp;
	checkOutPage ckoutp;
	orderPage op;

	@Given("I landed on E-commerce website")
	public void I_landed_on_Ecommerce_website() throws IOException 
	{
		lp = launchApplication();
	}

	@Given("^I logged in with username (.*) and password (.*)$")
	public void I_logged_with_username_and_password(String username, String password)
	{
		pc = lp.getLogin(username, password);
	}

	@When("^I add product (.*) name to cart$")
	public void I_add_product_name_to_cart(String prodName) throws InterruptedException
	{
		List<WebElement> products = pc.getProductsList();
		pc.addProductToCart(prodName);

		
	}

	@And("^checkout (.*) and submit the order$")
	public void checkout_and_submit_the_order(String prodName) 
	{
		
		cp = pc.goToCartPage();
		boolean flag = cp.cartList(prodName);

		Assert.assertTrue(flag);
		
		ckoutp = cp.checkoutbtn();

		ckoutp.countryBox("Ind");

		ckoutp.selectCountryName();

		ckoutp.windowsScroll();
		
	}
	
	@Then("I verify {string} the message is displayed on confirmation page")
	public void I_verify_the_message_is_displayed_on_confirmation_page(String string) throws InterruptedException
	{
		 op= ckoutp.gotoPlaceOrderPage();
		 String confirmMessage = op.getOrderMessage();

			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.quit();
	}
	
	
	@Then("I verify {string} message is displayed")
	public void I_verify_message_is_displayed(String string)
	{
		String errorMsg = lp.getErrorMessage();
		Assert.assertEquals(errorMsg, string);
		driver.quit();
	}
	

}
