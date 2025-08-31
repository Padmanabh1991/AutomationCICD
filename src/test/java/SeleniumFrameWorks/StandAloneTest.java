package SeleniumFrameWorks;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Padmanabh.SeleniumFrameWorks.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException  {
		
		String prodName="ZARA COAT 3";

		WebDriverManager.chromedriver().setup();

		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");
		
		
		landingPage lp=new landingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("padmanabh.lakshmaiah@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Pady@973100");
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartlists=driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean flag=cartlists.stream().anyMatch(cartlist->cartlist.getText().equalsIgnoreCase(prodName));
		
		Assert.assertTrue(flag);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		
		driver.findElement(By.cssSelector(".list-group-item:nth-child(3)")).click();
		
		
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,5000)");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".action__submit")))).click();
		
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		

	}
}
