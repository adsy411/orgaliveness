package testScript;

import org.testng.annotations.Test;
import inventory.Material_ListView_AddBarcode;
import inventory.Material_ListView_DeleteMaterial;
import inventory.Material_ListView_DisposeMaterial;
import inventory.Material_ListView_RequestMaterial;
import inventory.Material_List_View_Sigma_Material_View_SDS;
import inventory.Material_List_View_Third_Party_Vendor_Material_View_SDS;
import testBase.TestBase;

public class Inventory_Material_List_Page_Suite extends TestBase
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
	public void List_View_Request_Material() throws Exception
	{
		Material_ListView_RequestMaterial listViewRequestMaterial = new Material_ListView_RequestMaterial();
		listViewRequestMaterial.ListViewRequestMaterial();
	}
	
	@Test(priority = 2)
	public void List_View_Add_Barcode() throws Exception
	{
		Material_ListView_AddBarcode listViewAddBarcode = new Material_ListView_AddBarcode();
		listViewAddBarcode.ListViewAddBarcode();
	}
	
	@Test(priority = 3)
	public void List_View_Dispose_Material() throws Exception
	{
		Material_ListView_DisposeMaterial listViewDisposeMaterial = new Material_ListView_DisposeMaterial();
		listViewDisposeMaterial.ListViewDisposeMaterial();
	}
	
	@Test(priority = 4)
	public void List_View_Delete_Material() throws Exception
	{
		Material_ListView_DeleteMaterial listViewDeleteMaterial = new Material_ListView_DeleteMaterial();
		listViewDeleteMaterial.ListViewDeleteMaterial();
	}
	
	@Test(priority = 5)
	public void List_View_Sigma_Vendor_Material_View_SDS() throws Exception
	{
		Material_List_View_Sigma_Material_View_SDS viewSDS = new Material_List_View_Sigma_Material_View_SDS();
		viewSDS.ListView_SigmaMaterial_ViewSDS();
	}
	
	@Test(priority = 6)
	public void List_View_Third_Party_Vendor_Material_View_PDF_Type_SDS_File() throws Exception
	{
		Material_List_View_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_List_View_Third_Party_Vendor_Material_View_SDS();
		viewSDS.ListView_ThirdPartyVendorMaterial_View_PDF_OR_Image_Type_SDS_File();
	}
	
	@Test(priority = 7)
	public void List_View_Third_Party_Vendor_Material_View_Excel_Type_SDS_File() throws Exception
	{
		Material_List_View_Third_Party_Vendor_Material_View_SDS viewSDS = new Material_List_View_Third_Party_Vendor_Material_View_SDS();
		viewSDS.ListView_ThirdPartyVendorMaterial_View_Excel_Type_SDS_File();
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
