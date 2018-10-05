package userManagement;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.LoginPage;
import testBase.TestBase;

public class ContactUsErrorAndValidationMessage_Action extends TestBase{
	
	@Test
	public void ContactUsErrorAndValidationMessage() throws Exception{
		
		Reporter.log("Click on Home page Contact Us");
		init();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='navbar']/ul/li[3]/a")).click();
		//getWebElement("HomePageContactUsAuth").click();
		
		Reporter.log("Click on Send and Verify the Validation messages in Contact us popup");
		Thread.sleep(2000);
		getWebElement("ContactUsSendButton").click();
		
		Thread.sleep(2000);
		String Message  = getWebElement("ContactUsNameValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:name-error']", Message);
		
		Thread.sleep(2000);
		String Message1  = getWebElement("ContactUsEmailValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:email-error']", Message1);
		
		Thread.sleep(2000);
		String Message2  = getWebElement("ContactUsMessageValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:contactSubmit-error']", Message2);
		
		Reporter.log("Enter Invalid Email address and Verify the Error message in Contact us popup");
		Thread.sleep(2000);
		getWebElement("ContactUsEmailAddress").sendKeys("123");
		
		Thread.sleep(2000);
		String Message3  = getWebElement("ContactUsEmailValidationMessage").getText();
		Thread.sleep(2000);
		verifyText("//*[@id='contactForm:email-error']", Message3);
		
		getWebElement("ContactUsCancelButton").click();
		
		Reporter.log("Click on User Settings->logout and Close the application");
		Thread.sleep(5000);
		driver.close();
		/*LoginPage login = new LoginPage();
		login.Logout();*/
	}
}
