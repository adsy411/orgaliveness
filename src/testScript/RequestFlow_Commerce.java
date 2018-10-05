package testScript;

import org.testng.annotations.Test;

import labCatalog.AddNonSigmaAldrichProducttoCatalog;
import labCatalog.AddSigmaAldrichProducttoCatalog_Old;
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
import request.AddAlltoCart_AllProductsin_ApprovedTab;
import request.AddAlltoCart_AllProductsin_RequestedTab;
import request.AddNoteto_AllProductsin_ApprovedTab;
import request.AddNoteto_AllProductsin_OrderedTab;
import request.AddNoteto_AllProductsin_ReceivedTab;
import request.AddNoteto_AllProductsin_RequestedTab;
import request.AddNotetoa_SingleProductin_ApprovedTab;
import request.AddNotetoa_SingleProductin_OrderedTab;
import request.AddNotetoa_SingleProductin_ReceivedTab;
import request.AddNotetoa_SingleProductin_RequestedTab;
import request.EditPriceandCountofa_SingleProductin_ApprovedTab;
import request.EditPriceandCountofa_SingleProductin_OrderedTab;
import request.EditPriceandCountofa_SingleProductin_RequestedTab;
import request.Export_AllProductsin_ApprovedTab;
import request.Export_AllProductsin_OrderedTab;
import request.Export_AllProductsin_ReceivedTab;
import request.Export_AllProductsin_RequestedTab;
import request.MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabManager;
import request.MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabMemberWithPermission;
import request.MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabOwner;
import request.MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabManager;
import request.MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabMemberWithPermission;
import request.MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabOwner;
import request.QuickAdd_Multiple_SigmaAldrichProducttoRequestTab_asLabOwner;
import request.QuickAdd_SigmaAldrichProducttoRequestTab_asLabManager;
import request.QuickAdd_SigmaAldrichProducttoRequestTab_asLabMember;
import request.QuickAdd_SigmaAldrichProducttoRequestTab_asLabOwner;
import request.Request_ReverseFlow_Ordered_Approved_and_Reject_asLabManager;
import request.Request_ReverseFlow_Ordered_Approved_and_Reject_asLabMemberWithPermission;
import request.Request_ReverseFlow_Ordered_Approved_and_Reject_asLabOwner;
import request.RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabManager;
import request.RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabMemberWithPermission;
import request.RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabOwner;
import stockroomUserManagement.InviteStkroomMembersThroughBatchUpload_Action;
import stockroomUserManagement.InviteStockroomLeadAndUser_Action;
import stockroomUserManagement.LoginAsStockroomAdmin_Action;
import stockroomUserManagement.StkRoomAdminMemberListFunctionality_Action;
import stockroomUserManagement.VerifyContactUs_Action;
import stockroomUserManagement.VerifyStockroomName_Action;
import testBase.TestBase;

public class RequestFlow_Commerce extends TestBase{
	
	@Test(priority = 1)
	public void QuickOrderRequestNewSigmaAldrichProductas_LabOwner1() throws Exception{
		QuickAdd_SigmaAldrichProducttoRequestTab_asLabOwner Av = new QuickAdd_SigmaAldrichProducttoRequestTab_asLabOwner();
		Av.QuickAdd_SigmaAldrichProducttoRequestTab();
	
	}
	
	@Test(priority = 2)
	public void QuickOrderRequestNewSigmaAldrichProductas_LabManager2() throws Exception{
		QuickAdd_SigmaAldrichProducttoRequestTab_asLabManager Av = new QuickAdd_SigmaAldrichProducttoRequestTab_asLabManager();
		Av.QuickAdd_SigmaAldrichProducttoRequestTab();
	
	}
	
	@Test(priority = 3)
	public void QuickOrderRequestNewSigmaAldrichProductas_LabMember3() throws Exception{
		QuickAdd_SigmaAldrichProducttoRequestTab_asLabMember Av = new QuickAdd_SigmaAldrichProducttoRequestTab_asLabMember();
		Av.QuickAdd_SigmaAldrichProducttoRequestTab();
	
	}
	
	@Test(priority = 4)
	public void RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabOwner4() throws Exception{
		RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabOwner Av = new RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabOwner();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 5)
	public void RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabManager5() throws Exception{
		RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabManager Av = new RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabManager();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 6)
	public void RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabMemberwithPermission6() throws Exception{
		RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabMemberWithPermission Av = new RequestedProduct_Approved_Ordered_and_Received_CompleteFlow_asLabMemberWithPermission();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 7)
	public void Request_ReverseFlow_Ordered_Approved_and_Reject_asLabOwner7() throws Exception{
		Request_ReverseFlow_Ordered_Approved_and_Reject_asLabOwner Av = new Request_ReverseFlow_Ordered_Approved_and_Reject_asLabOwner();
		Av.CompleteReverseRequestFlow();
	}
	
	@Test(priority = 8)
	public void Request_ReverseFlow_Ordered_Approved_and_Reject_asLabManager8() throws Exception{
		Request_ReverseFlow_Ordered_Approved_and_Reject_asLabManager Av = new Request_ReverseFlow_Ordered_Approved_and_Reject_asLabManager();
		Av.CompleteReverseRequestFlow();
	}
	
	@Test(priority = 9)
	public void Request_ReverseFlow_Ordered_Approved_and_Reject_asLabMemberwithPermission9() throws Exception{
		Request_ReverseFlow_Ordered_Approved_and_Reject_asLabMemberWithPermission Av = new Request_ReverseFlow_Ordered_Approved_and_Reject_asLabMemberWithPermission();
		Av.CompleteReverseRequestFlow();
	}
	
	@Test(priority = 10)
	public void AddNotetoa_SingleProductin_RequestedTab10() throws Exception{
		AddNotetoa_SingleProductin_RequestedTab Av = new AddNotetoa_SingleProductin_RequestedTab();
		Av.AddNotesinRequestTab();
	}
	
	@Test(priority = 11)
	public void AddNotetoa_SingleProductin_ApprovedTab11() throws Exception{
		AddNotetoa_SingleProductin_ApprovedTab Av = new AddNotetoa_SingleProductin_ApprovedTab();
		Av.AddNotesinApprovedTab();
	}
	
	@Test(priority = 12)
	public void AddNotetoa_SingleProductin_OrderedTab12() throws Exception{
		AddNotetoa_SingleProductin_OrderedTab Av = new AddNotetoa_SingleProductin_OrderedTab();
		Av.AddNotesinOrderedTab();
	}
	
	@Test(priority = 13)
	public void QuickOrder_Multiple_SigmaAldrichProducts13() throws Exception{
		QuickAdd_Multiple_SigmaAldrichProducttoRequestTab_asLabOwner Av = new QuickAdd_Multiple_SigmaAldrichProducttoRequestTab_asLabOwner();
		Av.QuickAdd_SigmaAldrichProducttoRequestTab();
	}
	
	@Test(priority = 14)
	public void AddNotetoa_SingleProductin_ReceivedTab14() throws Exception{
		AddNotetoa_SingleProductin_ReceivedTab Av = new AddNotetoa_SingleProductin_ReceivedTab();
		Av.AddNotesinReceivedTab();
	}
	
	@Test(priority = 15)
	public void AddNoteto_AllProductsin_RequestTab15() throws Exception{
		AddNoteto_AllProductsin_RequestedTab Av = new AddNoteto_AllProductsin_RequestedTab();
		Av.AddNotesinRequestTab();
	}
	
	@Test(priority = 16)
	public void AddNoteto_AllProductsin_ApproveTab16() throws Exception{
		AddNoteto_AllProductsin_ApprovedTab Av = new AddNoteto_AllProductsin_ApprovedTab();
		Av.AddNotesinApprovedTab();
	}
	
	@Test(priority = 17)
	public void AddNoteto_AllProductsin_OrderedTab17() throws Exception{
		AddNoteto_AllProductsin_OrderedTab Av = new AddNoteto_AllProductsin_OrderedTab();
		Av.AddNotesinOrderedTab();
	}
	
	@Test(priority = 18)
	public void AddNoteto_AllProductsin_ReceivedTab18() throws Exception{
		AddNoteto_AllProductsin_ReceivedTab Av = new AddNoteto_AllProductsin_ReceivedTab();
		Av.AddNotesinReceivedTab();
	}
	
	@Test(priority = 19)
	public void MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabOwner19() throws Exception{
		MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabOwner Av = new MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabOwner();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 20)
	public void MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabManager20() throws Exception{
		MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabManager Av = new MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabManager();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 21)
	public void MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabMemberWithPermission21() throws Exception{
		MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabMemberWithPermission Av = new MultipleProducts_Requested_ApproveAll_OrderAll_and_ReceiveAll_CompleteFlow_asLabMemberWithPermission();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 22)
	public void MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabOwner22() throws Exception{
		MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabOwner Av = new MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabOwner();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 23)
	public void MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabManager23() throws Exception{
		MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabManager Av = new MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabManager();
		Av.CompleteRequestFlow();
	}

	@Test(priority = 24)
	public void MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabMemberWithPermission24() throws Exception{
		MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabMemberWithPermission Av = new MultipleProducts_RequestReverseFlow_MoveAlltoApproved_MoveAltoReceived_RejectAll_asLabMemberWithPermission();
		Av.CompleteRequestFlow();
	}
	
	@Test(priority = 25)
	public void Export_AllProductsin_RequestedTab25() throws Exception{
		Export_AllProductsin_RequestedTab Av = new Export_AllProductsin_RequestedTab();
		Av.ExportAllProductsinRequestTab();
	}
	
	@Test(priority = 26)
	public void Export_AllProductsin_ApprovedTab26() throws Exception{
		Export_AllProductsin_ApprovedTab Av = new Export_AllProductsin_ApprovedTab();
		Av.ExportAllProductsinApprovedTab();
	}

	@Test(priority = 27)
	public void Export_AllProductsin_OrderedTab27() throws Exception{
		Export_AllProductsin_OrderedTab Av = new Export_AllProductsin_OrderedTab();
		Av.ExportAllProductsinOrderedTab();
	}
	
	@Test(priority = 28)
	public void Export_AllProductsin_ReceivedTab28() throws Exception{
		Export_AllProductsin_ReceivedTab Av = new Export_AllProductsin_ReceivedTab();
		Av.ExportAllProductsinReceivedTab();
	}
	
	@Test(priority = 29)
	public void EditPriceandCountofa_SingleProductin_RequestedTab29() throws Exception{
		EditPriceandCountofa_SingleProductin_RequestedTab Av = new EditPriceandCountofa_SingleProductin_RequestedTab();
		Av.EditCountandPriceinRequestTab();
	}
	
	@Test(priority = 30)
	public void EditPriceandCountofa_SingleProductin_ApprovedTab30() throws Exception{
		EditPriceandCountofa_SingleProductin_ApprovedTab Av = new EditPriceandCountofa_SingleProductin_ApprovedTab();
		Av.EditCountandPriceinApprovedTab();
	}
	
	@Test(priority = 31)
	public void EditPriceandCountofa_SingleProductin_OrderedTab31() throws Exception{
		EditPriceandCountofa_SingleProductin_OrderedTab Av = new EditPriceandCountofa_SingleProductin_OrderedTab();
		Av.EditCountandPriceinOrderedTab();
	}
	
	
	@Test(priority = 32)
	public void AddAlltoCart_AllProductsin_RequestedTab32() throws Exception{
		AddAlltoCart_AllProductsin_RequestedTab Av = new AddAlltoCart_AllProductsin_RequestedTab();
		Av.AddAllProductstoCartinRequestTab();
	}
	
	@Test(priority = 33)
	public void AddAlltoCart_AllProductsin_ApprovedTab33() throws Exception{
		AddAlltoCart_AllProductsin_ApprovedTab Av = new AddAlltoCart_AllProductsin_ApprovedTab();
		Av.AddAllProducttoCartinApprovedTab();
	}
	
}
