package testScript;

import org.testng.annotations.Test;

import testBase.TestBase;
import userManagement.ForgotPasswordErrorAndValidationMessage_Action;
import userManagement.InvalidLoginAndRequiredFieldValidation_Action;
import userManagement.InviteNewUserEmailVerification_Action;
import userManagement.ResetPasswordEmailVerification_Action;
import userManagement.UserActivatedEmailVerification_Action;
import userManagement.UserProfileUpdateEmailVerification_Action;
import userManagement.UserRoleChangeEmailVerification_Action;

public class PlatformEmailVerification extends TestBase{
	
	@Test(priority = 1)
	public void ResetPasswordEmailVerification() throws Exception{
		ResetPasswordEmailVerification_Action rpeva = new ResetPasswordEmailVerification_Action();
		rpeva.ResetPasswordEmailVerification();
	}
	
	@Test(priority = 2)
	public void InviteNewUserEmailVerification() throws Exception{
		InviteNewUserEmailVerification_Action inueva = new InviteNewUserEmailVerification_Action();
		inueva.InviteNewUserEmailVerification();
	}
	
	@Test(priority = 3)
	public void UserRoleChangeEmailVerification() throws Exception{
		UserRoleChangeEmailVerification_Action urceva = new UserRoleChangeEmailVerification_Action();
		urceva.UserRoleChangeEmailVerification();
	}
	
	@Test(priority = 4)
	public void UserProfileUpdateEmailVerification() throws Exception{
		UserProfileUpdateEmailVerification_Action upueva = new UserProfileUpdateEmailVerification_Action();
		upueva.UserProfileUpdateEmailVerification();
	}
	
	@Test(priority = 5)
	public void UserActivatedEmailVerification() throws Exception{
		UserActivatedEmailVerification_Action uaeva = new UserActivatedEmailVerification_Action();
		uaeva.UserActivatedEmailVerification();
	}
	
	
	
	
	
	

}
