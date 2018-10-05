package testScript;

import org.testng.annotations.Test;
import research.Add_Material_To_Experiment_Action;
import research.AssignCodeToExperiment_Action;
import research.AssignCodeToProject_Action;
import research.Associate_Notes_With_Experiment_Steps_Action;
import research.Clone_Experiment_Action;
import research.Create_Experiment_Action;
import research.Create_Project_Action;
import research.Create_Protocol_Template_Action;
import research.Delete_Experiment_Action;
import research.Delete_Material_From_Experiment_Action;
import research.Delete_Product_AddedTo_ProtocolTemplateSteps_Action;
import research.Delete_Product_From_Template_Action;
import research.Delete_Project_Action;
import research.Delete_Protocol_Template_Action;
import research.Delete_Step_Material_Action;
import research.Edit_Experiment_Action;
import research.Edit_Project_Action;
import research.Edit_Protocol_Template_Action;
import research.Experiment_MarkAsCompleted_Action;
import research.Experiment_Save_As_Template_Action;
import research.Experiment_SubmitForReview_Action;
import research.Experiment_and_Protocol_Default_Visiblity_Scope_Action;
import research.Export_Protocol_In_Doc_Format_Action;
import research.LoadTemplateInExperiment_Action;
import research.ProjectListPageFunctionalities_Action;
import research.ProtocolTemplateListPageFunctionalities_Action;
import research.PublicProtocolVerification_Action;
import research.Request_Material_From_Experiment_Action;
import research.Request_Product_Added_To_Template_Action;
import research.Step_Note_Activity_Log_Action;
import research.Update_Material_In_Experiment_Action;
import research.Update_Product_In_Template_Action;
import research.Update_Step_Material_In_Experiment_Action;
import research.Update_Step_Product_In_Template_Action;
import research.Add_Material_To_Experiment_Steps_Action;
import research.Add_Product_To_ProtocolTemplate_Action;
import research.Add_Product_To_Template_Steps_Action;

public class Research_Suite {

	@Test(priority = 1)
	public void CreateProjectWithScopeAsLab() throws Exception {
		Create_Project_Action NewProj = new Create_Project_Action();
		NewProj.createLabProject();
	}

	@Test(priority = 2)
	public void CreateProjectWithScopeAsRestricted() throws Exception {
		Create_Project_Action NewProj = new Create_Project_Action();
		NewProj.createLabProject();
	}

	@Test(priority = 3)
	public void CreateLabExperimentInLabProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createLabExperimentInLabProject();
	}

	@Test(priority = 4)
	public void CreateRestrictedExperimentInLabProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createRestrictedExperimentInLabProject();
	}

	@Test(priority = 5)
	public void CreateProjectLevelExperimentInRestrictedProject() throws Exception {
		Create_Experiment_Action NewExp2 = new Create_Experiment_Action();
		NewExp2.createProjectExperimentInRestrictedProject();
	}

	@Test(priority = 6)
	public void CreateRestrictedExperimentInRestrictedProject() throws Exception {
		Create_Experiment_Action NewExp1 = new Create_Experiment_Action();
		NewExp1.createRestrictedExperimentInRestrictedProject();
	}

	@Test(priority = 7)
	public void CreateOrganisationScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewOrganisationProtocolTemplate();
	}

	@Test(priority = 8)
	public void CreatePrivateScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewPrivateProtocolTemplate();
	}

	@Test(priority = 9)
	public void CreateLabOnlyScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewLabOnlyProtocolTemplate();
	}

	@Test(priority = 10)
	public void EditProjectDescription() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.editProjectDescription();
	}

	@Test(priority = 11)
	public void AddProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.addProjectNotes();
	}

	@Test(priority = 12)
	public void EditProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.editProjectNotes();
	}

	@Test(priority = 13)
	public void DeleteProjectNotes() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.deleteProjectNotes();
	}

	@Test(priority = 14)
	public void AddProjectAttachment() throws Exception {
		Edit_Project_Action EditProj = new Edit_Project_Action();
		EditProj.addProjectAttachment();
	}

	@Test(priority = 15)
	public void EditExperimentDescription() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentDescription();
	}

	@Test(priority = 16)
	public void AddExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentStep();
	}

	@Test(priority = 17)
	public void EditExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentStep();
	}

	@Test(priority = 18)
	public void DeleteExperimentStep() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.deleteExperimentStep();
	}

	@Test(priority = 19)
	public void AddExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentNote();
	}

	@Test(priority = 20)
	public void EditExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.editExperimentNote();
	}

	@Test(priority = 21)
	public void DeleteExperimentNote() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.deleteExperimentNote();
	}

	@Test(priority = 22)
	public void AddExperimentAttachment() throws Exception {
		Edit_Experiment_Action EditExp = new Edit_Experiment_Action();
		EditExp.addExperimentAttachment();
	}

	@Test(priority = 23)
	public void AddProtocolDescription() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateDescription();
	}

	@Test(priority = 24)
	public void AddProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateStep();
	}

	@Test(priority = 25)
	public void EditProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateStep();
	}

	@Test(priority = 26)
	public void DeleteProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.deleteProtocolTemplateStep();
	}

	@Test(priority = 27)
	public void AddProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateNote();
	}

	@Test(priority = 28)
	public void EditProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateNote();
	}

	@Test(priority = 29)
	public void DeleteProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.deleteProtocolTemplateNote();
	}

	@Test(priority = 30)
	public void AddProtocolTemplateAttachment() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateAttachment();
	}

	@Test(priority = 31)
	public void OrganisationProtocolScopeValidation() throws Exception {
		PublicProtocolVerification_Action PublicProtocolVal = new PublicProtocolVerification_Action();
		PublicProtocolVal.verifyPublicProtocolInOtherLabUnderSameOrganisation();
	}

	@Test(priority = 32)
	public void AssignCodeToProject() throws Exception {
		AssignCodeToProject_Action AssignCodeToProj = new AssignCodeToProject_Action();
		AssignCodeToProj.assignCodeToProject();
	}

	@Test(priority = 33)
	public void AssignCodeToExperiment() throws Exception {
		AssignCodeToExperiment_Action AssignCodeToExp = new AssignCodeToExperiment_Action();
		AssignCodeToExp.assignCodeToExperiment();
	}

	@Test(priority = 34)
	public void CloneExperiment() throws Exception {
		Clone_Experiment_Action CloneExp = new Clone_Experiment_Action();
		CloneExp.cloneExperiment();
	}

	@Test(priority = 35)
	public void LoadTemplateInExperiment() throws Exception {
		LoadTemplateInExperiment_Action LoadTemp = new LoadTemplateInExperiment_Action();
		LoadTemp.loadTemplateInExperiment();
	}

	@Test(priority = 36)
	public void AddStepNoteInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action CreateStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		CreateStepNote.addNoteToExperimentStep();
	}

	@Test(priority = 37)
	public void AddMultipleNotesToStepInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action AddMulStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		AddMulStepNote.addMultipleNotesToStep();
	}

	@Test(priority = 38)
	public void EditStepNoteInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action EditStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		EditStepNote.editStepNotes();
	}

	@Test(priority = 39)
	public void MoveStepNoteToAnotherStepInExperiment() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action MoveStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		MoveStepNote.moveStepNoteFromOneStepToAnotherStep();
	}

	@Test(priority = 40)
	public void MoveExperimentLevelNoteToStep() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action MoveStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		MoveStepNote.moveExperimentLevelNoteToExperimentStep();
	}

	@Test(priority = 41)
	public void DeleteStepNote() throws Exception {
		Associate_Notes_With_Experiment_Steps_Action DeleteStepNote = new Associate_Notes_With_Experiment_Steps_Action();
		DeleteStepNote.deleteStepNote();
	}

	@Test(priority = 42)
	public void ExperimentSaveAsTemplate() throws Exception {
		Experiment_Save_As_Template_Action ESaveAsTemp = new Experiment_Save_As_Template_Action();
		ESaveAsTemp.experimentSaveAsTemplate();
	}

	@Test(priority = 43)
	public void ExperimentSubmitForReview_Approve() throws Exception {
		Experiment_SubmitForReview_Action ExpSubForRev_A = new Experiment_SubmitForReview_Action();
		ExpSubForRev_A.experimentSubmitForReview_Approve();
	}

	@Test(priority = 44)
	public void ExperimentSubmitForReview_Reject() throws Exception {
		Experiment_SubmitForReview_Action ExpSubForRev_B = new Experiment_SubmitForReview_Action();
		ExpSubForRev_B.experimentSubmitForReview_Reject();
	}

	@Test(priority = 45)
	public void ExperimentStepNoteActivityLog() throws Exception {
		Step_Note_Activity_Log_Action ExpStepNoteActLog = new Step_Note_Activity_Log_Action();
		ExpStepNoteActLog.experimentStepNoteActivityLogValidation();
	}

	@Test(priority = 46)
	public void ExportProtocolInDocFormat() throws Exception {
		Export_Protocol_In_Doc_Format_Action ExProInDoc = new Export_Protocol_In_Doc_Format_Action();
		ExProInDoc.exportProtocolInDocFormat();
	}

	@Test(priority = 47)
	public void ValidationOfDefaultVisibilityForExperiment() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.VerifyDefaultVisiblityScopeForExperiment();
	}

	@Test(priority = 48)
	public void ValidationOfDefaultVisibilityForProtocol() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.VerifyDefaultVisiblityScopeForProtocol();
	}

	@Test(priority = 49)
	public void ValidationOfDefaultVisibilityForProtocolLoadedAsExperiment() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.ValidationOfVisiblityScopeForProtocolTemplateLoadedAsExperiment();
	}

	@Test(priority = 50)
	public void ProjectList_SwitchView() throws Exception {
		ProjectListPageFunctionalities_Action projListFunc = new ProjectListPageFunctionalities_Action();
		projListFunc.switchProjectListView();
	}

	@Test(priority = 51)
	public void FilterProjectList_ByCreator_ByDateRange_ByStatus() throws Exception {
		ProjectListPageFunctionalities_Action projListFunc = new ProjectListPageFunctionalities_Action();
		projListFunc.projectListFilters();
	}

	@Test(priority = 52)
	public void ProtocolTemplateList_SwitchView() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action protocolListFunc = new ProtocolTemplateListPageFunctionalities_Action();
		protocolListFunc.switchProtocolTemplateListView();
	}

	@Test(priority = 53)
	public void FilterProtocolList_ByCreator_ByDateRange_ByLab() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action protocolListFunc = new ProtocolTemplateListPageFunctionalities_Action();
		protocolListFunc.protocolTemplateListFilters();
	}

	@Test(priority = 54)
	public void AddSigmaAldrichMaterialToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperiment();
	}

	@Test(priority = 55)
	public void AddThirdPartyMaterialToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperiment();
	}

	@Test(priority = 56)
	public void AddSigmaAldrichMaterialToExperimentWhichIsPresentInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperimentWhichIsPresentInInventory();
	}

	@Test(priority = 57)
	public void AddThirdPartyMaterialToExperimentWhichIsPresentInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperimentWhichIsPresentInInventory();
	}

	@Test(priority = 58)
	public void AddSigmaAldrichMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperimentWhichIsPresentInCatalogButNotInInventory();
	}

	@Test(priority = 59)
	public void AddThirdPartyMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperimentWhichIsPresentInCatalogButNotInInventory();
	}

	@Test(priority = 60)
	public void AddMaterialToExperimentFromSigmaAldrichDatabase() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addMaterialToExperimentFromSigmaAldrichDataBase();
	}

	@Test(priority = 61)
	public void UpdateMaterialInExperiment() throws Exception {
		Update_Material_In_Experiment_Action EditMatToExpSteps = new Update_Material_In_Experiment_Action();
		EditMatToExpSteps.updateMaterialInExperiment();
	}

	@Test(priority = 62)
	public void RequestMaterialInExperiment() throws Exception {
		Request_Material_From_Experiment_Action ReqMatFromExp = new Request_Material_From_Experiment_Action();
		ReqMatFromExp.requestMaterialFromExperiment();
	}

	@Test(priority = 63)
	public void AddMaterialToExperimentSteps() throws Exception {
		Add_Material_To_Experiment_Steps_Action AddMatToExpSteps = new Add_Material_To_Experiment_Steps_Action();
		AddMatToExpSteps.addMaterialToExperimentSteps_WhichIsPresentInExperiment();
	}

	@Test(priority = 64)
	public void UpdateStepMaterialInExperiment() throws Exception {
		Update_Step_Material_In_Experiment_Action UpdateMatInExp = new Update_Step_Material_In_Experiment_Action();
		UpdateMatInExp.updateMaterialInExperimentStep();
	}

	@Test(priority = 65)
	public void AddSigmaAldrichProductToProtocolTemplate() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addSigmaAldrichProductToTemplate();
	}

	@Test(priority = 66)
	public void AddThirdPartyProductToProtocolTemplate() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addThirdPartyProductToTemplate();
	}

	@Test(priority = 67)
	public void AddProductToProtocolTemplateFromSigmaAldrichDatabase() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addProductToTemplateFromSigmaDatabase();
	}

	@Test(priority = 68)
	public void AddProductToProtocolTemplateFromLabCatalog() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addProductToTemplateFromCatalog();
	}

	@Test(priority = 69)
	public void UpdateProductInTemplate() throws Exception {
		Update_Product_In_Template_Action UpdateProductToTemplateSteps = new Update_Product_In_Template_Action();
		UpdateProductToTemplateSteps.updateProductInTemplate();
	}

	@Test(priority = 70)
	public void RequestProductInTemplate() throws Exception {
		Request_Product_Added_To_Template_Action RequestProductFromTemplateSteps = new Request_Product_Added_To_Template_Action();
		RequestProductFromTemplateSteps.requestProductAddedToTemplate();
	}

	@Test(priority = 71)
	public void AddProductToProtocolTemplateSteps() throws Exception {
		Add_Product_To_Template_Steps_Action AddProductToTemplateSteps = new Add_Product_To_Template_Steps_Action();
		AddProductToTemplateSteps.addProductToTemplateSteps_WhichIsPresentInTemplate();
	}

	@Test(priority = 72)
	public void AddProductToProtocolTemplateStepsWhichIsPartiallyUsedInAnotherStep() throws Exception {
		Add_Product_To_Template_Steps_Action AddProductToTemplateSteps2 = new Add_Product_To_Template_Steps_Action();
		AddProductToTemplateSteps2.addProductToTemplateSteps_WhichIsPartiallyUsedInAnotherStep();
	}

	@Test(priority = 73)
	public void UpdateProductInProtocolTemplateSteps() throws Exception {
		Update_Step_Product_In_Template_Action UpdateProductToTemplateSteps = new Update_Step_Product_In_Template_Action();
		UpdateProductToTemplateSteps.updateProductInTemplateStep();
	}

	@Test(priority = 74)
	public void DeleteProductFromProtocolTemplateSteps() throws Exception {
		Delete_Product_AddedTo_ProtocolTemplateSteps_Action DeleteProdFromTempSteps = new Delete_Product_AddedTo_ProtocolTemplateSteps_Action();
		DeleteProdFromTempSteps.deleteProductAddedToTemplateSteps();
	}

	@Test(priority = 75)
	public void DeleteProductFromProtocolTemplate() throws Exception {
		Delete_Product_From_Template_Action DeleteProdFromTemp = new Delete_Product_From_Template_Action();
		DeleteProdFromTemp.deleteProductFromTemplate();
	}

	@Test(priority = 76)
	public void DeleteExperimentStepMaterial() throws Exception {
		Delete_Step_Material_Action DeleteStepMatInExp = new Delete_Step_Material_Action();
		DeleteStepMatInExp.deleteStepMaterialFromExperiment();
	}

	@Test(priority = 77)
	public void DeleteMaterialFromExperiment() throws Exception {
		Delete_Material_From_Experiment_Action DeleteMatFromExp = new Delete_Material_From_Experiment_Action();
		DeleteMatFromExp.deleteMaterialFromExperiment();
	}

	@Test(priority = 78)
	public void ConsumeMaterialAddedToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.consumeMaterialInInventoryWhichIsAddedToExperiment();
	}

	@Test(priority = 79)
	public void ExperimentMarkAsCompleted() throws Exception {
		Experiment_MarkAsCompleted_Action ExpMarkComp = new Experiment_MarkAsCompleted_Action();
		ExpMarkComp.markExperimentAsCompleted();
	}

	@Test(priority = 80)
	public void DeleteProjectWithExperiment() throws Exception {
		Delete_Project_Action deleteProject = new Delete_Project_Action();
		deleteProject.deleteProjectFromProjectDetailPage_WithExperiment();
	}

	@Test(priority = 81)
	public void DeleteExperiment() throws Exception {
		Delete_Experiment_Action deleteExp = new Delete_Experiment_Action();
		deleteExp.deleteExperimentFromExperimentDetailPage();
	}

	@Test(priority = 82)
	public void DeleteProjectWithOutExperiment() throws Exception {
		Delete_Project_Action deleteProject = new Delete_Project_Action();
		deleteProject.deleteProjectFromProjectDetailPage_WithOutExperiment();
	}

	@Test(priority = 83)
	public void DeleteProtocolTemplate() throws Exception {
		Delete_Protocol_Template_Action deleteTemplate = new Delete_Protocol_Template_Action();
		deleteTemplate.deleteProtocolTemplate();
	}

	@Test(priority = 84)
	public void DownloadProjectList() throws Exception {
		ProjectListPageFunctionalities_Action download = new ProjectListPageFunctionalities_Action();
		download.downloadProjectList();
	}

	@Test(priority = 85)
	public void DownloadProtocolTemplateList() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action download = new ProtocolTemplateListPageFunctionalities_Action();
		download.downloadProtocolTemplateList();
	}
}