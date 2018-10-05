package testScript;

import org.testng.annotations.Test;

import testBase.TestBase;
import userManagement.ChangePasswordErrorAndValidationMessage_Action;
import userManagement.ContactUsErrorAndValidationMessage_Action;
import userManagement.DeletedUserResetPassword_Action;
import userManagement.EditLabValidationMessage_Action;
import userManagement.EditNameValidationMessage_Action;
import userManagement.ForgotPasswordErrorAndValidationMessage_Action;
import userManagement.InvalidLoginAndRequiredFieldValidation_Action;
import userManagement.LabMembersBatchUploadInvalidFile_Action;
import userManagement.LabMembersErrorAndValidationMessage_Action;
import userManagement.PaymentSettingsValidationMessage_Action;
import userManagement.PendingUserResetPassword_Action;
import userManagement.RegisteredUserDeletedInAllLabs_Action;
import userManagement.RegisteredUserInactiveInAllLabs_Action;
import userManagement.UnregisteredUserDeletedInAllLabs_Action;
import userManagement.UnregisteredUserPendingInAllLabs_Action;
import userManagement.UnregisteredUserResetPassword_Action;
import userManagement.UnregisteredUserTriesToResetHisPassword_Action;
import userManagement.UserAlreadyInvitedRequestsToRegister_Action;
import userManagement.UserPendingInOneLabTriesToResetHisPassword_Action;

public class PlatformErrorAndValidation extends TestBase{
	
	/*@Test(priority = 1)
	public void InvalidLoginAndRequiredFieldValidation() throws Exception{
		InvalidLoginAndRequiredFieldValidation_Action ilarfva = new InvalidLoginAndRequiredFieldValidation_Action();
		ilarfva.InvalidLoginAndRequiredFieldValidation();
	}
	
	@Test(priority = 2)
	public void ForgotPasswordErrorAndValidationMessage() throws Exception{
		ForgotPasswordErrorAndValidationMessage_Action fpeavma = new ForgotPasswordErrorAndValidationMessage_Action();
		fpeavma.ForgotPasswordErrorAndValidationMessage();	
	}
	
	@Test(priority = 3)
	public void ChangePasswordErrorAndValidationMessage() throws Exception{
		ChangePasswordErrorAndValidationMessage_Action cpeavma = new ChangePasswordErrorAndValidationMessage_Action();
		cpeavma.ChangePasswordErrorAndValidationMessage();	
	}
	
	@Test(priority = 4)
	public void LabMembersErrorAndValidationMessage() throws Exception{
		LabMembersErrorAndValidationMessage_Action lmeavma = new LabMembersErrorAndValidationMessage_Action();
		lmeavma.LabMembersErrorAndValidationMessage();	
	}
	
	@Test(priority = 5)
	public void LabMembersBatchUploadInvalidFile() throws Exception{
		LabMembersBatchUploadInvalidFile_Action lmbuif = new LabMembersBatchUploadInvalidFile_Action();
		lmbuif.LabMembersBatchUploadInvalidFile();	
	}
	
	@Test(priority = 6)
	public void ContactUsErrorAndValidationMessage() throws Exception{
		ContactUsErrorAndValidationMessage_Action cueavma = new ContactUsErrorAndValidationMessage_Action();
		cueavma.ContactUsErrorAndValidationMessage();
	}
	
	@Test(priority = 7)
	public void EditNameValidationMessage() throws Exception{
		EditNameValidationMessage_Action envma = new EditNameValidationMessage_Action();
		envma.EditNameValidationMessage();
	}
	
	@Test(priority = 8)
	public void EditLabValidationMessage() throws Exception{
		EditLabValidationMessage_Action evma = new EditLabValidationMessage_Action();
		evma.EditLabValidationMessage();
	}
	
	@Test(priority = 9)
	public void RegisteredUserInactiveInAllLabs() throws Exception{
		RegisteredUserInactiveInAllLabs_Action ruiiala = new RegisteredUserInactiveInAllLabs_Action();
		ruiiala.RegisteredUserInactiveInAllLabs();
	}
	
	@Test(priority = 10)
	public void RegisteredUserDeletedInAllLabs() throws Exception{
		RegisteredUserDeletedInAllLabs_Action rudiala = new RegisteredUserDeletedInAllLabs_Action();
		rudiala.RegisteredUserDeletedInAllLabs();
	}
	
	@Test(priority = 11)
	public void UnregisteredUserPendingInAllLabs() throws Exception{
		UnregisteredUserPendingInAllLabs_Action uupila = new UnregisteredUserPendingInAllLabs_Action();
		uupila.UnregisteredUserPendingInAllLabs();
	}
	
	@Test(priority = 12)
	public void UnregisteredUserDeletedInAllLabs() throws Exception{
		UnregisteredUserDeletedInAllLabs_Action uudiala = new UnregisteredUserDeletedInAllLabs_Action();
		uudiala.UnregisteredUserPendingInAllLabs();
	}*/
	
	//@Test(priority = 13)
	//public void UserAlreadyInvitedRequestsToRegister() throws Exception{
	//	UserAlreadyInvitedRequestsToRegister_Action uairtra = new UserAlreadyInvitedRequestsToRegister_Action();
	//	uairtra.UserAlreadyInvitedRequestsToRegister();
	//}
	
	@Test(priority = 14)
	public void PendingUserResetPassword() throws Exception{
		PendingUserResetPassword_Action purpa = new PendingUserResetPassword_Action();
		purpa.PendingUserResetPassword();
	}
	
	/*@Test(priority = 15)
	public void UnregisteredUserResetPassword() throws Exception{
		UnregisteredUserResetPassword_Action uurpa = new UnregisteredUserResetPassword_Action();
		uurpa.UnregisteredUserResetPassword();
	}
	
	@Test(priority = 16)
	public void DeletedUserResetPassword() throws Exception{
		DeletedUserResetPassword_Action durpa = new DeletedUserResetPassword_Action();
		durpa.DeletedUserResetPassword();
	}*/
	

}
