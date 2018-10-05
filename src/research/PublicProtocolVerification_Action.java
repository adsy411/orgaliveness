package research;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.Constants;
import utills.ExcelUtils;
import utills.Research_Constants;

public class PublicProtocolVerification_Action extends TestBase {
	
	@Test
	public void verifyPublicProtocolInOtherLabUnderSameOrganisation() throws Exception{
		
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String Org1_Lab1_User_Name = ExcelUtils.getCellData(1, 0);
		String Org1_Lab1_Pwd = ExcelUtils.getCellData(1, 1);
		String Org1_Lab2_User_Name = ExcelUtils.getCellData(1, 2);
		String Org1_Lab2_Pwd = ExcelUtils.getCellData(1, 3);
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "OrgProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(1,1);
		String visibility = ExcelUtils.getCellData(1,2);
		
		Reporter.log("Login to application under one organisation");
		init();
		lib.loginToApplication(Org1_Lab1_User_Name, Org1_Lab1_Pwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template with scope Organisation");
		ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		LoginPage login = new LoginPage();
		login.Logout();
		
		Reporter.log("Login to application under same organisation for another lab");
		init();
		lib.loginToApplication(Org1_Lab2_User_Name, Org1_Lab2_Pwd);
		
		Reporter.log("Navigate to protocol template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Verify the created protocol in lab 2 of same organisation");
		boolean BprotocolExists = driver.findElement(By.linkText(protocolName)).isDisplayed();
		Assert.assertEquals(BprotocolExists,true,"protocol template not visible in different lab under same organisation");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}