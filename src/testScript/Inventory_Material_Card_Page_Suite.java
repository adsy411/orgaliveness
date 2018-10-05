package testScript;

import org.testng.annotations.Test;

import inventory.Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS_File;
import inventory.Disposed_Material_Reactivation;
import inventory.Material_CardView_AddBarcode;
import inventory.Material_CardView_DeleteMaterial;
import inventory.Material_CardView_DisposeMaterial;
import inventory.Material_CardView_Edit_Quantity;
import inventory.Material_Card_View_Add_GHS_Symbol;
import inventory.Material_Card_View_RequestMaterial;
import inventory.Material_Card_View_Sigma_Material_View_SDS;
import inventory.Material_Card_View_Sigma_Product_No_SDS_File_Verification;
import inventory.Material_Card_View_Third_Party_Vendor_Material_View_SDS;
import inventory.Material_Card_View_Update_Qty_0_Verify_Dispose_Modal;
import inventory.Material_Search;
import inventory.Materials_Page_Card_Color_Verification;
import inventory.Materials_Page_Filter_Search_Expiration_Date;
import inventory.Materials_Page_Filter_Search_Location_Name;
import inventory.Materials_Page_Filter_Search_Material_Type;
import inventory.Materials_Page_Filter_Search_Material_status;
import inventory.Materials_Page_Multi_Select_Delete_Materials;
import inventory.Materials_Page_Multi_Select_Dispose_Materials;
import inventory.Materials_Page_SelectAll_MaterialDispose;
import inventory.Materials_Page_SelectAll_Material_Request;
import inventory.Materials_Page_SelectAll_Materials_Delete;
import inventory.Materials_Page_Single_Select_Material_Request;
import testBase.TestBase;

public class Inventory_Material_Card_Page_Suite extends TestBase
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
	public void Card_View_Request_Material() throws Exception
	{
		Material_Card_View_RequestMaterial cardViewRequestMaterial = new Material_Card_View_RequestMaterial();
		cardViewRequestMaterial.CardViewRequestMaterial();
		cardViewRequestMaterial.Logout();
	}

	@Test(priority = 2)
	public void Card_View_Add_Barcode() throws Exception
	{
		Material_CardView_AddBarcode cardViewAddBarcode = new Material_CardView_AddBarcode();
		cardViewAddBarcode.CardViewAddBarcode();
		cardViewAddBarcode.Logout();
	}
	
	@Test(priority = 3)
	public void Card_View_Sigma_Vendor_Material_View_SDS() throws Exception
	{
		Material_Card_View_Sigma_Material_View_SDS viewSDS = new Material_Card_View_Sigma_Material_View_SDS();
		viewSDS.CardView_SigmaMaterial_ViewSDS();
	}
	
	@Test(priority = 4)
	public void Card_View_Sigma_Vendor_Material_No_SDS_File_Verification() throws Exception
	{
		Material_Card_View_Sigma_Product_No_SDS_File_Verification viewSDS = new Material_Card_View_Sigma_Product_No_SDS_File_Verification();
		viewSDS.CardView_SigmaMaterial_NoSDSFile_Verification();
	}
	
	@Test(priority = 5)
	public void Card_View_Third_Party_Vendor_Material_View_PDF_Type_SDS_File() throws Exception
	{
		Material_Card_View_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_Card_View_Third_Party_Vendor_Material_View_SDS();
		viewSDS.CardView_ThirdPartyVendorMaterial_View_PDF_OR_Image_Type_SDS_File();
	}
	
	@Test(priority = 6)
	public void Card_View_Third_Party_Vendor_Material_View_Excel_Type_SDS_File() throws Exception
	{
		Material_Card_View_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_Card_View_Third_Party_Vendor_Material_View_SDS();
		viewSDS.CardView_ThirdPartyVendorMaterial_View_Excel_Type_SDS_File();
	}
	
	@Test(priority = 7)
	public void Verify_Deleted_SDS_File_Attached_To_New_Material() throws Exception
	{
		Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS_File deleteSDS = new Add_Third_Party_Vendor_Material_By_Deleting_Attached_SDS_File();
		deleteSDS.Verify_Deleted_SDS_File_Attached_To_New_Material();
	}
	
	@Test(priority = 8)
	public void Card_View_Complete_Material_Disposal() throws Exception
	{
		Material_CardView_DisposeMaterial cardViewDisposeMaterial = new Material_CardView_DisposeMaterial();
		cardViewDisposeMaterial.CardViewCompleteMaterialDisposal();
	}
	
	@Test(priority = 9)
	public void Card_View_Partial_Material_Disposal() throws Exception
	{
		Material_CardView_DisposeMaterial cardViewDisposeMaterial = new Material_CardView_DisposeMaterial();
		cardViewDisposeMaterial.CardViewPartialMaterialDisposal();
	}
	
	@Test(priority = 10)
	public void Card_View_Delete_Material() throws Exception
	{
		Material_CardView_DeleteMaterial cardViewDeleteMaterial = new Material_CardView_DeleteMaterial();
		cardViewDeleteMaterial.CardViewDeleteMaterial();
		cardViewDeleteMaterial.Logout();
	}
	
	@Test(priority = 11)
	public void Card_View_Disposed_Material_Reactivation() throws Exception
	{
		Disposed_Material_Reactivation disposeMaterialReactivation = new Disposed_Material_Reactivation();
		disposeMaterialReactivation.DisposedMaterial_Reactivation_Card_View();
	}
	
	@Test(priority = 12)
	public void Card_View_Page_Edit_Quantity() throws Exception
	{
		Material_CardView_Edit_Quantity editMaterialQuantity = new Material_CardView_Edit_Quantity();
		editMaterialQuantity.MaterialCardViewPageEditQuantity();
		editMaterialQuantity.Logout();
	}
	
	@Test(priority = 13)
	public void Card_View_Update_Qty_0_Verify_Dispose_Material() throws Exception
	{
		Material_Card_View_Update_Qty_0_Verify_Dispose_Modal CardViewUpdateQty = new Material_Card_View_Update_Qty_0_Verify_Dispose_Modal();
		CardViewUpdateQty.Material_CardView_Update_Qty_0_Verify_DisposeMaterial();
	}
	
	@Test(priority = 14)
	public void Card_View_Add_GHS_Symbol() throws Exception
	{
		Material_Card_View_Add_GHS_Symbol cardViewAddGHSSymbol = new Material_Card_View_Add_GHS_Symbol();
		cardViewAddGHSSymbol.CardViewAddGHSSymbol();
	}	
	
	@Test(priority = 15)
	public void Materials_Page_Single_Select_Material_Request() throws Exception
	{
		Materials_Page_Single_Select_Material_Request singleSelectMaterialRequest = new Materials_Page_Single_Select_Material_Request();
		singleSelectMaterialRequest.MaterialsPageSingleSelectMaterialRequest();
	}
	
	@Test(priority = 16)
	public void Multi_Select_Dispose_Materials() throws Exception
	{
		Materials_Page_Multi_Select_Dispose_Materials  verifyCardColorMaterialCountLessThanMinCount = new Materials_Page_Multi_Select_Dispose_Materials();
		verifyCardColorMaterialCountLessThanMinCount.MultiSelectDisposeMaterials();
	}
	
	@Test(priority = 17)
	public void Multi_Select_Delete_Materials() throws Exception
	{
		Materials_Page_Multi_Select_Delete_Materials  verifyCardColorMaterialCountLessThanMinCount = new Materials_Page_Multi_Select_Delete_Materials();
		verifyCardColorMaterialCountLessThanMinCount.MultiSelectDeleteMaterials();
	}
	
	@Test(priority = 18)
	public void Materials_Page_SelectAll_Material_Dispose() throws Exception
	{
		Materials_Page_SelectAll_MaterialDispose selectAllMaterialsDispose = new Materials_Page_SelectAll_MaterialDispose();
		selectAllMaterialsDispose.MaterialsPageSelectAllMaterialDispose();
	}
	
	@Test(priority = 19)
	public void Materials_Page_SelectAll_Material_Request() throws Exception
	{
		Materials_Page_SelectAll_Material_Request multipleSelectMaterialRequest = new Materials_Page_SelectAll_Material_Request();
		multipleSelectMaterialRequest.MaterialsPageSelectAllMaterialRequest();
	}	
	
	@Test(priority = 20)
	public void Materials_Page_SelectAll_Material_Delete() throws Exception
	{
		Materials_Page_SelectAll_Materials_Delete selectAllMaterialsDelete = new Materials_Page_SelectAll_Materials_Delete();
		selectAllMaterialsDelete.MaterialsPageSelectAllMaterialDelete();
	}
	
	@Test(priority = 21)
	public void Materials_Page_Material_Name_Search() throws Exception
	{
		Material_Search materialNameSearch = new Material_Search();
		materialNameSearch.Material_Name_Search();
	}
	
	@Test(priority = 22)
	public void Materials_Page_Barcode_Search() throws Exception
	{
		Material_Search verifyCardColorMaterialCountLessThanMinCount = new Material_Search();
		verifyCardColorMaterialCountLessThanMinCount.MaterialsPage_Barcode_Search();
	}
	
	@Test(priority = 23)
	public void Materials_Page_Catalog_Number_Search() throws Exception
	{
		Material_Search materialNameSearch = new Material_Search();
		materialNameSearch.Catalog_Number_Search();
	}
	
	@Test(priority = 24)
	public void Materials_Page_CAS_Number_Search() throws Exception
	{
		Material_Search materialNameSearch = new Material_Search();
		materialNameSearch.CAS_Number_Search();
	}
	
	@Test(priority = 25)
	public void Materials_Page_Filter_Search_Material_Type() throws Exception
	{
		Materials_Page_Filter_Search_Material_Type  filterSearchMaterialType = new Materials_Page_Filter_Search_Material_Type();
		filterSearchMaterialType.MaterialsPage_Filter_Search_Material_Type();
	}
	
	@Test(priority = 26)
	public void Materials_Page_Filter_Search_Material_Status_Running_Low() throws Exception
	{
		Materials_Page_Filter_Search_Material_status filterSearchRunningLow = new Materials_Page_Filter_Search_Material_status();
		filterSearchRunningLow.Filter_Search_Material_Status_Running_Low();
	}
	
	@Test(priority = 27)
	public void Materials_Page_Filter_Search_Material_Status_Minimum_Quantity() throws Exception
	{
		Materials_Page_Filter_Search_Material_status  filterSearchMinimumQuantity = new Materials_Page_Filter_Search_Material_status();
		filterSearchMinimumQuantity.Filter_Search_Material_Status_Minimum_Quantity();
	}
	
	@Test(priority = 28)
	public void Materials_Page_Filter_Search_All_Material_Status_Types() throws Exception
	{
		Materials_Page_Filter_Search_Material_status  filterSearchAllMaterialStatusTypes = new Materials_Page_Filter_Search_Material_status();
		filterSearchAllMaterialStatusTypes.Filter_Search_All_Material_Status_Types();
	}
	
	@Test(priority = 29)
	public void Materials_Page_Filter_Search_Expired() throws Exception
	{
		Materials_Page_Filter_Search_Expiration_Date  filterSearchExpired = new Materials_Page_Filter_Search_Expiration_Date();
		filterSearchExpired.Filter_Search_Expired();
	}
	
	@Test(priority = 30)
	public void Materials_Page_Filter_Search_NotExpired() throws Exception
	{
		Materials_Page_Filter_Search_Expiration_Date  filterSearchNotExpired = new Materials_Page_Filter_Search_Expiration_Date();
		filterSearchNotExpired.Filter_Search_NotExpired();
	}
	
	@Test(priority = 31)
	public void Materials_Page_Filter_Search_Material_Expires_In_Next_30Days() throws Exception
	{
		Materials_Page_Filter_Search_Expiration_Date  filterSearchExpiresInNext30Days = new Materials_Page_Filter_Search_Expiration_Date();
		filterSearchExpiresInNext30Days.Filter_Search_ExpiresInNext30Days();
	}
	
	@Test(priority = 32)
	public void MaterialsPage_Filter_Search_Location_Name() throws Exception
	{
		Materials_Page_Filter_Search_Location_Name  verifyCardColorMaterialCountLessThanMinCount = new Materials_Page_Filter_Search_Location_Name();
		verifyCardColorMaterialCountLessThanMinCount.MaterialsPage_Filter_Search_Location_Name();
	}
	
	@Test(priority = 33)
	public void Verify_Card_Color_Min_Count_Null() throws Exception
	{
		Materials_Page_Card_Color_Verification  verifyCardColorMinCountNull = new Materials_Page_Card_Color_Verification();
		verifyCardColorMinCountNull.Verify_Card_Color_Min_Count_Null();
	}
	
	@Test(priority = 34)
	public void Verify_Card_Color_Material_Count_Greater_Than_MinCount() throws Exception
	{
		Materials_Page_Card_Color_Verification  verifyCardColorMaterialCountGreaterThanMinCount = new Materials_Page_Card_Color_Verification();
		verifyCardColorMaterialCountGreaterThanMinCount.Verify_Card_Color_MaterialCount_GreaterThan_MinCount();
	}
	
	@Test(priority = 35)
	public void Verify_Card_Color_MaterialCount_EqualTo_MinCount() throws Exception
	{
		Materials_Page_Card_Color_Verification  verifyCardColorMaterialCountEqualToMinCount = new Materials_Page_Card_Color_Verification();
		verifyCardColorMaterialCountEqualToMinCount.Verify_Card_Color_MaterialCount_EqualTo_MinCount();
	}
	
	@Test(priority = 36)
	public void Verify_Card_Color_MaterialCount_LessThan_MinCount() throws Exception
	{
		Materials_Page_Card_Color_Verification  verifyCardColorMaterialCountLessThanMinCount = new Materials_Page_Card_Color_Verification();
		verifyCardColorMaterialCountLessThanMinCount.Verify_Card_Color_MaterialCount_LessThan_MinCount();
	}
	
	/*//Logout from an application.
	@AfterTest
	public void Logout() throws Exception
	{
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
	}*/	
}
