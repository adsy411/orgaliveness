Feature: Soft delete function for file in research
	As a lab user, User should be able to delete the file from projects, experiments and protocol templates
	
Scenario: Delete a file from projects which is created by lab owner himself
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a file attachment to it
	When  User clicks on delete icon next to the file attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  File should be deleted from project
	Then  Click on User Settings->logout and Close the application
	
Scenario: Delete a file from experiment which is created by lab owner himself
	Given User is logged in to the application as lab owner
	Given User has created a project and experiment and adds a file attachment to experiment
	When  User clicks on delete icon next to the file attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  File should be deleted from experiment
	Then  Click on User Settings->logout and Close the application

Scenario: Delete a file from project by a lab member which is created by lab owner
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a file attachment to it
	Then  Click on User Settings->logout and Close the application
	And   User logs in as lab member
	And   Navigates to project list page
	And   Clicks on project created by lab owner and checks the attachment created by lab owner
	Then  Delete icon should not be there for attachments created by lab owner
	Then  Click on User Settings->logout and Close the application