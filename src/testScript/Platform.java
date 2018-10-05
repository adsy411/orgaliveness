package testScript;

import org.testng.annotations.Test;

import testBase.TestBase;
import userManagement.ChangePassword_Action;
import userManagement.ContactUs_Action;
import userManagement.DeleteMemberAndInviteFromOwner_Action;
import userManagement.DeletedUserResetPassword_Action;
import userManagement.EditProfile_Action;
import userManagement.EmailNotificationSettings_Action;
import userManagement.ExistingMemberInvitation_Action;
import userManagement.ForgotPassword_Action;
import userManagement.InviteLabManagerCheckJoinLabExists_Action;
import userManagement.LabManagerInvitationAsLabOwner_Action;
import userManagement.LabManagerInvitation_Action;
import userManagement.LabManagerOnboardingPage_Action;
import userManagement.LabMemberInvitationFromManager_Action;
import userManagement.LabMemberInvitationFromOwner_Action;
import userManagement.LabMembersBatchUploadFromManager_Action;
import userManagement.LabMembersBatchUpload_Action;
import userManagement.LabOwnerOnboardingPage_Action;
import userManagement.LabOwnerRegistrationAndOnboarding_Action;
import userManagement.LabSettings_Action;
import userManagement.PaymentSettings_Action;
import userManagement.RegisteredUserDeletedInAllLabs_Action;
import userManagement.RegisteredUserInactiveInAllLabs_Action;
import userManagement.SupportSite_Action;
import userManagement.SwitchToDiffLab_Action;
import userManagement.UserProfileSameAcrossLabs_Action;
import userManagement.VerifyHomeDashboardLinks_Action;
import userManagement.VerifyMaterialTimezone_Action;
import userManagement.VerifyProjectTimezone_Action;

public class Platform extends TestBase{
	
	@Test(priority = 1)
	public void ChangePassword() throws Exception{
		ChangePassword_Action cpa = new ChangePassword_Action();
		cpa.ChangePassword();	
	}
	
	/*@Test(priority = 2)
	public void ContactUs() throws Exception{
		ContactUs_Action cua = new ContactUs_Action();
		cua.ContactUs();	
	}
	
	@Test(priority = 3)
	public void EditProfile() throws Exception{
		EditProfile_Action epa = new EditProfile_Action();
		epa.EditProfile();
	}

	@Test(priority = 4)
	public void ForgotPassword() throws Exception{
		ForgotPassword_Action fpa = new ForgotPassword_Action();
		fpa.ForgotPassword();
	}
	
	@Test(priority = 5)
	public void LabSettings() throws Exception{
		LabSettings_Action lsa = new LabSettings_Action();
		lsa.LabSettings();
	}
	
	@Test(priority = 6)
	public void ExistingMemberInvitation() throws Exception{
		ExistingMemberInvitation_Action emia = new ExistingMemberInvitation_Action();
		emia.ExistingMemberInvitation();
	}
	
	@Test(priority = 7)
	public void LabManagerInvitation() throws Exception{
		LabManagerInvitation_Action lmia = new LabManagerInvitation_Action();
		lmia.LabManagerInvitation();
	}
	
	@Test(priority = 8)
	public void LabMemberInvitationFromOwner() throws Exception{
		LabMemberInvitationFromOwner_Action lmifoa = new LabMemberInvitationFromOwner_Action();
		lmifoa.LabMemberInvitationFromOwner();
	}
	
	@Test(priority = 9)
	public void LabMemberInvitationFromManager() throws Exception{
		LabMemberInvitationFromManager_Action lmifma = new LabMemberInvitationFromManager_Action();
		lmifma.LabMemberInvitationFromManager();
	}
	
	@Test(priority = 10)
	public void DeleteMemberAndInviteFromOwner() throws Exception{
		DeleteMemberAndInviteFromOwner_Action dmaifoa = new DeleteMemberAndInviteFromOwner_Action();
		dmaifoa.DeleteMemberAndInviteFromOwner();
	}
	
	@Test(priority = 11)
	public void SwitchToDiffLab() throws Exception{
		SwitchToDiffLab_Action stda = new SwitchToDiffLab_Action();
		stda.SwitchToDiffLab();
	}
	
	@Test(priority = 12)
	public void LabMembersBatchUpload() throws Exception{
		LabMembersBatchUpload_Action lmbua = new LabMembersBatchUpload_Action();
		lmbua.LabMembersBatchUpload();
	}
	
	@Test(priority = 13)
	public void InviteLabManagerCheckJoinLabExists() throws Exception{
		InviteLabManagerCheckJoinLabExists_Action ilmcjlea = new InviteLabManagerCheckJoinLabExists_Action();
		ilmcjlea.InviteLabManagerCheckJoinLabExists();
	}
	
	@Test(priority = 14)
	public void LabOwnerRegistrationAndOnboarding() throws Exception{
		LabOwnerRegistrationAndOnboarding_Action loraoa = new LabOwnerRegistrationAndOnboarding_Action();
		loraoa.LabOwnerRegistrationAndOnboarding();
	}
	
	@Test(priority = 15)
	public void LabManagerOnboardingPage() throws Exception{
		LabManagerOnboardingPage_Action lmopa = new LabManagerOnboardingPage_Action();
		lmopa.LabManagerOnboardingPage();
	}
	
	@Test(priority = 16)
	public void LabMembersBatchUploadFromManager() throws Exception{
		LabMembersBatchUploadFromManager_Action lmbufm = new LabMembersBatchUploadFromManager_Action();
		lmbufm.LabMembersBatchUploadFromManager();
	}
	
	@Test(priority = 17)
	public void UserProfileSameAcrossLabs() throws Exception{
		UserProfileSameAcrossLabs_Action upsala = new UserProfileSameAcrossLabs_Action();
		upsala.UserProfileSameAcrossLabs();
	}
	
	@Test(priority = 18)
	public void LabManagerInvitationAsLabOwner() throws Exception{
		LabManagerInvitationAsLabOwner_Action lialoa = new LabManagerInvitationAsLabOwner_Action();
		lialoa.LabManagerInvitationAsLabOwner();
	}
	
	@Test(priority = 19)
	public void VerifyMaterialTimezone() throws Exception{
		VerifyMaterialTimezone_Action vmta = new VerifyMaterialTimezone_Action();
		vmta.VerifyMaterialTimezone();
	}
	
	@Test(priority = 20)
	public void VerifyProjectTimezone() throws Exception{
		VerifyProjectTimezone_Action vpta = new VerifyProjectTimezone_Action();
		vpta.VerifyProjectTimezone();
	}*/
	
	//@Test(priority = 4)
		//public void EmailNotificationSettings() throws Exception{
		//	EmailNotificationSettings_Action ensa = new EmailNotificationSettings_Action();
		//	ensa.EmailNotificationSettings();
		//}
	
	//@Test(priority = 6)
		//public void VerifyHomeDashboardLinks() throws Exception{
		//	VerifyHomeDashboardLinks_Action vhdla = new VerifyHomeDashboardLinks_Action();
		//	vhdla.VerifyHomeDashboardLinks();
		//}
	
	
	
}
