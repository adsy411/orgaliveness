package production;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ProdtestScenarios_Action {
	
	@Test
	public void ProdtestScenarios() throws Exception{
		
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.brightlab.com/authprod/");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='navbar']/ul/li[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("loginForm:username")).sendKeys("deepakp414@mailinator.com");
		driver.findElement(By.id("loginForm:password")).sendKeys("admin123");
		driver.findElement(By.id("loginForm:validate")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.id("header-nameshortId1")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logoutForm:logoutLink")).click();
		Thread.sleep(3000);
		driver.quit();
		
	}
}
