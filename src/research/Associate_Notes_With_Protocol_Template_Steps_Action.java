package research;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageLibrary.Library;
import pageLibrary.LoginPage;
import testBase.TestBase;
import utills.ExcelUtils;
import utills.Research_Constants;

public class Associate_Notes_With_Protocol_Template_Steps_Action extends TestBase {
	
	//@Test(priority = 1)
	public void addNoteToProtocolTemplateStep() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_note = "ProtocolStepNote"+uniqueNumber;
		String step_1_note_desc = "ProtocolStepNoteDesc"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add step to the protocol Template");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note to the protocol Template step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test(priority = 2)
	public void editProtocolTemplateStepNotes() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);

		//ASSOCIATE NOTES TO PROTOCOL TEMPLATE STEPS TEST DATA
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_note = "ProtocolStepNote"+uniqueNumber;
		String step_1_note_desc = "ProtocolStepNoteDesc"+uniqueNumber;
		editStepNoteTitle = "EDITEDSTEPNOTE"+uniqueNumber;
		editStepNoteDescription = "EDITEDSTEPNOTEDESC"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add step to the protocol Template");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add note to the protocol Template step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Edit step note title and description");
		ResearchFunc.editStepNote(step_1_name, step_1_note, editStepNoteTitle, editStepNoteDescription);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	//@Test(priority = 3)
	public void addMultipleNotesToProtocolTemplateStep() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);

		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_note = "ProtocolStepNote1"+uniqueNumber;
		String step_1_note_desc = "ProtocolStepNoteDesc"+uniqueNumber;
		String step_2_note = "ProtocolStepNote2"+uniqueNumber;
		String step_2_note_desc = "ProtocolStepNoteDesc2"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add step to the protocol Template");
		ResearchFunc.addStep(step_1_name, "");
		
		Reporter.log("Add 1st note to the protocol Template step 1");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Add 2nd note to the protocol Template step 1");
		ResearchFunc.addStepNote(step_1_name, step_2_note, step_2_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	//@Test(priority = 4)
	public void moveProtocolTemplateStepNoteFromOneStepToAnotherStep() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);
		
		//STEP NOTE TEST DATA
		String step_1_name = "ProtocolStep1"+uniqueNumber;
		String step_1_desc = "ProtocolStep1Desc"+uniqueNumber;
		String step_2_name = "ProtocolStep2"+uniqueNumber;
		String step_2_desc = "ProtocolStep2Desc"+uniqueNumber;
		String step_3_name = "ProtocolStep3"+uniqueNumber;
		String step_3_desc = "ProtocolStep3Desc"+uniqueNumber;
		String step_1_note = "ProtocolStep1Note"+uniqueNumber;
		String step_1_note_desc = "ProtocolStep1NoteDesc"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add Step "+step_1_name+" to template "+protocolTemplateName);
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add Step "+step_2_name+" to template "+protocolTemplateName);
		ResearchFunc.addStep(step_2_name, step_2_desc);
		
		Reporter.log("Add Step "+step_3_name+" to template "+protocolTemplateName);
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
	public void moveProtocolTemplateLevelNoteToExperimentStep() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);

		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "AssociateNotesWithExpSteps");
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_desc = "ProtocolStepDesc"+uniqueNumber;
		String template_note = "ProtocolLevelNote"+uniqueNumber;
		String template_note_desc = "ProtocolLevelNoteDesc"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add new Step "+step_1_name+" to Template "+protocolTemplateName);
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add new Note "+template_note+" to Template "+protocolTemplateName);
		ResearchFunc.addNote(template_note, template_note_desc);
		
		Reporter.log("Move Template Note "+template_note+" to Template step "+step_1_name);
		ResearchFunc.moveTemplateLevelOrExperimentLevelNotesToStep(step_1_name, template_note, template_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}

	//@Test(priority = 6)
	public void deleteProtocolTemplateStepNote() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_desc = "ProtocolStepDesc"+uniqueNumber;
		String step_1_note = "ProtocolStepNote"+uniqueNumber;
		String step_1_note_desc = "ProtocolStepNoteDesc"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add step to the template");
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Delete step note from the template step");
		ResearchFunc.deleteStepNote(step_1_name, step_1_note);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
	
	@Test(priority = 7)
	public void moveStepNoteAsTemplateNote() throws Exception {
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
		
		//PROTOCOL TEMPLATE TEST DATA
		ExcelUtils.setExcelFile(Research_Constants.Path_TestData+ Research_Constants.File_TestData, "Protocol_Creation");
		String protocolTemplateName = ExcelUtils.getCellData(1, 0);
		
		//ASSOCIATE NOTES TO EXPERIMENT STEPS TEST DATA
		String step_1_name = "ProtocolStep"+uniqueNumber;
		String step_1_desc = "ProtocolStepDesc"+uniqueNumber;
		String step_1_note = "ProtocolStepNote"+uniqueNumber;
		String step_1_note_desc = "ProtocolStepNoteDesc"+uniqueNumber;
		
		Reporter.log("Login to application");
		init();
		lib.loginToApplication(labOwnerUserID, labOwnerPwd);
		
		Reporter.log("Navigate to Project list");
		ResearchFunc.navigateToProjectList();
		
		Reporter.log("Navigate to Protocol Template list");
		ResearchFunc.navigateToProtocolTemplateList();
		
		Reporter.log("Navigate to protocol Template detail page");
		ResearchFunc.navigateToProtocolTemplateDetailPage(protocolTemplateName);
		
		Reporter.log("Add step to the template");
		ResearchFunc.addStep(step_1_name, step_1_desc);
		
		Reporter.log("Add note to the step");
		ResearchFunc.addStepNote(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Move Step note as Experiment level notes or Protocol template notes");
		ResearchFunc.moveStepNotesAsTemplateLevelNotesOrExperimentLevelNotes(step_1_name, step_1_note, step_1_note_desc);
		
		Reporter.log("Click on User Settings and logout");
		Thread.sleep(1000);
		login.Logout();
	}
}