package testScript;

import org.testng.annotations.Test;
import inventory.AddSigmaVendorMaterial;
import inventory.AddThirdPartyVendorMaterial;
import inventory.Add_Material_Auto_Complete_Existing_Lab_Catalog_Product;
import inventory.Add_Material_Auto_Complete_Update_Existing_Sigma_Vendor_Product;
import inventory.Add_Material_Auto_Complete_Update_Existing_Third_Party_Vendor_Product;
import inventory.Add_Sigma_Vendor_Material_With_PackSize;
import inventory.Add_Third_Party_Vendor_Material_Update_Existing_SDS_File;
import inventory.Add_Third_Party_Vendor_Material_With_SDS;
import inventory.Add_Bulk_Of_Materials;
import testBase.TestBase;

public class Inventory_Add_Material_Page_Suite extends TestBase
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
	public void Add_Third_Party_Vendor_Material() throws Exception
	{
		AddThirdPartyVendorMaterial addThirdPartyVendorMaterial = new AddThirdPartyVendorMaterial();
		addThirdPartyVendorMaterial.AddThirdPartyMaterial();
	}
	
	@Test(priority = 2)
	public void Add_SigmaAldrich_Vendor_Material() throws Exception
	{
		AddSigmaVendorMaterial addSigmaVendorMaterial = new AddSigmaVendorMaterial();
		addSigmaVendorMaterial.AddSigmaAldrichVendorMaterial();
	}
	
	@Test(priority = 3)
	public void Add_Material_Auto_Complete_Lab_Catalog_Product() throws Exception
	{
		Add_Material_Auto_Complete_Existing_Lab_Catalog_Product addExistingMaterial = new Add_Material_Auto_Complete_Existing_Lab_Catalog_Product();
		addExistingMaterial.Add_Material_Auto_Complete_Existing_Product();
	}
	
	@Test(priority = 4)
	public void Add_Material_Update_Existing_Sigma_Vendor_Product() throws Exception
	{
		Add_Material_Auto_Complete_Update_Existing_Sigma_Vendor_Product addExistingSigmaMaterial = new Add_Material_Auto_Complete_Update_Existing_Sigma_Vendor_Product();
		addExistingSigmaMaterial.AddMaterialUpdateExistingSigmaVendorProduct();
	}
	
	@Test(priority = 5)
	public void Add_Material_Update_Existing_Third_Party_Vendor_Product() throws Exception
	{
		Add_Material_Auto_Complete_Update_Existing_Third_Party_Vendor_Product addExistingThirdPartyMaterial = new Add_Material_Auto_Complete_Update_Existing_Third_Party_Vendor_Product();
		addExistingThirdPartyMaterial.AddMaterialUpdateExistingThirdPartyVendorProduct();
	}
	
	@Test(priority = 6)
	public void Add_Materials_In_Bulk() throws Exception
	{
		Add_Bulk_Of_Materials addMaterialsInBulk = new Add_Bulk_Of_Materials();
		addMaterialsInBulk.Add_Materials_In_Bulk();
	}
	
	@Test(priority = 7)
	public void Add_Third_Party_Vendor_Material_With_PDF_Type_SDS_File() throws Exception
	{
		Add_Third_Party_Vendor_Material_With_SDS addSDS = new Add_Third_Party_Vendor_Material_With_SDS();
		addSDS.Add_Third_Party_Vendor_Material_With_SDS_PDF_File();
	}
	@Test(priority = 8)
	public void Add_Third_Party_Vendor_Material_With_Image_Type_SDS_File() throws Exception
	{
		Add_Third_Party_Vendor_Material_With_SDS addSDS = new Add_Third_Party_Vendor_Material_With_SDS();
		addSDS.Add_Third_Party_Vendor_Material_With_SDS_Image_File();
	}
	
	@Test(priority = 9)
	public void Add_Third_Party_Vendor_Material_With_Excel_Type_SDS_File() throws Exception
	{
		Add_Third_Party_Vendor_Material_With_SDS addSDS = new Add_Third_Party_Vendor_Material_With_SDS();
		addSDS.Add_Third_Party_Vendor_Material_With_SDS_Excel_File();
	}
	
	@Test(priority = 10)
	public void Add_Sigma_Material_With_PackSize() throws Exception
	{
		Add_Sigma_Vendor_Material_With_PackSize addSigmaMaterialWithPacksize = new Add_Sigma_Vendor_Material_With_PackSize();
		addSigmaMaterialWithPacksize.Add_Sigma_Material_With_PackSize();
	}
	
	@Test(priority = 11)
	public void Add_Third_Party_Vendor_Material_Update_Existing_Material_SDS_File() throws Exception
	{
		Add_Third_Party_Vendor_Material_Update_Existing_SDS_File updateSDS = new Add_Third_Party_Vendor_Material_Update_Existing_SDS_File();
		updateSDS.Add_Third_Party_Vendor_Material_Update_Existing_Material_SDS_File();
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