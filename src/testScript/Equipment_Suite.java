package testScript;

import org.testng.annotations.Test;
import equipment.Add_Equipment_Action;
import equipment.Delete_Equipment;

public class Equipment_Suite {
	
	@Test(priority = 1)
	public void AddEquipment_SigmaAldrich() throws Exception {
		Add_Equipment_Action AddEquip = new Add_Equipment_Action();
		AddEquip.addSigmaAldrichEquipment();
	}
	
	@Test(priority = 2)
	public void AddEquipment_ThirdPartyProduct() throws Exception {
		Add_Equipment_Action AddEquip = new Add_Equipment_Action();
		AddEquip.addThirdPartyEquipment();
	}
	
	@Test(priority = 3)
	public void AddNewManufacturerAndType() throws Exception {
		Add_Equipment_Action AddEquip = new Add_Equipment_Action();
		AddEquip.add_NewManufacturer_NewType();
	}
	
	@Test(priority = 4)
	public void DuplicateManufacturerAndTypeValidation() throws Exception {
		Add_Equipment_Action AddEquip = new Add_Equipment_Action();
		AddEquip.val_DuplicateManufacturer_DuplicateType();
	}
	
	@Test(priority = 5)
	public void ValidationOfPredefinedManufacturerAndTypeLists() throws Exception {
		Add_Equipment_Action AddEquip = new Add_Equipment_Action();
		AddEquip.validationOfPredefinedManufacturerAndTypeLists();
	}
	
	@Test(priority = 6)
	public void DeleteEquipmentFromEquipmentList() throws Exception {
		Delete_Equipment DeleteEquip = new Delete_Equipment();
		DeleteEquip.deleteEquipment_EquipmentList();
	}
	
	@Test(priority = 7)
	public void DeleteEquipmentFromEquipmentDetailPage() throws Exception {
		Delete_Equipment DeleteEquip = new Delete_Equipment();
		DeleteEquip.deleteEquipment_EquipmentDetailPage();
	}
}