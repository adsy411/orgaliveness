package research;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Edit_Experiment_Action extends TestBase {
	
	@Test(priority = 1)
	public void editExperimentDescription() throws Exception {
		//GENERATE UNIQUE NUMBER
		Library TodayDate = new Library();
		String uniqueNumber = TodayDate.Date();
		
		//RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		Library lib = new Library();
		LoginPage login = new LoginPage();
		SoftAssert softAssert = new SoftAssert();
		
		//LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);
		
		//PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String editExperimentDesc = "Experiment description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Edit experiment description");
		try{
			getWebElement("EditExperiment_DescriptionIcon").click();
		} 
		catch(Exception e) {
			getWebElement("EditExperiment_ExperimentDescription").click();
			Thread.sleep(1000);
			getWebElement("EditExperimentDescription_PencilIcon").click();
		}
		Thread.sleep(5000);
		WebElement EditExperimentDescriptionFrame = getWebElement("EditExperiment_DescriptionFrame");
		driver.switchTo().frame(EditExperimentDescriptionFrame);
		WebElement editExperimentDescBody = driver.findElement(By.tagName("body"));
		editExperimentDescBody.click();
		editExperimentDescBody.clear();
		editExperimentDescBody.sendKeys(editExperimentDesc);
		driver.switchTo().defaultContent();
		getWebElement("SaveDescription").click();
		Thread.sleep(4000);
		
		Reporter.log("Validate edited experiment description");
		String getEditedExperimentDescription = getWebElement("EditExperiment_ExperimentDescription").getText();
		softAssert.assertTrue((getEditedExperimentDescription.equals(editExperimentDesc)), "Edit experiment description failed!");
					
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 2)
	public void addExperimentStep() throws Exception {
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String addStepName = "Step"+uniqueNumber;
		String addStepDescription = "Adding step to a experiment"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Adding steps to the experiment");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		// UPDATING STEP DETAILS TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		ExcelUtils.setCellData(addStepName, 1, 2, Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		ExcelUtils.setCellData(addStepDescription, 1, 3, Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 3)
	public void editExperimentStep() throws Exception {
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EXPERIMENT STEP TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String addStepName = "Step"+uniqueNumber;
		String addStepDescription = "Adding step to a experiment"+uniqueNumber;
		String editStepTitle = "Step_Edited"+uniqueNumber;
		String editStepDescription = "Step_Edited_Description"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to experiment from experiment detail page");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		Reporter.log("Edit step from experiment detail page");
		ResearchFunc.editStep(addStepName, editStepTitle, editStepDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 4)
	public void deleteExperimentStep() throws Exception {
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String addStepName = "Step"+uniqueNumber;
		String addStepDescription = "Adding step to a experiment"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to experiment from experiment detail page");
		ResearchFunc.addStep(addStepName, addStepDescription);
		
		Reporter.log("Delete step from experiment detail page");
		ResearchFunc.deleteStep(addStepName);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 5)
	public void addExperimentNote() throws Exception {
		
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String addExperimentNote = "ExperimentNote"+uniqueNumber;
		String addExperimentNoteDescription = "Adding Experiment notes"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add notes to experiment with title and description");
		ResearchFunc.addNote(addExperimentNote, addExperimentNoteDescription);
		
		// UPDATING STEP DETAILS TO TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		ExcelUtils.setCellData(addExperimentNote, 1, 4, Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		ExcelUtils.setCellData(addExperimentNoteDescription, 1, 5, Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 6)
	public void editExperimentNote() throws Exception {
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String noteTitle = "Note_"+uniqueNumber;
		String noteDescription = "NoteDescription_"+uniqueNumber;
		String editNoteTitle = "EditExperimentNote"+uniqueNumber;
		String editNoteDescription = "Editing Experiment notes"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add note to experiment");
		ResearchFunc.addNote(noteTitle, noteDescription);
		
		Reporter.log("Edit notes added to experiment");
		ResearchFunc.editNote(noteTitle, editNoteTitle, editNoteDescription);

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 7)
	public void deleteExperimentNote() throws Exception {
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
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);
		
		//EDIT EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Edit_Experiment");
		String noteTitle = "Note_"+uniqueNumber;
		String noteDescription = "NoteDescription_"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Edit notes added to experiment");
		ResearchFunc.addNote(noteTitle, noteDescription);
		
		Reporter.log("Delete notes added to experiment");
		ResearchFunc.deleteNote(noteTitle);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	/*
	@Test(priority = 8)
	public void editExperimentStartDate() throws Exception {
		
	}*/
	
	@Test(priority = 9)
	public void addExperimentAttachment() throws Exception {
		// RESEARCH FUNCTIONS OBJECT INITIALIZATION
		ResearchRegularFunctions ResearchFunc = new ResearchRegularFunctions();
		SoftAssert softAssert = new SoftAssert();
		Library lib = new Library();
		LoginPage login = new LoginPage();

		// LOGIN TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Research_Logins");
		String labOwnerUserID = ExcelUtils.getCellData(1, 4);
		String labOwnerPwd = ExcelUtils.getCellData(1, 5);

		// PROJECT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData + Research_Constants.File_TestData, "Create_Project");
		String projectName = ExcelUtils.getCellData(1, 0);
		
		//EXPERIMENT TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Create_Exp");
		String experimentName = ExcelUtils.getCellData(1, 0);

		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);

		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();

		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);

		Reporter.log("Add attachment to experiment");
		getWebElement("Upload_Attachment").click();
		Thread.sleep(2000);
		String currentDir = System.getProperty("user.dir");
		Runtime.getRuntime()
				.exec(currentDir + "//src//testData//Labdrive_Attachments//pdf_upload");
		Thread.sleep(8000);

		Reporter.log("Validation of added attachment");
		List<WebElement> allProjectAttachments = driver
				.findElements(By.xpath("//a[@title='View Attachment']//span"));
		boolean bAttachmentFound = false;
		for (int i = 0; i < allProjectAttachments.size(); i++) {
			String allProjectAttachmentsText = allProjectAttachments.get(i).getText().trim();
			bAttachmentFound = allProjectAttachmentsText.equals("pdf_upload");
		}
		softAssert.assertTrue(bAttachmentFound, "Adding attachment to experiment failed");

		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	/*
	@Test(priority = 10)
	public void downloadExperimentAttachment() throws Exception {
		
	}
	
	@Test(priority = 11)
	public void deleteExperimentAttachment() throws Exception {
		
	}*/
}