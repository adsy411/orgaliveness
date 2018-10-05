package testScript;

import org.testng.annotations.Test;

import stockroomUserManagement.InviteStkroomMembersThroughBatchUpload_Action;
import stockroomUserManagement.InviteStockroomLeadAndUser_Action;
import stockroomUserManagement.LoginAsStockroomAdmin_Action;
import stockroomUserManagement.StkRoomAdminMemberListFunctionality_Action;
import stockroomUserManagement.VerifyContactUs_Action;
import stockroomUserManagement.VerifyStockroomName_Action;
import testBase.TestBase;

public class StkroomUserManagement extends TestBase{
	
	@Test(priority = 1)
	public void LoginAsStockroomAdmin() throws Exception{
		LoginAsStockroomAdmin_Action lasaa = new LoginAsStockroomAdmin_Action();
		lasaa.LoginAsStockroomAdmin();
	}
	
	@Test(priority = 2)
	public void StkRoomAdminMemberListFunctionality() throws Exception{
		StkRoomAdminMemberListFunctionality_Action sramlfa = new StkRoomAdminMemberListFunctionality_Action();
		sramlfa.StkRoomAdminMemberListFunctionality();
	}
	
	@Test(priority = 3)
	public void InviteStkroomMembersThroughBatchUpload() throws Exception{
		InviteStkroomMembersThroughBatchUpload_Action ismtbua = new InviteStkroomMembersThroughBatchUpload_Action();
		ismtbua.InviteStkroomMembersThroughBatchUpload();
	}
	
	@Test(priority = 4)
	public void InviteStockroomLeadAndUser() throws Exception{
		InviteStockroomLeadAndUser_Action islaua = new InviteStockroomLeadAndUser_Action();
		islaua.InviteStockroomLeadAndUser();
	}
	
	@Test(priority = 5)
	public void VerifyStockroomName() throws Exception{
		VerifyStockroomName_Action vsna = new VerifyStockroomName_Action();
		vsna.VerifyStockroomName();
	}
	
	@Test(priority = 6)
	public void VerifyContactUs() throws Exception{
		VerifyContactUs_Action vcua = new VerifyContactUs_Action();
		vcua.VerifyContactUs();
	}
	
	
}
