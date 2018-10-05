package testScript;

import org.testng.annotations.Test;
import inventory.Add_Material_with_Storage;
import inventory.Material_Card_View_Assign_New_Location;
import inventory.Material_Detail_Assign_New_Storage;
import inventory.Material_List_View_Assign_New_Location;
import inventory.Materials_Page_SelectAll_Assign_New_Storage;
import inventory.Storage_Delete;
import inventory.Storage_Edit;
import inventory.Storage_Search;
import inventory.SubStorage_Level2_Delete;
import inventory.SubStorage_Level2_Edit;
import inventory.SubStorage_Level2_Move;
import inventory.SubStorage_Search;
import testBase.TestBase;

public class Storage_Suite extends TestBase
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
	public void AddMaterialWithStorage() throws Exception
	{
		Add_Material_with_Storage addMaterialWithStorage = new Add_Material_with_Storage();
		addMaterialWithStorage.AddMaterialWithStorage();
	}
	
	@Test(priority = 2)
	public void MaterialDetail_Assign_New_Location() throws Exception
	{
		Material_Detail_Assign_New_Storage materialDetailAssignLocation = new Material_Detail_Assign_New_Storage();
		materialDetailAssignLocation.MaterialDetail_Assign_New_Location();
	}	
	
	@Test(priority = 3)
	public void Material_Card_View_Page_Assign_New_Location() throws Exception
	{
		Material_Card_View_Assign_New_Location materialCardViewAssignLocation = new Material_Card_View_Assign_New_Location();
		materialCardViewAssignLocation.Material_Card_View_Page_Assign_New_Location();
	}
	
	@Test(priority = 4)
	public void Material_List_View_Page_Assign_New_Location() throws Exception
	{
		Material_List_View_Assign_New_Location materialListViewAssignLocation = new Material_List_View_Assign_New_Location();
		materialListViewAssignLocation.Material_List_View_Page_Assign_New_Location();
	}
	
	@Test(priority = 5)
	public void Materials_Page_SelectAll_Assign_New_Location() throws Exception
	{
		Materials_Page_SelectAll_Assign_New_Storage selectAllAssignLocation = new Materials_Page_SelectAll_Assign_New_Storage();
		selectAllAssignLocation.Materials_Page_SelectAll_Assign_New_Location();
	}
	
	@Test(priority = 6)
	public void AddMaterialPage_Delete_Location() throws Exception
	{
		Storage_Delete deleteLocation = new Storage_Delete();
		deleteLocation.Add_Material_Page_Delete_Location();
	}
	
	@Test(priority = 7)
	public void MaterialsCardViewPage_Delete_Location() throws Exception
	{
		Storage_Delete deleteLocation = new Storage_Delete();
		deleteLocation.Material_Card_View_Page_Delete_Location();
	}
	
	@Test(priority = 8)
	public void MaterialsListViewPage_Delete_Location() throws Exception
	{
		Storage_Delete deleteLocation = new Storage_Delete();
		deleteLocation.Material_List_View_Page_Delete_Location();
	}
	
	@Test(priority = 9)
	public void MaterialDetailPage_Delete_Location() throws Exception
	{
		Storage_Delete deleteLocation = new Storage_Delete();
		deleteLocation.Material_Detail_Page_Delete_Location();
	}
	
	@Test(priority = 10)
	public void MaterialsPage_SelectAll_Delete_Location() throws Exception
	{
		Storage_Delete deleteLocation = new Storage_Delete();
		deleteLocation.Materials_Page_Select_All_Delete_Location();
	}
	
	@Test(priority = 11)
	public void AddMaterialPage_Edit_Location() throws Exception
	{
		Storage_Edit updateLocation = new Storage_Edit();
		updateLocation.Add_Material_Page_Edit_Location();
	}
	
	@Test(priority = 12)
	public void MaterialCardViewPage_Edit_Location() throws Exception
	{
		Storage_Edit updateLocation = new Storage_Edit();
		updateLocation.Material_Card_View_Page_Edit_Location();
	}
	
	@Test(priority = 13)
	public void MaterialsListViewPage_Edit_Location() throws Exception
	{
		Storage_Edit updateLocation = new Storage_Edit();
		updateLocation.Material_List_View_Page_Edit_Location();
	}
	
	@Test(priority = 14)
	public void MaterialDetailPage_Edit_Location() throws Exception
	{
		Storage_Edit updateLocation = new Storage_Edit();
		updateLocation.Material_Detail_Page_Edit_Location();
	}
	
	@Test(priority = 15)
	public void MaterialsPage_SelectAll_Edit_Location() throws Exception
	{
		Storage_Edit updateLocation = new Storage_Edit();
		updateLocation.Materials_Page_Select_All_Edit_Location();
	}
	
	@Test(priority = 16)
	public void AddMaterialPage_Location_Search() throws Exception
	{
		Storage_Search locationSearch = new Storage_Search();
		locationSearch.AddMaterialPage_Location_Search();
	}
	
	@Test(priority = 17)
	public void MaterialsCardViewPage_Location_Search() throws Exception
	{
		Storage_Search locationSearch = new Storage_Search();
		locationSearch.MaterialsCardViewPage_Location_Search();
	}
	
	@Test(priority = 18)
	public void MaterialsListViewPage_Location_Search() throws Exception
	{
		Storage_Search locationSearch = new Storage_Search();
		locationSearch.MaterialsListViewPage_Location_Search();
	}
	
	@Test(priority = 19)
	public void MaterialDetailPage_Location_Search() throws Exception
	{
		Storage_Search locationSearch = new Storage_Search();
		locationSearch.MaterialDetailPage_Location_Search();
	}
	
	@Test(priority = 20)
	public void AddMaterial_With_SubStorage() throws Exception
	{
		Add_Material_with_Storage addMaterialWithStorage = new Add_Material_with_Storage();
		addMaterialWithStorage.AddMaterial_With_Level2_Storage();
	}
	
	@Test(priority = 21)
	public void AddMaterialPage_Delete_Level2_SubLocation() throws Exception
	{
		SubStorage_Level2_Delete deleteLevel2SubLocation = new SubStorage_Level2_Delete();
		deleteLevel2SubLocation.AddMaterialPage_Delete_Level2_SubLocation();
	}
	
	@Test(priority = 22)
	public void AddMaterialPage_Edit_Level2_SubLocation() throws Exception
	{
		SubStorage_Level2_Edit editLevel2SubLocation = new SubStorage_Level2_Edit();
		editLevel2SubLocation.AddMaterialPage_Edit_Level2_SubLocation();
	}
	
	@Test(priority = 23)
	public void AddMaterialPage_SubLocation_Search() throws Exception
	{
		SubStorage_Search subLocationSearch = new SubStorage_Search();
		subLocationSearch.AddMaterialPage_SubLocation_Search();
	}
	
	@Test(priority = 24)
	public void AddMaterialPage_Move_Level2_SubLocation() throws Exception
	{
		SubStorage_Level2_Move moveLevel2SubLocation = new SubStorage_Level2_Move();
		moveLevel2SubLocation.Add_Material_Page_Move_Level2_SubLocation();
	}
}