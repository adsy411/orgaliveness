package userManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabMembersFilterFunctionality_Action extends TestBase{
	
	@Test
	public void LabMembersFilterFunctionality() throws Exception{
		
		Reporter.log("Login to Application with specific user");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("devtestmsadmin1dec@20mm.eu");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();

		Thread.sleep(3000);
		getWebElement("LabMembersFilterIcon").click();
		/*Thread.sleep(3000);
		driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='filterForm']/div/ul/li/div/div")));*/
		Thread.sleep(3000);
		getWebElement("FilterLabOwnerCheckBox").click();
		Thread.sleep(1000);
		getWebElement("FilterApplyButton").click();
		Thread.sleep(1000);
		getWebElement("FilterCloseIcon").click();
		Thread.sleep(3000);
		driver.getPageSource().contains("Lab Owner");
		
		Thread.sleep(3000);
		getWebElement("LabMembersFilterIcon").click();
		Thread.sleep(3000);
		driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[@id='filterForm']/div/ul/li/div/div")));
		Thread.sleep(1000);
		getWebElement("FilterResetButton").click();
		
		Thread.sleep(3000);
		getWebElement("FilterLabManagerCheckBox").click();
		Thread.sleep(1000);
		getWebElement("FilterApplyButton").click();
		Thread.sleep(1000);
		getWebElement("FilterCloseIcon").click();
		Thread.sleep(3000);
		driver.getPageSource().contains("Lab Manager");
		
		Thread.sleep(3000);
		getWebElement("LabMembersFilterIcon").click();
		Thread.sleep(1000);
		getWebElement("FilterResetButton").click();
		
		
		
		
		
		
		
		
		
		
		
	}
}
