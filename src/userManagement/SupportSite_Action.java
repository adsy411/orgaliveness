package userManagement;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class SupportSite_Action extends TestBase{
	
	@Test
	public void SupportSite() throws Exception{
		
		init();
		Reporter.log("Click on Login page -> Support Site");
		impliciteWait(5);
		getWebElement("HomePageLoginButton").click();
		impliciteWait(5);
		getWebElement("SupportSite").click();
		
		Reporter.log("User should be navigate to a new tab -> M Connected Lab:Home");
		Thread.sleep(8000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		//newTab.remove(oldTab);
		driver.switchTo().window(newTab.get(0));
		
		Reporter.log("Close the Application");
		Thread.sleep(3000);
		driver.quit();
	}
}
