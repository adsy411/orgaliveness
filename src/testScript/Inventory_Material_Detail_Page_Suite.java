package testScript;

import org.testng.annotations.Test;

import inventory.Disposed_Material_Reactivation;
import inventory.Material_Detail_Add_Barcode;
import inventory.Material_Detail_Attach_Files;
import inventory.Material_Detail_DeleteMaterial;
import inventory.Material_Detail_Delete_Attached_File;
import inventory.Material_Detail_DisposeMaterial;
import inventory.Material_Detail_Download_Attached_Files;
import inventory.Material_Detail_Material_Log;
import inventory.Material_Detail_Sigma_Material_View_SDS;
import inventory.Material_Detail_Third_Party_Vendor_Material_Add_SDS_File;
import inventory.Material_Detail_Third_Party_Vendor_Material_Update_Existing_SDS;
import inventory.Material_Detail_Third_Party_Vendor_Material_View_SDS;
import inventory.Material_Detail_Update_Material_Details;
import inventory.Material_Detail_Update_Qty_0_Verify_Dispose_Modal;
import inventory.Material_Detail_View_Attached_Files;
import inventory.Material_detail_Request_Material;
import testBase.TestBase;

public class Inventory_Material_Detail_Page_Suite extends TestBase
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
	}*/
	
	@Test(priority = 1)
	public void Material_Detail_Update_Material_Details() throws Exception
	{
		Material_Detail_Update_Material_Details updateMaterialDetails = new Material_Detail_Update_Material_Details();
		updateMaterialDetails.MaterialDetailUpdateDetails();
	}
	
	@Test(priority = 2)
	public void Material_Detail_Request_Material() throws Exception
	{
		Material_detail_Request_Material materialDetailRequestMaterial = new Material_detail_Request_Material();
		materialDetailRequestMaterial.MaterialDetailRequestMaterial();
		materialDetailRequestMaterial.Logout();
	}
	
	@Test(priority = 3)
	public void Material_Detail_Add_Barcode() throws Exception
	{
		Material_Detail_Add_Barcode materialDetailAddBarcode = new Material_Detail_Add_Barcode();
		materialDetailAddBarcode.MaterialDetailAddBarcode();
	}
	
	@Test(priority = 4)
	public void Material_Detail_Complete_Material_Disposal() throws Exception
	{
		Material_Detail_DisposeMaterial materialDetailDispose = new Material_Detail_DisposeMaterial();
		materialDetailDispose.MaterialDetail_CompleteMaterialDisposal();
	}
	
	@Test(priority = 5)
	public void Material_Detail_Partial_Material_Disposal() throws Exception
	{
		Material_Detail_DisposeMaterial materialDetailDispose = new Material_Detail_DisposeMaterial();
		materialDetailDispose.MaterialDetail_PartialMaterialDisposal();
	}
	
	@Test(priority = 6)
	public void Material_Detail_Delete_Material() throws Exception
	{
		Material_Detail_DeleteMaterial materialDetailDelete = new Material_Detail_DeleteMaterial();
		materialDetailDelete.MaterialDetailDeleteMaterial();
		materialDetailDelete.Logout();
	}
	
	@Test(priority = 7)
	public void DisposedMaterial_Reactivation_Detail_Page() throws Exception
	{
		Disposed_Material_Reactivation disposeMaterialReactivation = new Disposed_Material_Reactivation();
		disposeMaterialReactivation.DisposedMaterial_Reactivation_Detail_Page();
	}
	
	@Test(priority = 8)
	public void Material_Detail_Update_Qty_0_Dispose_Material() throws Exception
	{
		Material_Detail_Update_Qty_0_Verify_Dispose_Modal disposeMaterial = new Material_Detail_Update_Qty_0_Verify_Dispose_Modal();
		disposeMaterial.MaterialDetail_Update_Qty_0_DisposeMaterial();
	}
	
	@Test(priority = 9)
	public void Material_Added_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.userLogin();
		materialLog.Material_Added_Log();
	}
	
	@Test(priority = 10)
	public void Material_Requested_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Material_Requested_Log();
	}
	
	@Test(priority = 11)
	public void Material_Quantity_Updated_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Material_Quantity_Updated_Log();
	}
	
	@Test(priority = 12)
	public void Material_Quantity_Consumed_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Material_Quantity_Consumed_Log();
	}
	
	@Test(priority = 13)
	public void Partial_Material_Disposed_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Partial_Material_Disposed_Log();
	}
	
	@Test(priority = 14)
	public void Material_Location_Assigned_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Location_Assigned_Log();
	}
	
	@Test(priority = 15)
	public void Material_Location_Changed_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Location_Changed_Log();
	}
	
	@Test(priority = 16)
	public void Material_Location_Unassigned_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Location_Unassigned_Log();
	}
	
	@Test(priority = 17)
	public void Complete_Material_Disposed_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Complete_Material_Disposed_Log();
	}
	
	@Test(priority = 18)
	public void Disposed_Material_Reactivation_Log() throws Exception
	{
		Material_Detail_Material_Log materialLog = new Material_Detail_Material_Log();
		materialLog.Disposed_Material_Reactivation_Log();
		materialLog.userLogout();
	}
	
	@Test(priority = 19)
	public void Material_Detail_Sigma_Material_View_SDS() throws Exception
	{
		Material_Detail_Sigma_Material_View_SDS viewSDS = new Material_Detail_Sigma_Material_View_SDS();
		viewSDS.MaterialDetail_SigmaMaterial_ViewSDS();
	}
	
	@Test(priority = 20)
	public void Material_Detail_Third_Party_Vendor_Material_View_PDF_Type_SDS_File() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_Detail_Third_Party_Vendor_Material_View_SDS();
		viewSDS.Material_Detail_ThirdPartyVendorMaterial_View_PDF_OR_Image_Type_SDS_File();
	}
	
	@Test(priority = 21)
	public void Material_Detail_Page_Third_Party_Vendor_Material_View_Excel_Type_SDS_File() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_Detail_Third_Party_Vendor_Material_View_SDS();
		viewSDS.Material_Detail_Page_ThirdPartyVendorMaterial_View_Excel_Type_SDS_File();
	}
	
	@Test(priority = 22)
	public void Material_Detail_Third_Party_Vendor_Material_Add_PDF_Type_SDS_File() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_Add_SDS_File addSDS = new Material_Detail_Third_Party_Vendor_Material_Add_SDS_File();
		addSDS.Material_Detail_Third_Party_Vendor_Material_Add_PDF_OR_Image_Type_SDS_File();
	}
	
	@Test(priority = 23)
	public void Material_Detail_Third_Party_Vendor_Material_Add_Excel_Type_SDS_File() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_Add_SDS_File addSDS = new Material_Detail_Third_Party_Vendor_Material_Add_SDS_File();
		addSDS.Material_Detail_Third_Party_Vendor_Material_Add_Excel_Type_SDS_File();
	}
	
	@Test(priority = 24)
	public void Material_Detail_ThirdPartyVendorMaterial_Update_Existing_SDS_File() throws Exception
	{
		Material_Detail_Third_Party_Vendor_Material_Update_Existing_SDS updateSDS = new Material_Detail_Third_Party_Vendor_Material_Update_Existing_SDS();
		updateSDS.Material_Detail_ThirdPartyVendorMaterial_Update_Existing_SDS_File();
	}
	
	@Test(priority = 25)
	public void Material_Detail_View_Attached_PDF_OR_Image_Type_File() throws Exception
	{
		Material_Detail_View_Attached_Files viewAttachedFile = new Material_Detail_View_Attached_Files();
		viewAttachedFile.Material_Detail_View_Attached_PDF_OR_Image_Type_File();
	}
	
	@Test(priority = 26)
	public void Material_Detail_View_Attached_Excel_Type_File() throws Exception
	{
		Material_Detail_View_Attached_Files viewAttachedFile = new Material_Detail_View_Attached_Files();
		viewAttachedFile.Material_Detail_View_Attached_Excel_Type_File();
	}
	
	@Test(priority = 27)
	public void Material_Detail_Download_Attached_File() throws Exception
	{
		Material_Detail_Download_Attached_Files downloadAttachedFile = new Material_Detail_Download_Attached_Files();
		downloadAttachedFile.Material_Detail_Download_Attached_File();
	}
	
	@Test(priority = 28)
	public void Material_Detail_Page_Delete_Attached_File() throws Exception
	{
		Material_Detail_Delete_Attached_File deleteAttachedFile = new Material_Detail_Delete_Attached_File();
		deleteAttachedFile.Material_Detail_Page_Delete_Attached_File();
	}
	
	@Test(priority = 29)
	public void Material_Detail_Attach_PDF_Type_File() throws Exception
	{
		Material_Detail_Attach_Files attachFile = new Material_Detail_Attach_Files();
		attachFile.Material_Detail_Attach_PDF_Type_File();
	}
	
	@Test(priority = 30)
	public void Material_Detail_Attach_Image_Type_File() throws Exception
	{
		Material_Detail_Attach_Files attachFile = new Material_Detail_Attach_Files();
		attachFile.Material_Detail_Attach_Image_Type_File();
	}
	
	@Test(priority = 31)
	public void Material_Detail_Attach_Excel_Type_File() throws Exception
	{
		Material_Detail_Attach_Files attachFile = new Material_Detail_Attach_Files();
		attachFile.Material_Detail_Attach_Excel_Type_File();
	}
	
	/*//Logout from an application.
	@AfterTest
	public void Logout() throws Exception
	{
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
	}	*/
}
