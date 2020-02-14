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

public class BseParticipantTest {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Automation Softwares\\Automation Eclipse Workspace\\automation_bidding\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testBseParticipant() throws Exception {
		driver.get("https://bond.bseindia.com/");

		driver.findElement(By.id("txtLoginID")).sendKeys("ECLFINLTD4");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("Bseindia@345");
		driver.findElement(By.id("txtCaptcha")).clear();

		Thread.sleep(5000);
		// driver.findElement(By.id("txtCaptcha")).sendKeys("7321");
		driver.findElement(By.id("btnLogin")).click();

		Thread.sleep(500);
		driver.findElement(By.id("btnMPConfirm")).click();

		Thread.sleep(5000);
		//driver.findElement(By.id("btnSubmit")).click();


//		Thread.sleep(500);
//		driver.findElement(By.id("txt1")).click();
//		driver.findElement(By.id("txt1")).clear();
//		driver.findElement(By.id("txt1")).sendKeys("edelweiss");
//
//		driver.findElement(By.id("txt2")).click();
//		driver.findElement(By.id("txt2")).clear();
//		driver.findElement(By.id("txt2")).sendKeys("edelweiss");
//
//		driver.findElement(By.id("btnSubmit")).click();
		
		driver.findElement(By.xpath("//div[@id='UpdatePanel1']/table[2]/tbody/tr[3]/td/table/tbody/tr[3]/td/a/u")).click();

		driver.findElement(By.id("txtCoupon")).click();
		driver.findElement(By.id("txtCoupon")).clear();
		driver.findElement(By.id("txtCoupon")).sendKeys("7.2");
		
		driver.findElement(By.id("txtAmount")).click();
		driver.findElement(By.id("txtAmount")).clear();
		driver.findElement(By.id("txtAmount")).sendKeys("5");
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame |
		// relative=parent | ]]
		driver.findElement(By.linkText("Logout")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			// fail(verificationErrorString);
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
