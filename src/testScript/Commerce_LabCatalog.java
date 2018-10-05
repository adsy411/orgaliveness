package testScript;

import org.testng.annotations.Test;

import labCatalog.AddNonSigmaAldrichProducttoCatalog;
import labCatalog.AddSigmaAldrichProducttoCatalog_Old;
import labCatalog.Add_SigmaAldrich_ProducttoCatalog;
import labCatalog.DashboardActivity_EditQuantity_Negative;
import labCatalog.DashboardActivity_EditQuantity_Positive;
import labCatalog.DashboardActivity_Request_NoNSigmaAldrichProduct;
import labCatalog.DashboardActivity_Request_SigmaAldrichProduct;
import labCatalog.Delete_NonSigmaAldrichProduct_FromLabCatalog;
import labCatalog.Delete_SigmaAldrichProduct_FromLabCatalog;
import labCatalog.Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager;
import labCatalog.Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action;
import labCatalog.LabManagerDefaultApproverProcurer_Action;
import labCatalog.Request_NonSigmaAldrichProduct_FromLabCatalog;
import labCatalog.Request_SigmaAldrichProduct_FromLabCatalog;
import labCatalog.Request_SigmaAldrichProduct_FromProductDetailsPage;
import stockroomUserManagement.InviteStkroomMembersThroughBatchUpload_Action;
import stockroomUserManagement.InviteStockroomLeadAndUser_Action;
import stockroomUserManagement.LoginAsStockroomAdmin_Action;
import stockroomUserManagement.StkRoomAdminMemberListFunctionality_Action;
import stockroomUserManagement.VerifyContactUs_Action;
import stockroomUserManagement.VerifyStockroomName_Action;
import testBase.TestBase;

public class Commerce_LabCatalog extends TestBase{

	@Test(priority = 1)
	public void AddNonSigmaAldrichProducttoCatalogAsLabOwner1() throws Exception{
		AddNonSigmaAldrichProducttoCatalog Av = new AddNonSigmaAldrichProducttoCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabOwner();
	
	}
	
	@Test(priority = 2)
	public void AddNonSigmaAldrichProducttoCatalogAsLabManager2() throws Exception{
		AddNonSigmaAldrichProducttoCatalog Av = new AddNonSigmaAldrichProducttoCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabManager();
	
	}
	
	@Test(priority = 3)
	public void AddNonSigmaAldrichProducttoCatalogAsLabMember3() throws Exception{
		AddNonSigmaAldrichProducttoCatalog Av = new AddNonSigmaAldrichProducttoCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabMember();
	}

	@Test(priority = 4)
	public void AddSigmaAldrichProducttoCatalogAsLabOwner4() throws Exception{
		Add_SigmaAldrich_ProducttoCatalog Av = new Add_SigmaAldrich_ProducttoCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner();
	
	}
	
	@Test(priority = 5)
	public void AddSigmaAldrichProducttoCatalogAsLabManager5() throws Exception{
		Add_SigmaAldrich_ProducttoCatalog Av = new Add_SigmaAldrich_ProducttoCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager();
	
	}
	
	@Test(priority = 6)
	public void AddSigmaAldrichProducttoCatalogAsLabMember6() throws Exception{
		Add_SigmaAldrich_ProducttoCatalog Av = new Add_SigmaAldrich_ProducttoCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember();
	}
	
	@Test(priority = 7)
	public void DeleteNonSigmaAldrichProductfromCatalogAsLabOwner7() throws Exception{
		Delete_NonSigmaAldrichProduct_FromLabCatalog Av = new Delete_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabOwner_and_DeleteProduct();
	
	}
	
	@Test(priority = 8)
	public void DeleteNonSigmaAldrichProductfromCatalogAsLabManager8() throws Exception{
		Delete_NonSigmaAldrichProduct_FromLabCatalog Av = new Delete_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabManager_and_DeleteProduct();
	
	}
	
	@Test(priority = 9)
	public void DeleteNonSigmaAldrichProductfromCatalogAsLabMember9() throws Exception{
		Delete_NonSigmaAldrichProduct_FromLabCatalog Av = new Delete_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabMember_and_DeleteProduct();
	}

	@Test(priority = 10)
	public void DeleteSigmaAldrichProductfromCatalogAsLabOwner10() throws Exception{
		Delete_SigmaAldrichProduct_FromLabCatalog Av = new Delete_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_DeleteProduct();
	
	}

	@Test(priority = 11)
	public void DeleteSigmaAldrichProductfromCatalogAsLabManager11() throws Exception{
		Delete_SigmaAldrichProduct_FromLabCatalog Av = new Delete_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_DeleteProduct();
	
	}

	@Test(priority = 12)
	public void DeleteSigmaAldrichProductfromCatalogAsLabMember12() throws Exception{
		Delete_SigmaAldrichProduct_FromLabCatalog Av = new Delete_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_DeleteProduct();
	}

	@Test(priority = 13)
	public void RequestNonSigmaAldrichProductfromCatalogAsLabOwner13() throws Exception{
		Request_NonSigmaAldrichProduct_FromLabCatalog Av = new Request_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct();
	
	}
	
	@Test(priority = 14)
	public void RequestNonSigmaAldrichProductfromCatalogAsLabManager14() throws Exception{
		Request_NonSigmaAldrichProduct_FromLabCatalog Av = new Request_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct();
	
	}
	
	@Test(priority = 15)
	public void RequestNonSigmaAldrichProductfromCatalogAsLabMember15() throws Exception{
		Request_NonSigmaAldrichProduct_FromLabCatalog Av = new Request_NonSigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_NonSigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct();
	}
	
	@Test(priority = 16)
	public void RequestSigmaAldrichProductfromCatalogAsLabOwner16() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct();
	
	}

	@Test(priority = 17)
	public void RequestSigmaAldrichProductfromCatalogAsLabManager17() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct();
	
	}
	
	@Test(priority = 18)
	public void RequestSigmaAldrichProductfromCatalogAsLabMember18() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct();
	}

	@Test(priority = 19)
	public void EditNonSigmaAldrichProductfromCatalogAsLabOwner19() throws Exception{
		Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager Av = new Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Owner();
	
	}
	
	@Test(priority = 20)
	public void EditNonSigmaAldrichProductfromCatalogAsLabManager20() throws Exception{
		Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager Av = new Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Manager();
	
	}
	
	@Test(priority = 21)
	public void EditNonSigmaAldrichProductfromCatalogAsLabMember21() throws Exception{
		Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager Av = new Editing_NonSigmaAldrichProduct_From_LabCatalog_as_LabOwner_Member_Manager();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Member();
	}
	
	@Test(priority = 22)
	public void EditProductCreatedbyLabOwnerEditedbyLabOwner22() throws Exception{
		Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action Av = new Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Owner();
	}
	
	@Test(priority = 23)
	public void EditProductCreatedbyLabOwnerEditedbyLabManager23() throws Exception{
		Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action Av = new Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Manager();
	}
	
	@Test(priority = 24)
	public void EditProductCreatedbyLabOwnerEditedbyLabMember24() throws Exception{
		Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action Av = new Enable_Editing_Of_The_Lab_Catalog_By_The_Lab_Member_Action();
		Av.Edit_Lab_Catalog_Product_Created_By_Lab_Member();
	}
	
	@Test(priority = 25)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct25() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct();
	}
	
	@Test(priority = 26)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct26() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct();
	}
	
	@Test(priority = 27)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct27() throws Exception{
		Request_SigmaAldrichProduct_FromLabCatalog Av = new Request_SigmaAldrichProduct_FromLabCatalog();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct();
	}
	
	@Test(priority = 28)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct_FromProductDetailsPage28() throws Exception{
		Request_SigmaAldrichProduct_FromProductDetailsPage Av = new Request_SigmaAldrichProduct_FromProductDetailsPage();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabOwner_and_RequestProduct_FromProductDetailsPage();
	}
	/*
	@Test(priority = 29)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct_FromProductDetailsPage29() throws Exception{
		Request_SigmaAldrichProduct_FromProductDetailsPage Av = new Request_SigmaAldrichProduct_FromProductDetailsPage();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabManager_and_RequestProduct_FromProductDetailsPage();
	}
	
	@Test(priority = 30)
	public void Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct_FromProductDetailsPage30() throws Exception{
		Request_SigmaAldrichProduct_FromProductDetailsPage Av = new Request_SigmaAldrichProduct_FromProductDetailsPage();
		Av.Add_New_SigmaAldrich_Product_to_Catalog_as_LabMember_and_RequestProduct_FromProductDetailsPage();
	}
	*/
	
	/* // Permently not used code
	@Test(priority = 25)
	public void verifyingDefault_ApproverandOrderer_RoleasLabManager25() throws Exception{
		LabManagerDefaultApproverProcurer_Action Av = new LabManagerDefaultApproverProcurer_Action();
		Av.verifying_Default_Approver_And_Orderer_Role_As_Lab_Manager();
	}
	
	@Test(priority = 26)
	public void VerifyingLabManagerDefaultApproverandOrdererStatusWhenUserChangesRolefrom_LabManagertoLabMember26() throws Exception{
		LabManagerDefaultApproverProcurer_Action Av = new LabManagerDefaultApproverProcurer_Action();
		Av.Verifying_Lab_Manager_Default_Approver_And_Orderer_Status_When_User_Changes_Role_From_Lab_Manager_To_Lab_Member();
	}
	
	@Test(priority = 27)
	public void VerifyingLabManagerDefaultApproverandOrdererStatusWhenUserChangesRolefrom_LabMembertoLabManager27() throws Exception{
		LabManagerDefaultApproverProcurer_Action Av = new LabManagerDefaultApproverProcurer_Action();
		Av.Verifying_Lab_Manager_Default_Approver_And_Orderer_Status_When_User_Changes_Role_From_Lab_Member_To_Lab_Manager();
	}
	
	@Test(priority = 28)
	public void VerifyingDashboardActivityfor_QuantityChange_Positive28() throws Exception{
		DashboardActivity_EditQuantity_Positive Av = new DashboardActivity_EditQuantity_Positive();
		Av.VerifyDashboardActivityfor_EditQuantity_Positive();
	}
	
	@Test(priority = 29)
	public void VerifyingDashboardActivityfor_QuantityChange_Negative29() throws Exception{
		DashboardActivity_EditQuantity_Negative Av = new DashboardActivity_EditQuantity_Negative();
		Av.VerifyDashboardActivityfor_EditQuantity_Negative();
	}
	
	@Test(priority = 30)
	public void VerifyingDashboardActivityfor_RequestNonSigmaAldrichProduct30() throws Exception{
		DashboardActivity_Request_NoNSigmaAldrichProduct Av = new DashboardActivity_Request_NoNSigmaAldrichProduct();
		Av.VerifyDashboardActivityfor_NoNSigmaAldrichProductRequest();
	}
	
	@Test(priority = 31)
	public void VerifyingDashboardActivityfor_RequestSigmaAldrichProduct31() throws Exception{
		DashboardActivity_Request_SigmaAldrichProduct Av = new DashboardActivity_Request_SigmaAldrichProduct();
		Av.VerifyDashboardActivityfor_SigmaAldrichProductRequest();
	}
	*/
}
