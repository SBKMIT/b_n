package com.edelweiss.app.junit;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NseParticipantTest {

	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		    System.setProperty("webdriver.chrome.driver", "D:\\Automation Softwares\\Automation Eclipse Workspace\\automation_bidding\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			baseUrl = "https://www.google.com/";
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testNseProp() throws Exception {
	    driver.get("https://www.nse-ebp.com/ebp/rest/dashboard");
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.linkText("Place Bid")).click();
	    driver.findElement(By.xpath("(//input[@id='value'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@id='value'])[3]")).sendKeys("5");
	    driver.findElement(By.xpath("(//input[@id='bid'])[3]")).click();
	    driver.findElement(By.xpath("(//input[@id='bid'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@id='bid'])[3]")).sendKeys("9");
	    driver.findElement(By.id("clientType")).click();
	   // new Select(driver.findElement(By.id("clientType"))).selectByVisibleText("Proprietory");
	    driver.findElement(By.id("clientType")).click();
	    driver.findElement(By.id("btnSave")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[117]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[119]")).click();
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	    //  fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
