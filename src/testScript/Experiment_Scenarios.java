package testScript;

import org.testng.annotations.Test;
import research.AssignCodeToExperiment_Action;
import research.Associate_Notes_With_Experiment_Steps_Action;
import research.Clone_Experiment_Action;
import research.Create_Experiment_Action;
import research.Edit_Experiment_Action;
import research.Experiment_MarkAsCompleted_Action;
import research.Experiment_Save_As_Template_Action;
import research.Experiment_SubmitForReview_Action;
import research.Experiment_and_Protocol_Default_Visiblity_Scope_Action;
import research.LoadTemplateInExperiment_Action;
import research.Step_Note_Activity_Log_Action;

public class Experiment_Scenarios {

	@Test(priority = 1)
	public void CreateLabExperimentInLabProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createLabExperimentInLabProject();
	}

	@Test(priority = 2)
	public void CreateRestrictedExperimentInLabProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createRestrictedExperimentInLabProject();
	}

	@Test(priority = 3)
	public void CreateProjectLevelExperimentInRestrictedProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createProjectExperimentInRestrictedProject();
	}

	@Test(priority = 4)
	public void CreateRestrictedExperimentInRestrictedProject() throws Exception {
		Create_Experiment_Action NewExp1 = new Create_Experiment_Action();
		NewExp1.createRestrictedExperimentInRestrictedProject();
	}

	@Test(priority = 5)
	public void EditExperimentDescription() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentDescription();
	}

	@Test(priority = 6)
	public void AddExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentStep();
	}

	@Test(priority = 7)
	public void EditExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentStep();
	}

	@Test(priority = 8)
	public void DeleteExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.deleteExperimentStep();
	}

	@Test(priority = 9)
	public void AddExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentNote();
	}

	@Test(priority = 10)
	public void EditExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentNote();
	}

	@Test(priority = 11)
	public void DeleteExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.deleteExperimentNote();
	}

	@Test(priority = 12)
	public void AddExperimentAttachment() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentAttachment();
	}

	@Test(priority = 13)
	public void AssignCodeToExperiment() throws Exception {
		AssignCodeToExperiment_Action AssignCodeToExp = new AssignCodeToExperiment_Action();
		AssignCodeToExp.assignCodeToExperiment();
	}

	@Test(priority = 14)
	public void CloneExperiment() throws Exception {
		Clone_Experiment_Action CloneExp = new Clone_Experiment_Action();
		CloneExp.cloneExperiment();
	}

	@Test(priority = 15)
	public void LoadTemplateInExperiment() throws Exception {
		LoadTemplateInExperiment_Action LoadTemp = new LoadTemplateInExperiment_Action();
		LoadTemp.loadTemplateInExperiment();
	}

	@Test(priority = 16)
	public void AddStepNoteInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action CreateStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		CreateStepNote.addNoteToExperimentStep();
	}

	@Test(priority = 17)
	public void AddMultipleNotesToStepInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action AddMulStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		AddMulStepNote.addMultipleNotesToStep();
	}

	@Test(priority = 18)
	public void EditStepNoteInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action EditStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		EditStepNote.editStepNotes();
	}

	@Test(priority = 19)
	public void MoveStepNoteToAnotherStepInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action MoveStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		MoveStepNote.moveStepNoteFromOneStepToAnotherStep();
	}

	@Test(priority = 20)
	public void MoveExperimentLevelNoteToStep() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action MoveStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		MoveStepNote.moveExperimentLevelNoteToExperimentStep();
	}

	@Test(priority = 21)
	public void DeleteStepNote() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action DeleteStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		DeleteStepNote.deleteStepNote();
	}

	@Test(priority = 22)
	public void ExperimentSaveAsTemplate() throws Exception {
		Experiment_Save_As_Template_Action ESaveAsTemp = new Experiment_Save_As_Template_Action();
		ESaveAsTemp.experimentSaveAsTemplate();
	}

	@Test(priority = 23)
	public void ExperimentSubmitForReview_Approve() throws Exception {
		Experiment_SubmitForReview_Action ExpSubForRev_A = new Experiment_SubmitForReview_Action();
		ExpSubForRev_A.experimentSubmitForReview_Approve();
	}

	@Test(priority = 24)
	public void ExperimentSubmitForReview_Reject() throws Exception {
		Experiment_SubmitForReview_Action ExpSubForRev_B = new Experiment_SubmitForReview_Action();
		ExpSubForRev_B.experimentSubmitForReview_Reject();
	}

	@Test(priority = 25)
	public void ExperimentStepNoteActivityLog() throws Exception {
		Step_Note_Activity_Log_Action ExpStepNoteActLog = new Step_Note_Activity_Log_Action();
		ExpStepNoteActLog.experimentStepNoteActivityLogValidation();
	}

	@Test(priority = 26)
	public void ValidationOfDefaultVisibilityForExperiment() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.VerifyDefaultVisiblityScopeForExperiment();
	}

	@Test(priority = 27)
	public void ExperimentMarkAsCompleted() throws Exception {
		Experiment_MarkAsCompleted_Action ExpMarkComp = new Experiment_MarkAsCompleted_Action();
		ExpMarkComp.markExperimentAsCompleted();
	}
}