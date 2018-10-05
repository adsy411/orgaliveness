package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Associate_Notes_With_Experiment_Steps_Action extends TestBase {
	
	//@Test(priority = 1)
	public void addNoteToExperimentStep() throws Exception {
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
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		String step_1_note = ExcelUtils.getCellData(1, 3)+uniqueNumber;
		String step_1_note_desc = ExcelUtils.getCellData(1, 4)+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to the experiment");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test(priority = 2)
	public void editStepNotes() throws Exception {
		// DECLARATION
		String editStepNoteTitle, editStepNoteDescription;
		
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
		
		//EXPERIMENT STEP NOTES TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		String step_1_note = ExcelUtils.getCellData(1, 3)+uniqueNumber;
		String step_1_note_desc = ExcelUtils.getCellData(1, 4)+uniqueNumber;
		editStepNoteTitle = "EDITEDSTEPNOTE"+uniqueNumber;
		editStepNoteDescription = "EDITEDSTEPNOTEDESC"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to the experiment");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Edit step note title and description");
		ResearchFunc.editStepNote(step_1_name, step_1_note, editStepNoteTitle, editStepNoteDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test(priority = 3)
	public void addMultipleNotesToStep() throws Exception {
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
		
		//STEP NOTE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = ExcelUtils.getCellData(1, 0)+uniqueNumber;
		String step_1_note = ExcelUtils.getCellData(1, 3)+uniqueNumber;
		String step_1_note_desc = ExcelUtils.getCellData(1, 4)+uniqueNumber;
		String step_2_note = ExcelUtils.getCellData(1, 7)+uniqueNumber;
		String step_2_note_desc = ExcelUtils.getCellData(1, 8)+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to the experiment");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note 1 to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Again add note 2 to the step");
		ResearchFunc.addStepNote(step_1_name, step_2_note, step_2_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
		
	}

	//@Test(priority = 4)
	public void moveStepNoteFromOneStepToAnotherStep() throws Exception {
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
		
		//STEP NOTE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = ExcelUtils.getCellData(1, 0);
		String step_1_desc = ExcelUtils.getCellData(1, 11);
		String step_2_name = ExcelUtils.getCellData(1, 1);
		String step_2_desc = ExcelUtils.getCellData(1, 12);
		String step_3_name = ExcelUtils.getCellData(1, 2);
		String step_3_desc = ExcelUtils.getCellData(1, 13);
		String step_1_note = ExcelUtils.getCellData(1, 3)+uniqueNumber;
		String step_1_note_desc = ExcelUtils.getCellData(1, 4)+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add Step "+step_1_name+" to Experiment"+experimentName);
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add Step "+step_2_name+" to Experiment"+experimentName);
		ResearchFunc.addStep(step_2_name, step_2_desc);
		
		Reporter.log("Add Step "+step_3_name+" to Experiment"+experimentName);
		ResearchFunc.addStep(step_3_name, step_3_desc);
		
		Reporter.log("Add note to the step "+step_1_name);
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Move step note "+step_1_note+" to step "+step_3_name);
		ResearchFunc.moveStepNoteToAnotherStep(step_1_name, step_3_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test(priority = 5)
	public void moveExperimentLevelNoteToExperimentStep() throws Exception {
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
		
		//STEP NOTE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = "New_Step_"+uniqueNumber;
		String step_1_desc = "New_Step_Description_"+uniqueNumber;
		String note_1_name = "Experiment_Note_"+uniqueNumber;
		String note_1_desc = "Experiment_Note_Description_"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add new Step "+step_1_name+" to Experiment "+experimentName);
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add new Note "+note_1_name+" to Experiment "+experimentName);
		ResearchFunc.addNote(note_1_name, note_1_desc);
		
		Reporter.log("Move experiment Note "+note_1_name+" to Experiment step "+step_1_name);
		ResearchFunc.moveTemplateLevelOrExperimentLevelNotesToStep(step_1_name, note_1_name, note_1_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	//@Test(priority = 6)
	public void deleteStepNote() throws Exception {
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
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = "Step"+uniqueNumber;
		String step_1_note = "StepNote"+uniqueNumber;
		String step_1_note_desc = "StepNoteDescription"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to the experiment");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Delete step note from the experiment step");
		ResearchFunc.deleteStepNote(step_1_name, step_1_note);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 7)
	public void moveStepNoteAsExperimentNote() throws Exception {
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
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String stepTitle = "Step"+uniqueNumber;
		String stepNoteTitle = "StepNote"+uniqueNumber;
		String stepNoteDescription = "StepNoteDescription"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to project detail page");
		ResearchFunc.navigateToProjectDetailPage(projectName);
		
		Reporter.log("Navigate to experiment detail page");
		ResearchFunc.navigateToExperimentDetailPage(experimentName);
		
		Reporter.log("Add step to the experiment");
		ResearchFunc.addStep(stepTitle, "");
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(stepTitle, stepNoteTitle, stepNoteDescription);
		
		Reporter.log("Move step note "+stepNoteTitle+" from step "+stepTitle+" as experiment level notes");
		ResearchFunc.moveStepNotesAsTemplateLevelNotesOrExperimentLevelNotes(stepTitle, stepNoteTitle, stepNoteDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}