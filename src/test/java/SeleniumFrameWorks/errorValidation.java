package SeleniumFrameWorks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Padmanabh.SeleniumFrameWorks.ProductCatalog;
import Padmanabh.SeleniumFrameWorks.cartListPage;
import Padmanabh.SeleniumFrameWorks.checkOutPage;
import Padmanabh.SeleniumFrameWorks.orderPage;
import SeleniumFrameWorks.TestComponents.BaseTests;

//we have made changes
public class errorValidation extends BaseTests {

	@Test(groups = { "errorHandling" }, retryAnalyzer = SeleniumFrameWorks.TestComponents.Retry.class)
	public void submitOrder() throws IOException, InterruptedException {

		ProductCatalog pc = lp.getLogin("padmanabh.lakshmaiah@gmail.com", "Pady@9731");

		String errorMsg = lp.getErrorMessage();

		Assert.assertEquals(errorMsg, "Incorrect email or password.");

	}

	@Test
	public void verifyOrder() throws InterruptedException {
		String prodName = "ZARA COAT 3";
		ProductCatalog pc = lp.getLogin("padmanabh.lakshmaiah@gmail.com", "Pady@973100");

		List<WebElement> products = pc.getProductsList();

		pc.addProductToCart(prodName);

		cartListPage cp = pc.goToCartPage();

		boolean flag = cp.cartList(prodName);

		Assert.assertTrue(flag);

		checkOutPage ckoutp = cp.checkoutbtn();

		ckoutp.countryBox("Ind");

		ckoutp.selectCountryName();

		ckoutp.windowsScroll();

		orderPage op = ckoutp.gotoPlaceOrderPage();

		String confirmMessage = op.getOrderMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}
