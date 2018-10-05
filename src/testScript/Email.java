package testScript;

import org.testng.annotations.Test;

import email.ProductRequestedByManager_Action;
import email.ProductRequestedByMember_Action;
import email.ProductRequestedByOwner_Action;
import email.ReqEditApproveTabByManager_Action;
import email.ReqEditApproveTabByMember_Action;
import email.ReqEditApproveTabByOwner_Action;
import email.ReqEditCountPriceNoteByManager_Action;
import email.ReqEditCountPriceNoteByMember_Action;
import email.ReqEditCountPriceNoteByOwner_Action;
import email.ReqEditOrderedTabByManager_Action;
import email.ReqEditOrderedTabByMember_Action;
import email.ReqEditOrderedTabByOwner_Action;
import email.ReqMovApprovedToOrderedByManager_Action;
import email.ReqMovApprovedToOrderedByMember_Action;
import email.ReqMovApprovedToOrderedByOwner_Action;
import email.ReqMovApprovedToRequestedByManager_Action;
import email.ReqMovApprovedToRequestedByMember_Action;
import email.ReqMovApprovedToRequestedByOwner_Action;
import email.ReqMovOrderedToApprovedByManager_Action;
import email.ReqMovOrderedToApprovedByMember_Action;
import email.ReqMovOrderedToApprovedByOwner_Action;
import email.ReqMovOrderedToInventoryByManager_Action;
import email.ReqMovOrderedToInventoryByMember_Action;
import email.ReqMovOrderedToInventoryByOwner_Action;
import email.RequestApprovedByManager_Action;
import email.RequestApprovedByMember_Action;
import email.RequestApprovedByOwner_Action;
import email.RequestRejectedByManager_Action;
import email.RequestRejectedByMember_Action;
import email.RequestRejectedByOwner_Action;
import testBase.TestBase;

public class Email extends TestBase {
	
	/*@Test(priority = 1)
	public void RequestRejectedByOwner() throws Exception{
		RequestRejectedByOwner_Action rrbo = new RequestRejectedByOwner_Action();
		rrbo.RequestRejectedByOwner();
	}
	
	@Test(priority = 2)
	public void RequestRejectedByManager() throws Exception{
		RequestRejectedByManager_Action rrbma = new RequestRejectedByManager_Action();
		rrbma.RequestRejectedByManager();	
	}
	
	@Test(priority = 3)
	public void RequestRejectedByMember() throws Exception{
		RequestRejectedByMember_Action rrbma1 = new RequestRejectedByMember_Action();
		rrbma1.RequestRejectedByMember();	
	}
	
	@Test(priority = 4)
	public void ProductRequestedByOwner() throws Exception{
		ProductRequestedByOwner_Action prboa = new ProductRequestedByOwner_Action();
		prboa.ProductRequestedByOwner();
	}
	
	@Test(priority = 5)
	public void ProductRequestedByManager() throws Exception{
		ProductRequestedByManager_Action prbma = new ProductRequestedByManager_Action();
		prbma.ProductRequestedByManager();
	}
	
	@Test(priority = 6)
	public void ProductRequestedByMember() throws Exception{
		ProductRequestedByMember_Action prbma1 = new ProductRequestedByMember_Action();
		prbma1.ProductRequestedByMember();	
	}*/
	
	@Test(priority = 7)
	public void ReqEditCountPriceNoteByOwner() throws Exception{
		ReqEditCountPriceNoteByOwner_Action recpnboa = new ReqEditCountPriceNoteByOwner_Action();
		recpnboa.ReqEditCountPriceNoteByOwner();
	}
	
	@Test(priority = 8)
	public void ReqEditCountPriceNoteByManager() throws Exception{
		ReqEditCountPriceNoteByManager_Action recpnbma = new ReqEditCountPriceNoteByManager_Action();
		recpnbma.ReqEditCountPriceNoteByManager();	
	}
	
	@Test(priority = 9)
	public void ReqEditCountPriceNoteByMember() throws Exception{
		ReqEditCountPriceNoteByMember_Action recpnbma1 = new ReqEditCountPriceNoteByMember_Action();
		recpnbma1.ReqEditCountPriceNoteByMember();
	}
	
	@Test(priority = 10)
	public void ReqEditApproveTabByOwner() throws Exception{
		ReqEditApproveTabByOwner_Action reatboa = new ReqEditApproveTabByOwner_Action();
		reatboa.ReqEditApproveTabByOwner();
	}
	
	@Test(priority = 11)
	public void ReqEditApproveTabByManager() throws Exception{
		ReqEditApproveTabByManager_Action reatbma = new ReqEditApproveTabByManager_Action();
		reatbma.ReqEditApproveTabByManager();
	}
	
	@Test(priority = 12)
	public void ReqEditApproveTabByMember() throws Exception{
		ReqEditApproveTabByMember_Action reatbma1 = new ReqEditApproveTabByMember_Action();
		reatbma1.ReqEditApproveTabByMember();
	}
	
	@Test(priority = 13)
	public void ReqEditOrderedTabByOwner() throws Exception{
		ReqEditOrderedTabByOwner_Action reotboa = new ReqEditOrderedTabByOwner_Action();
		reotboa.ReqEditOrderedTabByOwner();
	}
	
	@Test(priority = 14)
	public void ReqEditOrderedTabByManager() throws Exception{
		ReqEditOrderedTabByManager_Action rrbma = new ReqEditOrderedTabByManager_Action();
		rrbma.ReqEditOrderedTabByManager();
	}
	
	@Test(priority = 15)
	public void ReqEditOrderedTabByMember() throws Exception{
		ReqEditOrderedTabByMember_Action rrbma1 = new ReqEditOrderedTabByMember_Action();
		rrbma1.ReqEditOrderedTabByMember();	
	}
	
	/*@Test(priority = 16)
	public void RequestApprovedByOwner() throws Exception{
		RequestApprovedByOwner_Action prboa = new RequestApprovedByOwner_Action();
		prboa.RequestApprovedByOwner();
	}
	
	@Test(priority = 17)
	public void RequestApprovedByManager() throws Exception{
		RequestApprovedByManager_Action prbma = new RequestApprovedByManager_Action();
		prbma.RequestApprovedByManager();
	}
	
	@Test(priority = 18)
	public void RequestApprovedByMember() throws Exception{
		RequestApprovedByMember_Action prbma1 = new RequestApprovedByMember_Action();
		prbma1.RequestApprovedByMember();	
	}
	
	@Test(priority = 19)
	public void ReqMovApprovedToOrderedByOwner() throws Exception{
		ReqMovApprovedToOrderedByOwner_Action recpnboa = new ReqMovApprovedToOrderedByOwner_Action();
		recpnboa.ReqMovApprovedToOrderedByOwner();
	}
	
	@Test(priority = 20)
	public void ReqMovApprovedToOrderedByManager() throws Exception{
		ReqMovApprovedToOrderedByManager_Action recpnbma = new ReqMovApprovedToOrderedByManager_Action();
		recpnbma.ReqMovApprovedToOrderedByManager();
	}
	
	@Test(priority = 21)
	public void ReqMovApprovedToOrderedByMember() throws Exception{
		ReqMovApprovedToOrderedByMember_Action recpnbma1 = new ReqMovApprovedToOrderedByMember_Action();
		recpnbma1.ReqMovApprovedToOrderedByMember();
	}
	
	@Test(priority = 22)
	public void ReqMovOrderedToInventoryByOwner() throws Exception{
		ReqMovOrderedToInventoryByOwner_Action reatboa = new ReqMovOrderedToInventoryByOwner_Action();
		reatboa.ReqMovOrderedToInventoryByOwner();
	}
	
	@Test(priority = 23)
	public void ReqMovOrderedToInventoryByManager() throws Exception{
		ReqMovOrderedToInventoryByManager_Action reatbma = new ReqMovOrderedToInventoryByManager_Action();
		reatbma.ReqMovOrderedToInventoryByManager();
	}
	
	@Test(priority = 24)
	public void ReqMovOrderedToInventoryByMember() throws Exception{
		ReqMovOrderedToInventoryByMember_Action reatbma1 = new ReqMovOrderedToInventoryByMember_Action();
		reatbma1.ReqMovOrderedToInventoryByManager();
	}
	
	@Test(priority = 25)
	public void ReqMovApprovedToRequestedByOwner() throws Exception{
		ReqMovApprovedToRequestedByOwner_Action recpnboa = new ReqMovApprovedToRequestedByOwner_Action();
		recpnboa.ReqMovApprovedToRequestedByOwner();
	}
	
	@Test(priority = 26)
	public void ReqMovApprovedToRequestedByManager() throws Exception{
		ReqMovApprovedToRequestedByManager_Action recpnbma = new ReqMovApprovedToRequestedByManager_Action();
		recpnbma.ReqMovApprovedToRequestedByManager();
	}
	
	@Test(priority = 27)
	public void ReqMovApprovedToRequestedByMember() throws Exception{
		ReqMovApprovedToRequestedByMember_Action recpnbma1 = new ReqMovApprovedToRequestedByMember_Action();
		recpnbma1.ReqMovApprovedToRequestedByMember();
	}
	
	@Test(priority = 28)
	public void ReqMovOrderedToApprovedByOwner() throws Exception{
		ReqMovOrderedToApprovedByOwner_Action reatboa = new ReqMovOrderedToApprovedByOwner_Action();
		reatboa.ReqMovOrderedToApprovedByOwner();
	}
	
	@Test(priority = 29)
	public void ReqMovOrderedToApprovedByManager() throws Exception{
		ReqMovOrderedToApprovedByManager_Action reatbma = new ReqMovOrderedToApprovedByManager_Action();
		reatbma.ReqMovOrderedToApprovedByManager();
	}
	
	@Test(priority = 30)
	public void ReqMovOrderedToApprovedByMember() throws Exception{
		ReqMovOrderedToApprovedByMember_Action reatbma1 = new ReqMovOrderedToApprovedByMember_Action();
		reatbma1.ReqMovOrderedToApprovedByMember();
	}*/

}
