package SeleniumFrameWorks.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Padmanabh.SeleniumFrameWorks.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	
	public WebDriver driver;
	public landingPage lp;
	public WebDriver initializeDriver() throws IOException 
	
	{
		
		
		//properties class
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(".//src//main//java//Padmanabh//resources//globaldata.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		//prop.getProperty("browser");
		
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			
				
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
		
		
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		//read json to string
	String jsonContent=	FileUtils.readFileToString(new File(filePath),
			StandardCharsets.UTF_8);
	
	//Convert string to hashmap-jackson databind
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver ) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File dstn=new File(".//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, dstn);
		return ".//reports//"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public landingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		
		lp = new landingPage(driver);
		lp.goTo();
		
		return lp;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}

}
