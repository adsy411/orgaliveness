package pageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testBase.TestBase;

public class LoginPage extends TestBase{
	
public void loginToApplication() throws Exception{
		
		WebElement clicklogin = getWebElement("Enotebook.clicklogin.username");
		clicklogin.click();
		WebElement username = getWebElement("Enotebook.login.username");
		username.sendKeys(repository.getProperty("username"));
		WebElement password = getWebElement("Enotebook.login.password");
		password.sendKeys(repository.getProperty("password"));
		WebElement login = getWebElement("Enotebook.login.loginButton");
		login.click();
		impliciteWait(20);
	}
	
public void Logout() throws Exception{
	
	/*WebElement hoverElement = getWebElement("Enotebook.mouseover.username");
	Actions builder = new Actions(driver);
	builder.moveToElement(hoverElement).perform();*/
	getWebElement("LogoutRightButton").click();
	Thread.sleep(2000);
	getWebElement("Enotebook.logout").click();
	Thread.sleep(2000);
	driver.quit();
}
	

}
