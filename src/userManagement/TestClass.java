package userManagement;

import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;
//import utills.ExcelWrite;

public class TestClass extends TestBase{
	
	@Test
	public void InviteLabManagerCheckJoinLabExists() throws Exception{
		
		Reporter.log("Login to Application with specific User Five");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("newmanager1653@amail.club");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();
		
		Reporter.log("Open Nadamail in New tab and create new email ID");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://ae1c-bioinfocloud-slb01/devtest/faces/switch_labs.xhtml");
		
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id='switchLabForm:usersListTable']/div/div/div[2]/div/table/tbody/tr[2]/td[3]/div/a")).click();
		//getWebElement("JoinLabButton").click();
		
		
	}
}
