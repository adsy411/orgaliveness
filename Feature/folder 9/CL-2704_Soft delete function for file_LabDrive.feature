Feature: Soft delete function for file
	As a lab user, User should be able to delete the file from lab drive
	
Scenario: Delete a file from lab drive which is created by lab owner himself
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a file attachment to it
	When  User navigates to lab drive page
	And   Clicks on delete icon next to the file attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  File should be deleted from lab drive
	
Scenario: Delete a folder from lab drive which is created by lab owner himself
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a folder attachment to it
	When  User navigates to lab drive page
	And   Clicks on delete icon next to the folder attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  Folder should be deleted from lab drive

Scenario: Delete a capture file from lab drive which is created by lab owner himself
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a capture file attachment to it
	When  User navigates to lab drive page
	And   Clicks on delete icon next to the capture file attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  capture file attachment should be deleted from lab drive	
	
Scenario: Delete a file from lab drive which is created by other lab members
	Given User is logged in to the application as lab owner
	Given User has created a project and adds a file attachment to it
	When  User navigates to lab drive page
	And   Clicks on delete icon next to the file attachment
	Then  A Confirmation modal should display
	When  User clicks on OK button
	Then  File should be deleted from lab drive