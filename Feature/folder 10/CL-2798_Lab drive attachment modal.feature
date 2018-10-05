Feature: Attachment modal for lab drive home page
	User should have a previliage to upload file in lab drive by browser link upload or by drag and drop
	
Scenario: Upload file in Research lab drive by Browse file upload method
	Given User is in Research lab drive home page
	When  User clicks on Browse link in file upload section
	And   Selects a file from file upload window
	And   Clicks on open button
	Then  File should get uploaded successfully 

Scenario: Upload file in Research lab drive by drag and drop upload
	Given User is in Research lab drive home page
	When  User drags a file from system and drops in file upload section
	Then  File should get uploaded successfully

Scenario: Upload file in Inventory lab drive by Browse file upload method
	Given User is in Inventory lab drive home page
	When  User clicks on Browse link in file upload section
	And   Selects a file from file upload window
	And   Clicks on open button
	Then  File should get uploaded successfully
	
Scenario: Upload file in Inventory lab drive by drag and drop upload
	Given User is in Inventory lab drive home page
	When  User drags a file from system and drops in file upload section
	Then  File should get uploaded successfully
	
Scenario: Upload file in Unassociated lab drive by Browse file upload method
	Given User is in Unassociated lab drive home page
	When  User clicks on Browse link in file upload section
	And   Selects a file from file upload window
	And   Clicks on open button
	Then  File should get uploaded successfully

Scenario: Upload file in Unassociated lab drive by drag and drop upload
	Given User is in Unassociated lab drive home page
	When  User drags a file from system and drops in file upload section
	Then  File should get uploaded successfully