package testScript;

import org.testng.annotations.Test;

import inventory.Add_Material_Error_Message_Validation_Scenario;
import inventory.Add_Material_Page_SDS_Validation_Scenario;
import inventory.Barcode_Modal_Error_Message_Validation_Scenarios;
import inventory.Card_View_Add_GHS_Modal_Validation_Scenario;
import inventory.Dispose_Modal_Error_Message_Validation_Scenarios;
import inventory.Disposed_Material_Detail_Page_Warning_Message_Validation_Scenarios;
import inventory.Material_Card_View_Edit_Quantity_Validation_Scenario;
import inventory.Material_Detail_Error_Message_Validation_Scenario;
import inventory.Material_Detail_File_Upload_Validation_Scenarios;
import inventory.Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios;
import inventory.Materials_Page_Third_Party_Vendor_Material_SDS_Validation_Scenarios;
import testBase.TestBase;

public class Inventory_Error_And_Validation_Scenarios_Suite extends TestBase
{
	/*@BeforeTest
	public void Login() throws Exception
	{
		//Fetch the user name and password
		ExcelUtils.setExcelFile(InventoryConstants.Path_TestData + InventoryConstants.File_TestData,"Login");
		String userName = ExcelUtils.getCellData(2, 0);
		String password = ExcelUtils.getCellData(2, 1);
			
		//Login in to application
		init();
		InventoryRegularFunctions login = new InventoryRegularFunctions();
		login.UserLogin(userName,password);			
	}
	*/
	@Test(priority = 1)
	public void AddMaterial_Error_Message_Validation_Scenario() throws Exception
	{
		Add_Material_Error_Message_Validation_Scenario addMaterial = new Add_Material_Error_Message_Validation_Scenario();
		addMaterial.AddMaterial_Error_Message_Validation_Scenario();
	}
	
	@Test(priority = 2)
	public void MaterialDetail_Error_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_Error_Message_Validation_Scenario materialDetail = new Material_Detail_Error_Message_Validation_Scenario();
		materialDetail.MaterialDetail_Error_Message_Validation_Scenario();
	}
	
	@Test(priority = 3)
	public void Materials_Page_BarcodeModal_Error_Message_Validation_Scenarios() throws Exception
	{
		Barcode_Modal_Error_Message_Validation_Scenarios materialsPageBarcodeModal = new Barcode_Modal_Error_Message_Validation_Scenarios();
		materialsPageBarcodeModal.Card_View_BarcodeModal_Error_Message_Validation_Scenario();
		
	}
	@Test(priority = 4)
	public void Material_Detail_BarcodeModal_Error_Message_Validation_Scenarios() throws Exception
	{
		Barcode_Modal_Error_Message_Validation_Scenarios detailPageBarcodeModal = new Barcode_Modal_Error_Message_Validation_Scenarios();
		detailPageBarcodeModal.Material_Detail_BarcodeModal_Error_Message_Validation_Scenario();
	}
	
	@Test(priority = 5)
	public void Materials_page_DisposeModal_Error_Message_Validation_Scenarios() throws Exception
	{
		Dispose_Modal_Error_Message_Validation_Scenarios materialsPageDisposeModal = new Dispose_Modal_Error_Message_Validation_Scenarios();
		materialsPageDisposeModal.Card_View_DisposeModal_Error_Message_Validation_Scenario();
	}
	
	@Test(priority = 6)
	public void Detail_Page_DisposeModal_Error_Message_Validation_Scenarios() throws Exception
	{
		Dispose_Modal_Error_Message_Validation_Scenarios detailPageDisposeModal = new Dispose_Modal_Error_Message_Validation_Scenarios();
		detailPageDisposeModal.Material_Detail_DisposeModal_Error_Message_Validation_Scenario();
	}
	
	@Test(priority = 7)
	public void Materials_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario() throws Exception
	{
		Materials_Page_Third_Party_Vendor_Material_SDS_Validation_Scenarios SDSWaringValidation = new Materials_Page_Third_Party_Vendor_Material_SDS_Validation_Scenarios();
		SDSWaringValidation.Materials_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 8)
	public void Detail_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios SDSWaringValidation = new Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios();
		SDSWaringValidation.Detail_Page_Third_Party_Vendor_Material_No_SDS_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 9)
	public void Dispose_Warning_Message_DisposedMaterial_Detail_Page() throws Exception
	{
		Disposed_Material_Detail_Page_Warning_Message_Validation_Scenarios DisposedMaterialWaringValidation = new Disposed_Material_Detail_Page_Warning_Message_Validation_Scenarios();
		DisposedMaterialWaringValidation.Dispose_Warning_Message_DisposedMaterial_Detail_Page();
	}
	
	@Test(priority = 10)
	public void Material_CardView_Edit_Quantity_Validation_Scenario() throws Exception
	{
		Material_Card_View_Edit_Quantity_Validation_Scenario editQuantityWaringValidation = new Material_Card_View_Edit_Quantity_Validation_Scenario();
		editQuantityWaringValidation.Material_CardView_Edit_Quantity_Validation_Scenario();
	}
	
	@Test(priority = 11)
	public void Materials_Page_AddGHSSymbol_Validation_Scenario() throws Exception
	{
		Card_View_Add_GHS_Modal_Validation_Scenario GHSSymbolWaringValidation = new Card_View_Add_GHS_Modal_Validation_Scenario();
		GHSSymbolWaringValidation.AddGHSSymbol_Validation_Scenario();
	}
	
	@Test(priority = 12)
	public void Add_Material_Page_SDS_File_Deletion_Validation_Scenario() throws Exception
	{
		Add_Material_Page_SDS_Validation_Scenario deleteSDSInfoValidation = new Add_Material_Page_SDS_Validation_Scenario();
		deleteSDSInfoValidation.Add_Material_Page_SDS_File_Deletion_Validation_Scenario();
	}
	
	@Test(priority = 13)
	public void Add_Material_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		Add_Material_Page_SDS_Validation_Scenario uploadSDSWarningMsgValidation = new Add_Material_Page_SDS_Validation_Scenario();
		uploadSDSWarningMsgValidation.Add_Material_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 14)
	public void Add_Material_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		Add_Material_Page_SDS_Validation_Scenario uploadSDSWarningMsgValidation = new Add_Material_Page_SDS_Validation_Scenario();
		uploadSDSWarningMsgValidation.Add_Material_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 15)
	public void Material_Detail_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios uploadSDSWarningMsgValidation = new Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios();
		uploadSDSWarningMsgValidation.Material_Detail_Page_Upload_Invalid_SDS_File_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 16)
	public void Material_Detail_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios uploadSDSWarningMsgValidation = new Material_Detail_Third_Party_Vendor_Material_SDS_Validation_Scenarios();
		uploadSDSWarningMsgValidation.Material_Detail_Page_Upload_Greater_Size_SDS_File_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 17)
	public void Material_Detail_Page_Attach_Invalid_File_Warning_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_File_Upload_Validation_Scenarios attachInvalidFileWarningMsgValidation = new Material_Detail_File_Upload_Validation_Scenarios();
		attachInvalidFileWarningMsgValidation.Material_Detail_Page_Attach_Invalid_File_Warning_Message_Validation_Scenario();
	}
	
	@Test(priority = 18)
	public void Material_Detail_Page_Attach_File_Greater_Size_Warning_Message_Validation_Scenario() throws Exception
	{
		Material_Detail_File_Upload_Validation_Scenarios attachGreaterFileSizeWarningMsgValidation = new Material_Detail_File_Upload_Validation_Scenarios();
		attachGreaterFileSizeWarningMsgValidation.Material_Detail_Page_Attach_File_Greater_Size_Warning_Message_Validation_Scenario();
	}
}
