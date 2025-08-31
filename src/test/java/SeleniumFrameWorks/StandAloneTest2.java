package SeleniumFrameWorks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Padmanabh.SeleniumFrameWorks.ProductCatalog;
import Padmanabh.SeleniumFrameWorks.cartListPage;
import Padmanabh.SeleniumFrameWorks.checkOutPage;
import Padmanabh.SeleniumFrameWorks.orderPage;
import Padmanabh.SeleniumFrameWorks.orderPageList;
import SeleniumFrameWorks.JsonData.DataReader;
import SeleniumFrameWorks.TestComponents.BaseTests;

public class StandAloneTest2 extends BaseTests {

	public String prodName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalog pc = lp.getLogin(input.get("email"), input.get("pwd"));

		List<WebElement> products = pc.getProductsList();

		pc.addProductToCart(input.get("prodName"));

		cartListPage cp = pc.goToCartPage();

		boolean flag = cp.cartList(input.get("prodName"));

		Assert.assertTrue(flag);

		checkOutPage ckoutp = cp.checkoutbtn();

		ckoutp.countryBox("Ind");

		ckoutp.selectCountryName();

		ckoutp.windowsScroll();

		orderPage op = ckoutp.gotoPlaceOrderPage();

		String confirmMessage = op.getOrderMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderVerifyInList() {
		ProductCatalog pc = lp.getLogin("padmanabh.lakshmaiah@gmail.com", "Pady@973100");

		orderPageList orp = pc.goToOrderPage();

		Assert.assertTrue(orp.verifyOrderList(prodName));

	}
	
	
	

	@DataProvider
	public Object[][] getData() throws IOException 
	{
	
		List<HashMap<String , String>> data=getJsonData(".//src//test//java//SeleniumFrameWorks//JsonData//PurchaseOrder.json");

		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
