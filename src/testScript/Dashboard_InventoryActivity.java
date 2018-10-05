package testScript;

import org.testng.annotations.Test;

import inventory.Dashboard_Inventory_Verify_Links;
import inventory.Dashboard_Verify_Materials_Count;
import inventory.Material_DashboardActivity;

public class Dashboard_InventoryActivity 
{
	@Test(priority = 1)
	public void AddMaterialDashboardActivityLog() throws Exception
	{
		Material_DashboardActivity addMaterialLog = new Material_DashboardActivity();
		addMaterialLog.AddMaterialDashboardActivityLog();
	}

	@Test(priority = 2)
	public void MaterialDetail_RequestMaterial_DashboardActivityLog() throws Exception
	{
		Material_DashboardActivity requestMaterialLog = new Material_DashboardActivity();
		requestMaterialLog.MaterialDetail_RequestMaterial_DashboardActivityLog();
	}
	
	@Test(priority = 3)
	public void MaterialDetail_AssignLocation_DashboardActivityLog() throws Exception
	{
		Material_DashboardActivity assignLocationToMaterialLog = new Material_DashboardActivity();
		assignLocationToMaterialLog.MaterialDetail_AssignLocation_DashboardActivityLog();
	}
	
	@Test(priority = 4)
	public void Material_Quantity_Updated_DashboardActivityLog() throws Exception
	{
		Material_DashboardActivity materialQuantityUpdatedLog = new Material_DashboardActivity();
		materialQuantityUpdatedLog.Material_Quantity_Updated_DashboardActivityLog();
	}
	
	@Test(priority = 5)
	public void Material_Quantity_Consumed_DashboardActivityLog() throws Exception
	{
		Material_DashboardActivity materialQuantityConsumedLog = new Material_DashboardActivity();
		materialQuantityConsumedLog.Material_Quantity_Consumed_DashboardActivityLog();
	}
	
	@Test(priority = 6)
	public void Dashboard_Page_Inventory_Verify_Add_Add_New_Icon_Functionality() throws Exception
	{
		Dashboard_Inventory_Verify_Links VerifyInventoryLinks = new Dashboard_Inventory_Verify_Links();
		VerifyInventoryLinks.Dashboard_Page_Inventory_Verify_Add_Add_New_Icon_Functionality();
	}
	
	@Test(priority = 7)
	public void Dashboard_Page_Inventory_Verify_View_In_Inventory_Link_Functionality() throws Exception
	{
		Dashboard_Inventory_Verify_Links VerifyInventoryLinks = new Dashboard_Inventory_Verify_Links();
		VerifyInventoryLinks.Dashboard_Page_Inventory_Verify_View_In_Inventory_Link_Functionality();
	}
	
	@Test(priority = 8)
	public void Dashboard_Verify_Materials_Count_After_Add_OR_Delete_Material() throws Exception
	{
		Dashboard_Verify_Materials_Count VerifyInventoryLinks = new Dashboard_Verify_Materials_Count();
		VerifyInventoryLinks.Dashboard_Verify_Materials_Count_After_Add_OR_Delete_Material();
	}	
}
