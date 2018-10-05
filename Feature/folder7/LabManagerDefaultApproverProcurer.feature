Feature: Make Lab Manager as default Approver and Procurer.
		 As a lab manager, System should be automatically provided with Approver and Procurer roles by default.

Scenario: Verifying Default Approver and Orderer role as Lab Manager
       Given User is logged in as Lab Owner and is in Lab members page
       When  User invites a new lab manager to lab and registers to lab
       And   User navigates to lab settings Page
       Then  Lab manager approver and procurer status should be active and toggle set to ON
       And 	 Click on User Settings->logout and Close the application
       
Scenario: Verifying lab manager default Approver and Orderer status when user changes role from lab manager to Lab member
       Given User is logged in as Lab Owner and is in Lab members page
       When  User invites a new lab manager to lab and registers to lab
       And   User navigates to lab settings Page
       Then  Lab manager approver and procurer status should be active and toggle set to ON
       When  User navigates to Lab members page and changes member role from Lab manager to Lab member
       And   User navigates to lab settings Page
       Then  Default Approver and procurer status for lab manager should reset back to normal and toggle should be OFF
       And 	 Click on User Settings->logout and Close the application
       
Scenario: Verifying lab manager default Approver and Orderer status when user changes role from Lab member to lab manager
       Given User is logged in as Lab Owner and is in Lab members page
       When  User invites a new lab member to lab and registers to lab
       And   User navigates to lab settings Page
       Then  Lab member approver and procurer status should not be active and toggle set to OFF
       When  User navigates to Lab members page and changes member role from Lab member to Lab manager
       And   User navigates to lab settings Page
       Then  Default Approver and procurer status for lab manager should be active and toggle should be ON
       And 	 Click on User Settings->logout and Close the application