package pageLibrary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.TestBase;

public class Library extends TestBase{
	
	
	@Test
	public void loginToApplication() throws Exception {
		
		Reporter.log("Login with valid Credential");
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.name("loginForm:username")).sendKeys(
				repository.getProperty("username"));
		driver.findElement(By.name("loginForm:password")).sendKeys(
				repository.getProperty("password"));
		driver.findElement(By.id("signInButton")).click();
		impliciteWait(20);
	}
	
	@Test
	public void loginToApplication(String userName, String password) throws Exception {
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys(userName);
		getWebElement("Enotebook.login.password").sendKeys(password);
		getWebElement("Enotebook.login.loginButton").click();
		impliciteWait(5);
	}
	
	public void InventoryMouseOver() throws Exception {
		
		Reporter.log("Mouse over to Inventory Home sub module");
		WebElement hoverElement = driver.findElement(By
				.xpath(".//*[@id='sidebar_inventory']/a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).perform();
		Thread.sleep(5000);
		
	
	}

	public void Logout() {
	
	WebElement hoverElement = driver.findElement(By
			.cssSelector("#header_dropdown_user_link"));
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();
	driver.findElement(By.linkText("Logout")).click();
	driver.quit();
	}
	
	public void Select(String locator, int index) throws Exception {
		Select category = new Select(getWebElement(locator));
		category.selectByIndex(index);
	}
	
	public String Date()
	{
	DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	String Date = DateFormat.format(now);
	//System.out.println("Todays's date is "+Date);
	Date = Date.replaceAll("[^a-zA-Z0-9_-]", "");
	//System.out.println("Unique Number is "+Date);
	return Date;

	}
	
	public void SelectByVisibleText(String locator, String text) throws Exception
	{
		String item = GetItemFromSelectList(locator, text);
		Select category = new Select(getWebElement(locator));
		category.selectByVisibleText(item);
		Thread.sleep(1000);
	}
	
	public void SelectByValue(String locator, String text) throws Exception
	{
		String item = GetItemFromSelectList(locator, text);
		Select category = new Select(getWebElement(locator));
		category.selectByValue(item);
		Thread.sleep(1000);
	}

	public Boolean VerifySelectList(String locator, String text)throws Exception
	{
		explicitWaitForElement(locator);
		Select Select1 = new Select(getWebElement(locator));
		List<WebElement> elementCount = Select1.getOptions();
		int ListSize = elementCount.size();
		Boolean itemFound = false;
		for(int i = 0; i<ListSize ; i++)
		{
			String SelectedItem = elementCount.get(i).getText();
			SelectedItem = SelectedItem.trim();
			if(SelectedItem.equalsIgnoreCase(text))
			{
				itemFound = true;
				break;
			}
			else
				itemFound = false;
		}
		return itemFound;			
	}
	
	public String GetItemFromSelectList(String locator, String text)throws Exception
	{
		Select Select1 = new Select(getWebElement(locator));
		List<WebElement> elementCount = Select1.getOptions();
		int ListSize = elementCount.size();
		String itemFound = null;
		for(int i = 0; i<ListSize ; i++)
		{
			String SelectedItem = elementCount.get(i).getText().trim();
			if(SelectedItem.equalsIgnoreCase(text))
			{
				itemFound = SelectedItem;
				break;
			}
		}
		return itemFound;			
	}
}
