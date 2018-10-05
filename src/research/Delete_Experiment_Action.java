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

public class Delete_Experiment_Action  extends TestBase {
	
	@Test
	public void deleteExperimentFromExperimentDetailPage() throws Exception {
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
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		String projectVisibility = ExcelUtils.getCellData(1, 2);
		
		//EXPERIMENT TEST DATA 
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = "labExp"+uniqueNumber;
		String experimentDescription = ExcelUtils.getCellData(1, 1)+uniqueNumber;
		String visibility = ExcelUtils.getCellData(1, 2);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope org");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
	
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Delete experiment from experiment detail page");
		try {
			getWebElement("ExperimentSettings").click();
			Thread.sleep(2000);
			getWebElement("DeleteExperiment").click();
			Thread.sleep(2000);
			getWebElement("Link_Delete").click();
			Thread.sleep(4000);
		} catch (Exception e) {
			driver.navigate().refresh();
			Thread.sleep(4000);
			getWebElement("ExperimentSettings").click();
			Thread.sleep(2000);
			getWebElement("DeleteExperiment").click();
			Thread.sleep(2000);
			getWebElement("Link_Delete").click();
			Thread.sleep(4000);
		}

		Reporter.log("Validate experiment from project detail page after deleting");
		if(driver.findElements(By.xpath("//a[@title='" + experimentName + "']")).size() > 0) {
			Assert.assertFalse(true, "Delete experiment failed, Experiment still exists after deleting");
		}
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}