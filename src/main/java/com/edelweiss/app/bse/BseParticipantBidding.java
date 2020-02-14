package com.edelweiss.app.bse;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Timer;

import com.edelweiss.app.dto.IssueDto;

public class BseParticipantBidding extends TimerTask {

	public WebDriver driver;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	String oldDate ;

	public void loadCromeDriver() {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Automation Softwares\\Automation Eclipse Workspace\\automation_bidding\\driver\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("debuggerAddress", "localhost:9014");
		driver = new ChromeDriver(option);

	}

	@Override
	public void run() {
		try {
			//placeBidByExcelData();
			checkFileUpdated();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		BseParticipantBidding bseParticipantBidding = new BseParticipantBidding();
		
		bseParticipantBidding.setBiddingTiming();
		// bseParticipantBidding.placeBidByExcelData();

		//bseParticipantBidding.checkFileUpdated();

	}

	public void setBiddingTiming() {

		try {

			DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			//Date date = dateFormatter.parse(dateAndTime);
			Timer timer = new Timer();
			timer.schedule(new BseParticipantBidding(),  0, 30000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void placeBidByExcelData() throws InterruptedException {

		loadCromeDriver();

		driver.switchTo().frame("IFRAME4");

		List<IssueDto> issueDtos = excelRead();

		for (IssueDto issueDto : issueDtos) {

			// setBiddingTiming(issueDto.getDate()+" "+issueDto.getTime());

			LocalDateTime now = LocalDateTime.now();
			System.out.println("Start Time" + dtf.format(now));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PLACE ORDER")));

			clickPlaceOrderLink(issueDto.getIssueName());

			if (issueDto.getCoupon() != null) {

				WebDriverWait wait1 = new WebDriverWait(driver, 30);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCoupon']")));
				driver.findElement(By.xpath("//*[@id='txtCoupon']")).click();
				driver.findElement(By.xpath("//*[@id='txtCoupon']")).clear();
				driver.findElement(By.xpath("//*[@id='txtCoupon']")).sendKeys("1");

			}

			WebDriverWait wait2 = new WebDriverWait(driver, 30);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtAmount']")));

			driver.findElement(By.xpath("//*[@id='txtAmount']")).click();
			driver.findElement(By.xpath("//*[@id='txtAmount']")).clear();
			driver.findElement(By.xpath("//*[@id='txtAmount']")).sendKeys(String.valueOf(issueDto.getBiddingAmount()));

			WebDriverWait wait3 = new WebDriverWait(driver, 30);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnSubmit']")));
			driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();

			WebDriverWait wait4 = new WebDriverWait(driver, 30);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnMPConfirm']")));
			driver.findElement(By.xpath("//*[@id='btnMPConfirm']")).click();

			WebDriverWait wait5 = new WebDriverWait(driver, 30);
			wait5.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();

			LocalDateTime now1 = LocalDateTime.now();
			System.out.println("End  Time" + dtf.format(now1));

		}

	}

//	public String format(long time) {
//		
//		String newDate = new String();
//
//		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//		System.out.println(sdf.format(new Date(time)));
//		
//		newDate = sdf.format(new Date(time));
//
//	//	System.exit(0);
//
//		return newDate;
//	}

	public void checkFileUpdated() throws InterruptedException {

		String newDate = new String();

		File file = new File("D:\\BseData.xlsx");

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		newDate = sdf.format(new Date(file.lastModified()));

		System.out.println("New Date " + newDate);

		if (newDate.equals(oldDate)) {

			System.out.println("Un updated File");
			System.exit(0);
			
			
		} else {
			
			List<IssueDto> issueDtos = excelRead();

			oldDate = newDate;

			System.out.println("OLD DATE " + oldDate);
			System.out.println("updated File");
		}

		// System.out.println( + file.lastModified());

	}

	public void clickPlaceOrderLink(String issueName) {

		WebElement textDemo = driver.findElement(By.xpath("//*[contains(text(), '" + issueName + "')]"));

		WebElement parent = textDemo.findElement(By.xpath("./.."));

		WebElement tdElement = parent.findElement(By.xpath("./.."));

		WebElement trElement = tdElement.findElement(By.xpath("./.."));

		if (trElement.isDisplayed()) {
			WebElement issueLink = trElement.findElement(By.linkText("PLACE ORDER"));
			issueLink.click();
		}
	}

	public List<IssueDto> excelRead() {

		List<IssueDto> dtos = new ArrayList<IssueDto>();

		try {

			File file = new File("D:\\BseData.xlsx");

			FileInputStream fileStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				IssueDto issueDto = new IssueDto();
				Row row = sheet.getRow(i);

				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (j == 0) {
						issueDto.setIssueName(cell.getStringCellValue());
					}
					if (j == 1) {
						issueDto.setBiddingAmount(new BigDecimal(cell.getNumericCellValue()));
					}
					if (j == 2) {
						issueDto.setCoupon(new BigDecimal(cell.getNumericCellValue()));
					}
				}
				dtos.add(issueDto);
			}

			fileStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;

	}

	public String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public String getCurrentWindowTitle() {
		String windowTitle = driver.getTitle();
		return windowTitle;
	}

}
