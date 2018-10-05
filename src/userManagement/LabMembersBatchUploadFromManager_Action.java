package userManagement;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;

public class LabMembersBatchUploadFromManager_Action extends TestBase {
	
	@Test
	public void LabMembersBatchUploadFromManager() throws Exception{
		
		ExcelUtils.setExcelFile(Constants.Path_TestData
				+ Constants.File_TestData, "UserManagement");

		String FirstName1 = ExcelUtils.getCellData(1, 43);
		String LastName1 = ExcelUtils.getCellData(1, 44);
		String FirstName2 = ExcelUtils.getCellData(2, 43);
		String LastName2 = ExcelUtils.getCellData(2, 44);
		String FirstName3 = ExcelUtils.getCellData(3, 43);
		String LastName3 = ExcelUtils.getCellData(3, 44);
		String NewEmail1 = ExcelUtils.getCellData(1, 42);
		String NewEmail2 = ExcelUtils.getCellData(2, 42);
		String NewEmail3 = ExcelUtils.getCellData(3, 42);
		
		Reporter.log("Login to Application with specific user2");
		init();
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("dt03aprilman@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();

		Reporter.log("Click on User Settings and go to Lab Members");
		impliciteWait(5);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Reporter.log("Click Add New Member");
		impliciteWait(5);
		getWebElement("AddNewMembersButton").click();
		
		Reporter.log("Click Batchupload and upload the Lab Members");
		Thread.sleep(2000);
		getWebElement("BatchUpload").click();
		Thread.sleep(3000);
		getWebElement("FileUpload").click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\dprakash\\Desktop\\Files\\ManagerBatchUpload\\ManagerBatchUpload.exe");
		Thread.sleep(10000);
		((JavascriptExecutor)driver).executeScript("window.open('about:blank', '_blank');");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://getnada.com/");
		Thread.sleep(3000);
		getWebElement("AddInbox").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail1);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles = driver.getWindowHandles();
	    String currentHandle = driver.getWindowHandle();
	    for (String handle : handles) {

	     if (!handle .equals(currentHandle))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName1);
		getWebElement("RegLastName").sendKeys(LastName1);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("RegConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		Library lib = new Library();
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		getWebElement("AddInbox1").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail2);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles1 = driver.getWindowHandles();
	    String currentHandle1 = driver.getWindowHandle();
	    for (String handle : handles1) {

	     if (!handle .equals(currentHandle1))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName2);
		getWebElement("RegLastName").sendKeys(LastName2);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("RegConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		getWebElement("AddInbox2").click();
		Thread.sleep(2000);
		getWebElement("maildomaindropdown").click();
		getWebElement("maildomaindropdown").sendKeys(Keys.chord("am"));
		getWebElement("maildomaindropdown").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		getWebElement("AddEmailID").clear();
		getWebElement("AddEmailID").sendKeys(NewEmail3);
		Thread.sleep(2000);
		getWebElement("AcceptButton").click();
		Thread.sleep(3000);
		getWebElement("NadaMailList").click();
		Thread.sleep(3000);
		driver.switchTo().frame("idIframe");
		Thread.sleep(2000);
		getWebElement("NadaRegisterToLabButton").click();
		
		Reporter.log("Enter the required fields in the Register page and Signup");
		Thread.sleep(7000);
		
		Set<String> handles2 = driver.getWindowHandles();
	    String currentHandle2 = driver.getWindowHandle();
	    for (String handle : handles2) {

	     if (!handle .equals(currentHandle2))
	     {
	         driver.switchTo().window(handle);
	     }
	   }
		
	    Thread.sleep(2000);
		getWebElement("RegFirstName").sendKeys(FirstName3);
		getWebElement("RegLastName").sendKeys(LastName3);
		Thread.sleep(2000);
		getWebElement("RegPassword").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("RegConfirmPassword").sendKeys("admin123");
		Thread.sleep(2000);
		lib.Select("RegCountry", 79);
		Thread.sleep(2000);
		getWebElement("RegTermsOfService").click();
		Thread.sleep(2000);
		getWebElement("RegAgreeButton").click();
		Thread.sleep(2000);
		getWebElement("RegSignUpButton").click();
		impliciteWait(5);
		getWebElement("RegUserCloseButton").click();
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs5 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs5.get(1));
		
		Thread.sleep(2000);
		driver.close();
		
		ArrayList<String> tabs6 = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs6.get(0));
		
		/*Thread.sleep(5000);
		getWebElement("LogoutRightButton").click();
		Thread.sleep(2000);
		getWebElement("Enotebook.logout").click();
		
		getWebElement("Enotebook.clicklogin.username").click();
		getWebElement("Enotebook.login.username").sendKeys("09jan@mailinator.com");
		getWebElement("Enotebook.login.password").sendKeys("admin123");
		Thread.sleep(2000);
		getWebElement("Enotebook.login.loginButton").click();*/
		
		Reporter.log("Click on User Settings and go to Lab Members");
		Thread.sleep(5000);
		getWebElement("UserSettings").click();
		getWebElement("LabMembers").click();
		
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:2:openpay']", FirstName1+" "+LastName1);
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:1:openpay']", FirstName2+" "+LastName2);
		Thread.sleep(4000);
		verifyText("//*[@id='memberForm:memberTable:0:openpay']", FirstName3+" "+LastName3);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(5000);
		LoginPage login = new LoginPage();
		login.Logout();

	}

}
