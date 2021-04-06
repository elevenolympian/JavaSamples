package com.tudresden.seleniumwebdriver.seleniumsamples;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger; 



public class FirstElementTest {
	
	WebDriver driver; 
	private static final SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); 
	private static final Logger logger = Logger.getLogger(FirstElementTest.class.getName());
	
	//Some of the Chrome options
	//	start-maximized: Opens Chrome in maximize mode
	//	incognito: Opens Chrome in incognito mode
	//	headless: Opens Chrome in headless mode
	//	disable-extensions: Disables existing extensions on Chrome browser
	//	disable-popup-blocking: Disables pop-ups displayed on Chrome browser
	//	make-default-browser: Makes Chrome default browser
	//	version: Prints chrome browser version
	//	disable-infobars: Prevents Chrome from displaying the notification 'Chrome is being controlled by automated software

	
	@SuppressWarnings("deprecation")
	@BeforeClass
	public void setup() {
		DesiredCapabilities capabilities = null;
		//change the following line if you need for a new version of browser driver
		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver_linux64_version_88/chromedriver");
		capabilities = DesiredCapabilities.chrome(); 
		ChromeOptions chromeOptions = new ChromeOptions(); 
		Map<String, Object> chromePreferences = new HashMap<String, Object>();
		chromePreferences.put("credentials_enable_service", false); 
		chromeOptions.setExperimentalOption("prefs", chromePreferences); 
		chromeOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking", "no-sandbox", "disable-infobars", "headless");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability("applicationCacheEnabled", false);
		driver = new ChromeDriver(capabilities);
	}
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
	
	@BeforeMethod
	public void navigate() {
		if(driver != null) {
			driver.get("http://demo-store.seleniumacademy.com/");
		}
	}
	
	@Test
	@Ignore
	public void elementGetAttributesExample() {
		//WebElement Searchbox
		WebElement searchBox = driver.findElement(By.name("q")); 
		
		//write the result of searchBox.getAttribute("name")
		System.out.println("Name of the box is: " + searchBox.getAttribute("name"));
		
		//write the result of searchBox.getAttribute("id")
		System.out.println("Id of the box: " + searchBox.getAttribute("id"));
		
		//Write the result of searchBox.getAttribute("class")
		System.out.println("Class of the box: " + searchBox.getAttribute("class"));
		
		//Write the result of searchBox.getAttribute("placeholder")
		System.out.println("Placeholder of the box: " + searchBox.getAttribute("placeholder"));
		
	}
	
	@Test
	@Ignore
	public void elementSendKeysExample() {
		WebElement searchBox = driver.findElement(By.name("q")); 
		searchBox.sendKeys("Phones"); 
		searchBox.submit(); 
		AssertJUnit.assertEquals(driver.getTitle(),"Search results for: 'Phones'");
		
	}
	
	@Test
	@Ignore
	public void elementSendKeysCompositeExamples() {
		WebElement searchBox = driver.findElement(By.name("q")); 
		searchBox.sendKeys(Keys.chord(Keys.SHIFT, "phones"));
		searchBox.submit(); 
		
		AssertJUnit.assertEquals(driver.getTitle(), "Search results for: 'PHONES'");
	}
	
	@Test
	@Ignore
	public void elementClearExample() {
		WebElement searchBox = driver.findElement(By.name("q"));
		
		searchBox.sendKeys(Keys.chord(Keys.SHIFT, "phones")); 
		searchBox.clear(); 
	}
	
	@Test
	public void screenRecordingSeleniumBmwFactory() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@51.4107959,12.4326389,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "_screenshot.png"));
//			driver.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Test
	//https://www.google.com/maps/search/bmw+werk+leipzig/@51.4060825,12.4323055,21z/data=!5m1!1e1 -- West side
	public void screenRecordingSeleniumBmwFactoryWest() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@51.4060825,12.4323055,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "west" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	//https://www.google.com/maps/search/bmw+werk+leipzig/@51.4029899,12.4593806,21z/data=!5m1!1e1 -- East side
	public void screenRecordingSeleniumBmwFactoryEast() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@51.4029899,12.4593806,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "east" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	//https://www.google.com/maps/search/bmw+werk+leipzig/@51.4123015,12.4372443,21z/data=!5m1!1e1 -- North side
	public void screenRecordingSeleniumBmwFactoryNorth() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@51.4123015,12.4372443,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "north" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	//https://www.google.com/maps/search/bmw+werk+leipzig/@51.3966311,12.4484182,21z/data=!5m1!1e1 -- South side
	public void screenRecordingSeleniumBmwFactorySouth() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@51.3966311,12.4484182,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "south" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	public void screenRecordingSeleniumTeslaGuardHouse() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@39.5445002,-119.4541425,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "tesla_guard_house" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	public void screenRecordingSeleniumIntelSouth() throws InterruptedException {
		Thread.sleep(500);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.info(timestamp.toString());
		try {
			driver.get("https://www.google.com/maps/@45.534223,-122.9121688,21z/data=!5m1!1e1"); 
			java.io.File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new java.io.File("images/" + timestamp.toString() + "intel_south" + "_screenshot.png"));
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}




}
