package research;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Experiment_MarkAsCompleted_Action extends TestBase {
	
	@Test
	public void markExperimentAsCompleted() throws Exception {
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
		String experimentDescription = ExcelUtils.getCellData(1, 1);
		String visibility = ExcelUtils.getCellData(1, 2);
		
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "ExperimentMarkAsCompleted");
		String addStepName = ExcelUtils.getCellData(1, 0);
		String addExperimentNote = ExcelUtils.getCellData(1, 1);
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Add new experiment with scope Public");
		experimentName = ResearchFunc.createNewExperiment(experimentName, experimentDescription, visibility, projectVisibility);
		
		Reporter.log("Navigate to detail page and validate visiblity scope for public experiment");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Adding steps to the experiment");
		ResearchFunc.addStep(addStepName, "");
		
		Reporter.log("Add notes to experiment");
		ResearchFunc.addNote(addExperimentNote, "");
		
		Reporter.log("Mark experiment step as done");
		driver.findElement(By.xpath("(//button[@class='circle-complete'])[1]")).click();
		Thread.sleep(2000);
		getWebElement("CaptureTodayDate_Check_2").click();
		Thread.sleep(2000);
		getWebElement("CompleteStep_Save").click();
		Thread.sleep(4000);
		
		Reporter.log("Verify experiment step after marking as done");
		boolean stepCompleteStatus = driver.findElement(By.xpath("(//button[@class='circle-complete completed'])[1]")).isDisplayed();
		Assert.assertEquals(stepCompleteStatus,true,"Marking step as done failed!");
		
		Reporter.log("Mark experiment as completed");
		getWebElement("Experiment_MarkAsCompleted_Button").click();
		Thread.sleep(2000);
		getWebElement("CaptureTodayDate_Check").click();
		Thread.sleep(2000);
		getWebElement("CompleteExperimentModal_Save").click();
		Thread.sleep(4000);
		
		Reporter.log("Verify experiment completes status");
		boolean expCompletedStatus = driver.findElement(By.xpath("//button[@class='btn dottedBtn-complete']")).isDisplayed();
		Assert.assertEquals(expCompletedStatus,true,"Marking experiment as completed failed!");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}
