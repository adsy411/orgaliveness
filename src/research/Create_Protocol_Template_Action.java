package research;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Create_Protocol_Template_Action extends TestBase {
	
	@Test
	public void createNewOrganisationProtocolTemplate() throws Exception {
		
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "OrgProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(1, 1)+uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to protocol template list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template with scope Organisation");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Validation of protocol visiblity scope");
		boolean visiblityPublic = getWebElement("Scope_Public").isDisplayed();
		Assert.assertEquals(visiblityPublic,true,"Protocol visiblity not set as Organisation after creation");
		
		Reporter.log("Click on User Settings and logout");
		ResearchFunc.Logout();
	}
	
	@Test
	public void createNewPrivateProtocolTemplate() throws Exception {
		
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "privateProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(2, 1)+uniqueNumber;
		String visibility = ExcelUtils.getCellData(2, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template with scope as Private");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Validation of protocol visiblity scope");
		boolean visiblityPrivate = getWebElement("Scope_Private").isDisplayed();
		Assert.assertEquals(visiblityPrivate,true,"Protocol visiblity not set as private after creation");
		
		Reporter.log("Click on User Settings and logout");
		ResearchFunc.Logout();
	}
	
	@Test
	public void createNewLabOnlyProtocolTemplate() throws Exception {
		
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//CREATE PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolName = "labOnlyProtocol"+uniqueNumber;
		String protocolDescription = ExcelUtils.getCellData(3, 1);
		String visibility = ExcelUtils.getCellData(3, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list page");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Create new protocol template");
		protocolName = ResearchFunc.createProtocolTemplate(protocolName, protocolDescription, visibility);
		
		Reporter.log("Navigate to protocol detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolName);
		
		Reporter.log("Validation of protocol visiblity scope");
		boolean visiblityPrivate = getWebElement("Scope_LabOnly").isDisplayed();
		Assert.assertEquals(visiblityPrivate,true,"Protocol visiblity not set as lab only after creation");

		Reporter.log("Click on User Settings and logout");
		ResearchFunc.Logout();
	}
}