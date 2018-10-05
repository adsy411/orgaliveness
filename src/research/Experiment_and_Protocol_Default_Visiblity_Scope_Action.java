package research;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Experiment_and_Protocol_Default_Visiblity_Scope_Action  extends TestBase  {

	@Test
	public void VerifyDefaultVisiblityScopeForExperiment() throws Exception {
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment");
		getWebElement("Button_Create").click();
		Thread.sleep(2000);
		getWebElement("Add_Experiment").click();
		Thread.sleep(5000);
		
		Reporter.log("Verify default visiblity scope for experiment");
		String getVisiblityStatus = getWebElement("Visiblity_Lab_2").getAttribute("checked");
		Assert.assertEquals(getVisiblityStatus,"true", "Visbility of experiment not set to Lab by default");
		getWebElement("Modal_CancelButton").click();
		Thread.sleep(1000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test
	public void VerifyDefaultVisiblityScopeForProtocol() throws Exception {
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create New Protocol template");
		getWebElement("Protocol.CreateNewButton").click();
		Thread.sleep(4000);
		
		Reporter.log("Verify default visiblity scope for protocol template");
		String getVisiblityStatus = getWebElement("ProtocolVisbilityLabOnly").getAttribute("checked");
		Assert.assertEquals(getVisiblityStatus,"true", "Visbility of protocol template not set to Lab by default");
		getWebElement("Modal_CancelButton").click();
		Thread.sleep(1000);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	@Test
	public void ValidationOfVisiblityScopeForProtocolTemplateLoadedAsExperiment() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "OrgProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(1, 1);
		String visibility = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template with scope Organisation");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Verify protocol in protocol list");
		ResearchFunc.verifyProtocolInProtocolList(protocolName);
		
		// REFRESH
		ResearchFunc.refreshPage();
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Open Load protocol template");
		getWebElement("Button_Create").click();
		Thread.sleep(1000);
		getWebElement("LoadProtocolTemplate").click();
		Thread.sleep(5000);
		
		Reporter.log("Verify protocol template in modal");
		getWebElement("LoadProtocolTemplate_Search").click();
		getWebElement("LoadProtocolTemplate_Search").sendKeys(protocolName);
		Thread.sleep(8000);
		java.util.List<WebElement> noOfRows = getWebElement("LoadProtocolTemplateModalTable").findElements(By.tagName("tr"));
		Assert.assertEquals(noOfRows.size(),2,"Protocol template not found in load template modal");
		
		Reporter.log("Load protocol template from load protocol template modal");
		try {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("(//table[@id='loadTemplateForm:templateTable']//td)[1]"))).build().perform();
			driver.findElement(By.xpath("(//table[@id='loadTemplateForm:templateTable']//td)[1]")).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			getWebElement("LoadProtocolTemplate_Search").clear();
			Thread.sleep(1000);
			getWebElement("LoadProtocolTemplate_Search").sendKeys(protocolName);
			Thread.sleep(4000);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("(//table[@id='loadTemplateForm:templateTable']//td)[1]"))).build().perform();
			driver.findElement(By.xpath("(//table[@id='loadTemplateForm:templateTable']//td)[1]")).click();
		}
		getWebElement("LoadProtocolTemplate_Save").click();
		impliciteWait(4);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(protocolName);
		
		Reporter.log("Validate visiblity scope for protocol template loaded into project");
		boolean bVisiblityScope = getWebElement("ExpVisiblityScope_Lab").isDisplayed();
		Assert.assertEquals(bVisiblityScope,true,"Experiment visiblity not set as Lab after loading protocol template in project");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}