package com.edelweiss.app.nse;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NseParticipantBidding {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Automation Softwares\\Automation Eclipse Workspace\\automation_bidding\\driver\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("debuggerAddress", "localhost:9014");
		driver = new ChromeDriver(option);
	    

		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

		Thread.sleep(500);
		driver.findElement(By.linkText("Place Bid")).click();

		Thread.sleep(500);
		//driver.findElement(By.id("value")).click();
		//driver.findElement(By.id("value")).clear();
		//driver.findElement(By.id("value")).sendKeys("1");
		
		
		driver.findElement(By.xpath("//*[@id='value']")).click();
		driver.findElement(By.xpath("//*[@id='value']")).clear();
		driver.findElement(By.xpath("//*[@id='value']")).sendKeys("1");
		
		Thread.sleep(500);
		driver.findElement(By.id("btnSave")).click();

		Thread.sleep(500);
		driver.switchTo().activeElement();
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='bootstrap-confirm-box-modal']/div/div/div[3]/button[1]")).click();


	}

}
