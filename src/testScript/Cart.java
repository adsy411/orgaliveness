package testScript;

import org.testng.annotations.Test;

import cart.AddProductToCart_ChangePacksize;
import cart.AddProductToCart_ChangeProductCount;
import cart.AddProductToCart_NavigatetoSigmaSite;
import cart.AddProductToCart_RequestPage;
import cart.CartPage_EditBillingAddress;
import cart.CartPage_EditContactInfo;
import cart.CartPage_EditShippingAddress;
import cart.ChangeProductCountAndDelete_Action;
import cart.Checkoutwith_AmericanExpressCreditCard;
import cart.Checkoutwith_MasterCreditCard;
import cart.Checkoutwith_PurchaseOrder;
import cart.Checkoutwith_VisaCreditCard;
import cart.PurchaseAnOrder_Action;
import cart.RemoveProductFromCartPopup_Action;
import cart.ValidateCardPurchaseOrderNumberField_Action;
import cart.ValidationMessageCountFieldCartPage_Action;
import cart.ValidationMessageCountFieldCartPopup_Action;
import cart.ValidationMessage_CartPage_EditContactInfo;
import cart.ValidationMessage_EditBillingAddress;
import cart.ValidationMessage_EditShippingAddress;
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
import stockroomUserManagement.InviteStkroomMembersThroughBatchUpload_Action;
import stockroomUserManagement.InviteStockroomLeadAndUser_Action;
import stockroomUserManagement.LoginAsStockroomAdmin_Action;
import stockroomUserManagement.StkRoomAdminMemberListFunctionality_Action;
import stockroomUserManagement.VerifyContactUs_Action;
import stockroomUserManagement.VerifyStockroomName_Action;
import testBase.TestBase;

public class Cart extends TestBase{
	
	/*@Test(priority = 1)
	public void SearchandAddProducttoCartfromRequestPage1() throws Exception{
		AddProductToCart_RequestPage Av = new AddProductToCart_RequestPage();
		Av.AddProducttocartfromRequestpage();
	}
	
	@Test(priority = 2)
	public void CartPageEditContactInfo2() throws Exception{
		CartPage_EditContactInfo Av = new CartPage_EditContactInfo();
		Av.EditContactInfo();
	}
	
	@Test(priority = 3)
	public void CartPageEditBillingAddress3() throws Exception{
		CartPage_EditBillingAddress Av = new CartPage_EditBillingAddress();
		Av.EditBillingInfo();
	}
	
	@Test(priority = 4)
	public void CartPageEditShippingAddress4() throws Exception{
		CartPage_EditShippingAddress Av = new CartPage_EditShippingAddress();
		Av.EditShippingInfo();
	}
	
	@Test(priority = 5)
	public void Checkoutwith_AmericanExpressCreditCard5() throws Exception{
		Checkoutwith_AmericanExpressCreditCard Av = new Checkoutwith_AmericanExpressCreditCard();
		Av.CheckoutAmericanExpress();
	}
	
	@Test(priority = 6)
	public void Checkoutwith_VisaCreditCard6() throws Exception{
		Checkoutwith_VisaCreditCard Av = new Checkoutwith_VisaCreditCard();
		Av.CheckoutVisa();
	}
	
	@Test(priority = 7)
	public void Checkoutwith_MasterCreditCard7() throws Exception{
		Checkoutwith_MasterCreditCard Av = new Checkoutwith_MasterCreditCard();
		Av.CheckoutMaster();
	}
	
	@Test(priority = 8)
	public void Checkoutwith_PurchaseOrder8() throws Exception{
		Checkoutwith_PurchaseOrder Av = new Checkoutwith_PurchaseOrder();
		Av.CheckoutPurchaseOrder();
	}
	
	@Test(priority = 9)
	public void AddProductToCart_ChangeProductCount9() throws Exception{
		AddProductToCart_ChangeProductCount Av = new AddProductToCart_ChangeProductCount();
		Av.AddProducttocartchangeQuantity();
	}
	
	@Test(priority = 10)
	public void AddProductToCart_ChangePacksize10() throws Exception{
		AddProductToCart_ChangePacksize Av = new AddProductToCart_ChangePacksize();
		Av.AddProducttocartChangePacksize();
	}
	
	
	@Test(priority = 11)
	public void ChangeProductCountandDelete11() throws Exception{
		ChangeProductCountAndDelete_Action Av = new ChangeProductCountAndDelete_Action();
		Av.ChangeProductCountAndDelete();
	}
	*/
	@Test(priority = 12)
	public void PurchaseAnOrder_Action12() throws Exception{
		PurchaseAnOrder_Action Av = new PurchaseAnOrder_Action();
		Av.PurchaseAnOrder();
	}
	/*
	@Test(priority = 13)
	public void RemoveProductFromCartPopup_Action13() throws Exception{
		RemoveProductFromCartPopup_Action Av = new RemoveProductFromCartPopup_Action();
		Av.RemoveProductFromCartPopup();
	}
	
	@Test(priority = 14)
	public void ValidateCardPurchaseOrderNumberField_Action14() throws Exception{
		ValidateCardPurchaseOrderNumberField_Action Av = new ValidateCardPurchaseOrderNumberField_Action();
		Av.ValidateCardPurchaseOrderNumberField();
	}
	
	@Test(priority = 15)
	public void ValidationMessageCountFieldCartPage_Action15() throws Exception{
		ValidationMessageCountFieldCartPage_Action Av = new ValidationMessageCountFieldCartPage_Action();
		Av.ValidationMessageCountFieldCartPage();
	}
	
	@Test(priority = 16)
	public void ValidationMessageCountFieldCartPopup_Action16() throws Exception{
		ValidationMessageCountFieldCartPopup_Action Av = new ValidationMessageCountFieldCartPopup_Action();
		Av.ValidationMessageCountFieldCartPopup();
	}
	
	
	@Test(priority = 17)
	public void ValidationMessage_CartPage_EditContactInfo17() throws Exception{
		ValidationMessage_CartPage_EditContactInfo Av = new ValidationMessage_CartPage_EditContactInfo();
		Av.ValidationMessage_EditContactInfo();
	}
	
	@Test(priority = 18)
	public void ValidationMessage_EditBillingAddress18() throws Exception{
		ValidationMessage_EditBillingAddress Av = new ValidationMessage_EditBillingAddress();
		Av.ValidationMessage_EditBillingInfo();
	}
	
	@Test(priority = 19)
	public void ValidationMessage_EditShippingAddress19() throws Exception{
		ValidationMessage_EditShippingAddress Av = new ValidationMessage_EditShippingAddress();
		Av.ValidationMessage_EditShippingInfo();
	}
	
	@Test(priority = 20)
	public void AddProductToCart_NavigatetoSigmaSite20() throws Exception{
		AddProductToCart_NavigatetoSigmaSite Av = new AddProductToCart_NavigatetoSigmaSite();
		Av.AddProducttocartNavigatetoSigmaSite();
	}*/
	
}
