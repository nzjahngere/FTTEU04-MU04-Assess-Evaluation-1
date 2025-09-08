package evaluationPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
		driver.findElement(By.id("userEmail")).sendKeys("demoEmail");
		driver.findElement(By.id("currentAddress")).sendKeys("Bangalore");
		driver.findElement(By.id("permanentAddress")).sendKeys("Karnataka");
		driver.findElement(By.id("submit")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Radio Button")).click();
		driver.findElement(By.id("impressiveRadio")).click();
		String actText = driver.findElement(By.className("text-success")).getText();
		Assert.assertEquals(actText, "Impressive");		
		
	}
	
	@Test(priority = 2)
	public void dynaWid() throws InterruptedException {
		
		driver.get("https://demoqa.com/accordian");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.linkText("Select Menu"));
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		
		WebElement drp = driver.findElement(By.name("Select Title"));
		Select drpdwn = new Select(drp);
		drpdwn.selectByVisibleText("Dr.");
		
		Thread.sleep(2000);
		
		WebElement drp2 = driver.findElement(By.name("Select Option"));
		Select drpdwn2 = new Select(drp2);
		drpdwn2.selectByVisibleText("Group 1, option 1");
		
		driver.findElement(By.linkText("Date Picker")).sendKeys("10/15/2025");
		
	}
	
	@Test(priority = 3)
	public void alertsFrames() throws InterruptedException {
		
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("alertButton")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.id("confirmButton")).click();
		driver.switchTo().alert().dismiss();
		
		String verifyTxt = driver.findElement(By.className("text-success")).getText();
		Assert.assertEquals(verifyTxt, "You selected");
		
		Thread.sleep(3000);

		driver.findElement(By.linkText("Frames")).click();
		
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
