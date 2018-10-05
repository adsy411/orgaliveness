package testScript;

import org.testng.annotations.Test;
import research.Create_Protocol_Template_Action;
import research.Edit_Protocol_Template_Action;
import research.Experiment_and_Protocol_Default_Visiblity_Scope_Action;
import research.Export_Protocol_In_Doc_Format_Action;
import research.ProtocolTemplateListPageFunctionalities_Action;
import research.PublicProtocolVerification_Action;

public class Protocol_Template_Scenarios {

	@Test(priority = 1)
	public void CreateOrganisationScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewOrganisationProtocolTemplate();
	}

	@Test(priority = 2)
	public void CreatePrivateScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewPrivateProtocolTemplate();
	}

	@Test(priority = 3)
	public void CreateLabOnlyScopeProtocolTemplate() throws Exception {
		Create_Protocol_Template_Action Protocol = new Create_Protocol_Template_Action();
		Protocol.createNewLabOnlyProtocolTemplate();
	}

	@Test(priority = 4)
	public void AddProtocolDescription() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateDescription();
	}

	@Test(priority = 5)
	public void AddProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateStep();
	}

	@Test(priority = 6)
	public void EditProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateStep();
	}

	@Test(priority = 7)
	public void DeleteProtocolStep() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.deleteProtocolTemplateStep();
	}

	@Test(priority = 8)
	public void AddProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateNote();
	}

	@Test(priority = 9)
	public void EditProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.editProtocolTemplateNote();
	}

	@Test(priority = 10)
	public void DeleteProtocolNote() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.deleteProtocolTemplateNote();
	}

	@Test(priority = 11)
	public void AddProtocolTemplateAttachment() throws Exception {
		Edit_Protocol_Template_Action EditProtocol = new Edit_Protocol_Template_Action();
		EditProtocol.addProtocolTemplateAttachment();
	}

	@Test(priority = 12)
	public void OrganisationProtocolScopeValidation() throws Exception {
		PublicProtocolVerification_Action PublicProtocolVal = new PublicProtocolVerification_Action();
		PublicProtocolVal.verifyPublicProtocolInOtherLabUnderSameOrganisation();
	}

	@Test(priority = 13)
	public void ExportProtocolInDocFormat() throws Exception {
		Export_Protocol_In_Doc_Format_Action ExProInDoc = new Export_Protocol_In_Doc_Format_Action();
		ExProInDoc.exportProtocolInDocFormat();
	}

	@Test(priority = 14)
	public void ValidationOfDefaultVisibilityForProtocol() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.VerifyDefaultVisiblityScopeForProtocol();
	}

	@Test(priority = 15)
	public void ValidationOfDefaultVisibilityForProtocolLoadedAsExperiment() throws Exception {
		Experiment_and_Protocol_Default_Visiblity_Scope_Action defVisiblity = new Experiment_and_Protocol_Default_Visiblity_Scope_Action();
		defVisiblity.ValidationOfVisiblityScopeForProtocolTemplateLoadedAsExperiment();
	}

	@Test(priority = 16)
	public void ProtocolTemplateList_SwitchView() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action protocolListFunc = new ProtocolTemplateListPageFunctionalities_Action();
		protocolListFunc.switchProtocolTemplateListView();
	}

	@Test(priority = 17)
	public void FilterProtocolList_ByCreator_ByDateRange_ByLab() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action protocolListFunc = new ProtocolTemplateListPageFunctionalities_Action();
		protocolListFunc.protocolTemplateListFilters();
	}

	@Test(priority = 18)
	public void DownloadProtocolTemplateList() throws Exception {
		ProtocolTemplateListPageFunctionalities_Action download = new ProtocolTemplateListPageFunctionalities_Action();
		download.downloadProtocolTemplateList();
	}
}