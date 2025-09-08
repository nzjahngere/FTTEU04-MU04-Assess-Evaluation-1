package evaluationPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumSprintChallenge {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		
	}
		
	
	@Test(priority = 1)
	public void validateElements() throws InterruptedException {
		
		driver.get("https://demoqa.com/text-box");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("userName")).sendKeys("demouser");
		driver.findElement(By.id("userEmail")).sendKeys("demoEmail@gmail.com");
		driver.findElement(By.id("currentAddress")).sendKeys("Bangalore");
		driver.findElement(By.id("permanentAddress")).sendKeys("Karnataka");
		
		WebElement submitBtn = driver.findElement(By.id("submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[text()='Radio Button']")).click();
		driver.findElement(By.xpath("//label[@for='impressiveRadio']")).click();
		String actText = driver.findElement(By.className("text-success")).getText();
		Assert.assertEquals(actText, "Impressive");		
		
	}
	
	@Test(priority = 2)
	public void dynaWid() throws InterruptedException {
		
	    driver.get("https://demoqa.com/select-menu");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement selectValue = driver.findElement(By.id("withOptGroup"));
	    selectValue.click();

	    WebElement group1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'react-select-2-option-0-0') and text()='Group 1, option 1']")));
	    group1.click();

	    WebElement selectOne = driver.findElement(By.id("selectOne"));
	    selectOne.click();

	    WebElement drOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'react-select-3-option-0') and text()='Dr.']")));
	    drOption.click();

	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("//span[text()='Date Picker']")).click();
	    
	    Thread.sleep(1000);

	    WebElement datePicker = driver.findElement(By.id("datePickerMonthYearInput"));
	    datePicker.clear();
	    datePicker.sendKeys("10/15/2025");
	    
	}
	
	@Test(priority = 3)
	public void alertsFrames() throws InterruptedException {
		
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement ele = driver.findElement(By.id("alertButton"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

		driver.switchTo().alert().accept();
		
		WebElement elem = driver.findElement(By.id("confirmButton"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		Thread.sleep(500); 
		elem.click();
		
		driver.switchTo().alert().dismiss();
		
		String verifyTxt = driver.findElement(By.className("text-success")).getText();
		Assert.assertTrue(verifyTxt.contains("You selected"));
		
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Frames']")).click();
		
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		
		String readTxt = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();
		Assert.assertEquals(readTxt, "This is a sample page");
		driver.switchTo().defaultContent();
		
	}
	
	@AfterMethod
	public void closeDriver() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.close();
		
	}

}
