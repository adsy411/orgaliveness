package testScript;

import org.testng.annotations.Test;
import inventory.Lab_Member_Detail_Page_DisposeMaterial;
import inventory.Lab_Member_Material_CardView_DisposeMaterial;
import testBase.TestBase;

public class Inventory_Lab_Member_Functions_Suite extends TestBase
{
	@Test(priority = 1)
	public void LabMember_CardView_CompleteMaterialDisposal() throws Exception
	{
		Lab_Member_Material_CardView_DisposeMaterial cardViewDisposeMaterial = new Lab_Member_Material_CardView_DisposeMaterial();
		cardViewDisposeMaterial.LabMember_CardView_CompleteMaterialDisposal();
	}
	
	@Test(priority = 2)
	public void LabMember_CardView_PartialMaterialDisposal() throws Exception
	{
		Lab_Member_Material_CardView_DisposeMaterial cardViewDisposeMaterial = new Lab_Member_Material_CardView_DisposeMaterial();
		cardViewDisposeMaterial.LabMember_CardView_PartialMaterialDisposal();
	}
	
	@Test(priority = 3)
	public void LabMember_MaterialDetail_CompleteMaterialDisposal() throws Exception
	{
		Lab_Member_Detail_Page_DisposeMaterial detailPageDisposeMaterial = new Lab_Member_Detail_Page_DisposeMaterial();
		detailPageDisposeMaterial.LabMember_MaterialDetail_CompleteMaterialDisposal();
	}
	
	@Test(priority = 4)
	public void LabMember_MaterialDetail_PartialMaterialDisposal() throws Exception
	{
		Lab_Member_Detail_Page_DisposeMaterial detailPageDisposeMaterial = new Lab_Member_Detail_Page_DisposeMaterial();
		detailPageDisposeMaterial.LabMember_MaterialDetail_PartialMaterialDisposal();
	}
	
	/*//Logout from an application.
	@Test(priority = 3)
	public void Logout() throws Exception
	{
		InventoryRegularFunctions logout = new InventoryRegularFunctions();
		logout.UserLogout();
		driver.quit();
	}	*/
}
