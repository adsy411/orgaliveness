package testScript;

import org.testng.annotations.Test;

import research.Add_Material_To_Experiment_Action;
import research.Add_Material_To_Experiment_Steps_Action;
import research.Add_Product_To_ProtocolTemplate_Action;
import research.Add_Product_To_Template_Steps_Action;
import research.Delete_Material_From_Experiment_Action;
import research.Delete_Product_AddedTo_ProtocolTemplateSteps_Action;
import research.Delete_Product_From_Template_Action;
import research.Delete_Step_Material_Action;
import research.Request_Material_From_Experiment_Action;
import research.Request_Product_Added_To_Template_Action;
import research.Update_Material_In_Experiment_Action;
import research.Update_Product_In_Template_Action;
import research.Update_Step_Material_In_Experiment_Action;
import research.Update_Step_Product_In_Template_Action;

public class Research_Material_Integration_Scenarios {
	@Test(priority = 1)
	public void AddSigmaAldrichMaterialToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperiment();
	}

	@Test(priority = 2)
	public void AddThirdPartyMaterialToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperiment();
	}

	@Test(priority = 3)
	public void AddSigmaAldrichMaterialToExperimentWhichIsPresentInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperimentWhichIsPresentInInventory();
	}

	@Test(priority = 4)
	public void AddThirdPartyMaterialToExperimentWhichIsPresentInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperimentWhichIsPresentInInventory();
	}

	@Test(priority = 5)
	public void AddSigmaAldrichMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addSigmaAldrichMaterialToExperimentWhichIsPresentInCatalogButNotInInventory();
	}

	@Test(priority = 6)
	public void AddThirdPartyMaterialToExperimentWhichIsPresentInCatalogButNotInInventory() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addThirdPartyMaterialToExperimentWhichIsPresentInCatalogButNotInInventory();
	}

	@Test(priority = 7)
	public void AddMaterialToExperimentFromSigmaAldrichDatabase() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.addMaterialToExperimentFromSigmaAldrichDataBase();
	}

	@Test(priority = 8)
	public void UpdateMaterialInExperiment() throws Exception {
		Update_Material_In_Experiment_Action EditMatToExpSteps = new Update_Material_In_Experiment_Action();
		EditMatToExpSteps.updateMaterialInExperiment();
	}

	@Test(priority = 9)
	public void RequestMaterialInExperiment() throws Exception {
		Request_Material_From_Experiment_Action ReqMatFromExp = new Request_Material_From_Experiment_Action();
		ReqMatFromExp.requestMaterialFromExperiment();
	}

	@Test(priority = 10)
	public void AddMaterialToExperimentSteps() throws Exception {
		Add_Material_To_Experiment_Steps_Action AddMatToExpSteps = new Add_Material_To_Experiment_Steps_Action();
		AddMatToExpSteps.addMaterialToExperimentSteps_WhichIsPresentInExperiment();
	}

	@Test(priority = 11)
	public void UpdateStepMaterialInExperiment() throws Exception {
		Update_Step_Material_In_Experiment_Action UpdateMatInExp = new Update_Step_Material_In_Experiment_Action();
		UpdateMatInExp.updateMaterialInExperimentStep();
	}
	
	@Test(priority = 12)
	public void AddSigmaAldrichProductToProtocolTemplate() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addSigmaAldrichProductToTemplate();
	}

	@Test(priority = 13)
	public void AddThirdPartyProductToProtocolTemplate() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addThirdPartyProductToTemplate();
	}

	@Test(priority = 14)
	public void AddProductToProtocolTemplateFromSigmaAldrichDatabase() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addProductToTemplateFromSigmaDatabase();
	}

	@Test(priority = 15)
	public void AddProductToProtocolTemplateFromLabCatalog() throws Exception {
		Add_Product_To_ProtocolTemplate_Action AddProductToTemplate = new Add_Product_To_ProtocolTemplate_Action();
		AddProductToTemplate.addProductToTemplateFromCatalog();
	}

	@Test(priority = 16)
	public void UpdateProductInTemplate() throws Exception {
		Update_Product_In_Template_Action UpdateProductToTemplateSteps = new Update_Product_In_Template_Action();
		UpdateProductToTemplateSteps.updateProductInTemplate();
	}

	@Test(priority = 17)
	public void RequestProductInTemplate() throws Exception {
		Request_Product_Added_To_Template_Action RequestProductFromTemplateSteps = new Request_Product_Added_To_Template_Action();
		RequestProductFromTemplateSteps.requestProductAddedToTemplate();
	}

	@Test(priority = 18)
	public void AddProductToProtocolTemplateSteps() throws Exception {
		Add_Product_To_Template_Steps_Action AddProductToTemplateSteps = new Add_Product_To_Template_Steps_Action();
		AddProductToTemplateSteps.addProductToTemplateSteps_WhichIsPresentInTemplate();
	}

	@Test(priority = 19)
	public void AddProductToProtocolTemplateStepsWhichIsPartiallyUsedInAnotherStep() throws Exception {
		Add_Product_To_Template_Steps_Action AddProductToTemplateSteps2 = new Add_Product_To_Template_Steps_Action();
		AddProductToTemplateSteps2.addProductToTemplateSteps_WhichIsPartiallyUsedInAnotherStep();
	}

	@Test(priority = 20)
	public void UpdateProductInProtocolTemplateSteps() throws Exception {
		Update_Step_Product_In_Template_Action UpdateProductToTemplateSteps = new Update_Step_Product_In_Template_Action();
		UpdateProductToTemplateSteps.updateProductInTemplateStep();
	}
	
	@Test(priority = 21)
	public void DeleteProductFromProtocolTemplateSteps() throws Exception {
		Delete_Product_AddedTo_ProtocolTemplateSteps_Action DeleteProdFromTempSteps = new Delete_Product_AddedTo_ProtocolTemplateSteps_Action();
		DeleteProdFromTempSteps.deleteProductAddedToTemplateSteps();
	}

	@Test(priority = 22)
	public void DeleteProductFromProtocolTemplate() throws Exception {
		Delete_Product_From_Template_Action DeleteProdFromTemp = new Delete_Product_From_Template_Action();
		DeleteProdFromTemp.deleteProductFromTemplate();
	}

	@Test(priority = 23)
	public void DeleteExperimentStepMaterial() throws Exception {
		Delete_Step_Material_Action DeleteStepMatInExp = new Delete_Step_Material_Action();
		DeleteStepMatInExp.deleteStepMaterialFromExperiment();
	}

	@Test(priority = 24)
	public void DeleteMaterialFromExperiment() throws Exception {
		Delete_Material_From_Experiment_Action DeleteMatFromExp = new Delete_Material_From_Experiment_Action();
		DeleteMatFromExp.deleteMaterialFromExperiment();
	}

	@Test(priority = 25)
	public void ConsumeMaterialAddedToExperiment() throws Exception {
		Add_Material_To_Experiment_Action AddMatToExp = new Add_Material_To_Experiment_Action();
		AddMatToExp.consumeMaterialInInventoryWhichIsAddedToExperiment();
	}
}