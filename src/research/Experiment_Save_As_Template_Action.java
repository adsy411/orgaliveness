package research;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Experiment_Save_As_Template_Action extends TestBase {
	
	@Test
	public void experimentSaveAsTemplate() throws Exception {
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
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "LabExp"+uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1)+uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope Public");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
		
		Reporter.log("Validate visiblity scope for public experiment");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Save experiment as template");
		getWebElement("ExperimentSettings").click();
		Thread.sleep(2000);
		getWebElement("ExperimentSettings_AddToTemplate").click();
		
		Reporter.log("Verify add to template success message");
		String getAddToTemplatesSuccMsg = getWebElement("SaveAsTemplate_SuccMsg").getText().trim();
		Assert.assertEquals(getAddToTemplatesSuccMsg,"Success! Experiment has been saved as Template successfully!","Experiment not added as template successfully");
		Thread.sleep(4000);
		
		Reporter.log("Navigate to Protocol Templates list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Verify saved template in protocol template list");
		ResearchFunc.verifyProtocolInProtocolList(experimentName);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

}