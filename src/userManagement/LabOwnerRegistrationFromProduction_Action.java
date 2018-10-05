package userManagement;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabOwnerRegistrationFromProduction_Action extends TestBase {
	
	@Test
	public void LabOwnerRegistration() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String MailIdCreation = ExcelUtils.getCellData(1, 33);
		String FirstName = ExcelUtils.getCellData(1, 35);
		String LastName = ExcelUtils.getCellData(1, 34);
		
		/*System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mconnectedlab.com/authprod/");*/
		init();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='navBar']/ul/li[1]/a")).click();
		//getWebElement("ProdHomePageLoginButton").click();
		Thread.sleep(1000);
		getWebElement("ProdEmailAddress").sendKeys("prodtest_superuser@lab.com");
		Thread.sleep(1000);
		getWebElement("ProdPassword").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("ProdSigninButton").click();
		
		Thread.sleep(8000);
		getWebElement("ProdUserDropdownToggle").click();
		Thread.sleep(1000);
		getWebElement("ProdBatchUploadForBatchOwner").click();
		Thread.sleep(1000);
		getWebElement("ProdUploadFile").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\ProductionBatchUpload\\ProductionBatchUpload.exe");
		
		Thread.sleep(10000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.mailinator.com/");
		impliciteWait(5);
		getWebElement("MailinatorEmailField").sendKeys(MailIdCreation);
		Thread.sleep(1000);
		getWebElement("GoButton").click();
		Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		driver.findElement(By.linkText("Register to the lab")).click();
		
		Reporter.log("");
		Thread.sleep(7000);
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	    }
	    Thread.sleep(3000);
		getWebElement("RegistrationFirstName").sendKeys(FirstName);
		Thread.sleep(1000);
		getWebElement("RegistrationLastName").sendKeys(LastName);
		Thread.sleep(1000);
		getWebElement("RegistrationPassword").sendKeys("admin123");
		Thread.sleep(1000);
		Library lib = new Library();
		lib.Select("RegistrationCountry", 79);
		Thread.sleep(1000);
		getWebElement("RegistrationCaptcha").sendKeys("ab");
		Thread.sleep(15000);
		getWebElement("RegistrationTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegistrationAccept").click();
		Thread.sleep(2000);
		getWebElement("RegistrationCreateAccount").click();
	    
		Thread.sleep(15000);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
	    getWebElement("MalinatorInbox").click();
	    Thread.sleep(3000);
		getWebElement("FirstMessage").click();
		Thread.sleep(5000);
		driver.switchTo().frame("msg_body");
		driver.findElement(By.linkText("Click Here To Access")).click();
		
		Reporter.log("");
		Thread.sleep(7000);
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	    }
	    Thread.sleep(3000);
	    getWebElement("Enotebook.login.username").sendKeys(MailIdCreation);
	    Thread.sleep(1000);
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(1000);
		getWebElement("Enotebook.login.loginButton").click();
		Thread.sleep(3000);
		getWebElement("SelectLabName").click();
		Thread.sleep(3000);
		getWebElement("SetupYourProfilePageNextButton").click();
		Thread.sleep(15000);
		getWebElement("ConfigureYourLabNextButton").click();
		Thread.sleep(8000);
		getWebElement("TutorialTipsGotoMyLabButton").click();

		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();

	}

}
