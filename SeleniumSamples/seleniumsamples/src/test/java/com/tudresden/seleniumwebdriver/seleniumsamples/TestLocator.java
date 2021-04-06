package com.tudresden.seleniumwebdriver.seleniumsamples;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import java.util.logging.*;

public class TestLocator {

	WebDriver driver; 
	private static WebDriver SINGLETON_INSTANCE = null; 
	private static final Logger logger = Logger.getLogger(TestLocator.class.getName());
	
	//@BeforeClass: The annotated method will be run before the first test method in the current class is invoked.
	//setup function is necessary 
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver_linux64_version_88/chromedriver"); 
		//singleton pattern necessary 
//		driver = new ChromeDriver();
		driver = getInstance();
	}
	
	public static WebDriver getInstance() {
		if(SINGLETON_INSTANCE == null) {
			synchronized(TestLocator.class) {
				if(SINGLETON_INSTANCE == null) { //double locked - synchronized
					SINGLETON_INSTANCE = new ChromeDriver();
				}
			}
		}
		return SINGLETON_INSTANCE; 
	}
	
	@BeforeMethod
	//@BeforeMethod: The annotated method will be run before each test method.
	public void navigate() {
		driver.get("http://demo-store.seleniumacademy.com/"); //Test Website
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void getIdLocatorExample() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("Bags");
		searchBox.submit(); 
		AssertJUnit.assertEquals(driver.getTitle(), "Search results for: 'Bags'");
	}
	
	@Test
	public void getClasNameLocatorExample() {
		WebElement searchBox = driver.findElement(By.id("search"));
		searchBox.sendKeys("Electronics");
		WebElement searchButton = driver.findElement(By.className("search-button")); 
		searchButton.click(); 
		
//		 driver.getTitle() = The title of the current page, with leading and trailing whitespace stripped, or null
//		   if one is not already set
		AssertJUnit.assertEquals(driver.getTitle(), "Search results for: 'Electronics'");
	}
	
	@Test
	public void getLinkTextLocatorExample() {
		//A Link Text in Selenium is used to identify the hyperlinks on a web page. 
		//It is determined with the help of an anchor tag. For creating the hyperlinks on a web page, we can use an anchor tag followed by the link Text.
		WebElement accountLink = driver.findElement(By.linkText("MY ACCOUNT")); 
		accountLink.click(); 
		
		AssertJUnit.assertEquals(driver.getTitle(), "Customer Login");

	}
	
	@Test
	public void getPartialLinkTextLocator() {
//		Accessing links using a portion of their link text is done using the By.partialLinkText() method. 
//		If you specify a partial link text that has multiple matches, only the first match will be accessed. 
		WebElement partialLinkOrders = driver.findElement(By.partialLinkText("PRIVACY")); 
		partialLinkOrders.click();
		AssertJUnit.assertEquals(driver.getTitle(), "Privacy Policy");
	}
	
	@Test
	public void getTagNameLocatorExample() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		logger.info("Found links: " + links.size()); 
		links.stream().filter(element -> element.getText().length() > 0)
					.forEach(element -> logger.info(element.getText()));
	}
	
	@Test
	public void byXPathLocatorExample() {

//	XPath Locators	Find different elements on web page
//	ID	To find the element by ID of the element
//	Classname	To find the element by Classname of the element
//	Name	To find the element by name of the element
//	Link text	To find the element by text of the link
//	XPath	XPath required for finding the dynamic element and traverse between various elements of the web page
//	CSS path	CSS path also locates elements having no name, class or ID.

		WebElement element = driver.findElement(By.xpath("//*[@id='search']")); 
		
		element.sendKeys("Bags");
		element.submit();
		
		AssertJUnit.assertEquals(driver.getTitle(),"Search results for: 'Bags'");
	}
	
	@Test
	public void getCssSelectorLocatorExample() {
		WebElement searchBox = driver.findElement(By.cssSelector("#search")); 
//		sendkeys() is a method in Selenium that allows QAs to type content automatically into an editable field while executing any tests for forms. 
//		These fields are web elements that can be identified using locators like element id, name, class name, etc.
		searchBox.sendKeys("Bags");
		searchBox.submit();
		
		AssertJUnit.assertEquals(driver.getTitle(), "Search results for: 'Bags'");
	}
	
	@Test
	public void testUrlNavigation() {
		//navigate to the url
		driver.get("https://www.sozcu.com.tr/");
		
		//validate page title
		AssertJUnit.assertEquals(driver.getTitle(), "Sözcü Gazetesi");
	}
	
	@Test
	public void searchProduct() {
		//enter search string in the by.name tag of id
		WebElement searchBox = driver.findElement(By.name("q")); 
		
		searchBox.sendKeys("Phones");
		WebElement searchButton = driver.findElement(By.className("search-button")); 
		searchButton.click(); 
		
		AssertJUnit.assertEquals(driver.getTitle(), "Search results for: 'Phones'");
	}
}
