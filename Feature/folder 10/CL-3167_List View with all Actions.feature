Feature: Lab drive home page List view actions
	As a user,He should be able to do list view actions like Open, Quick view, Download for lab drive files
	and open action for folder [As discussed with squad lead and scope of jira CL-3167 only Open option is allowed for folder]
	
	Scenario: Quick view file from list view from Research lab drive home page
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on quick view option corresponding to file
		Then  Application should open a new document viewer and file data should be successfully viewed
		
	Scenario: Quick view file from list view from Inventory lab drive home page
		Given User is logged in to the application and is in Inventory lab drive home page
		Given User has selected List view in Inventory lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on quick view option corresponding to file
		Then  Application should open a new document viewer and file data should be successfully viewed
		
	Scenario: Quick view file from list view from Unassociated lab drive home page
		Given User is logged in to the application and is in Unassociated lab drive home page
		Given User has selected List view in Unassociated lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on quick view option corresponding to file
		Then  Application should open a new document viewer and file data should be successfully viewed
		
	Scenario: Download file from list view from Research lab drive home page
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on download option corresponding to file
		Then  File should get downloaded successfully
		
	Scenario: Download file from list view from Inventory lab drive home page
		Given User is logged in to the application and is in Inventory lab drive home page
		Given User has selected List view in Inventory lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on download option corresponding to file
		Then  File should get downloaded successfully
		
	Scenario: Download file from list view from Unassociated lab drive home page
		Given User is logged in to the application and is in Unassociated lab drive home page
		Given User has selected List view in Unassociated lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on download option corresponding to file
		Then  File should get downloaded successfully
	
	Scenario: Delete file from list view from Research lab drive home page
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on delete option corresponding to file
		Then  Delete file confirmation modal should appear
		When  Users clicks on Yes button in Delete file confirmation modal
		Then  File should get deleted successfully from lab drive
		
	Scenario: Delete file from list view from Inventory lab drive home page
		Given User is logged in to the application and is in Inventory lab drive home page
		Given User has selected List view in Inventory lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on delete option corresponding to file
		Then  Delete file confirmation modal should appear
		When  Users clicks on Yes button in Delete file confirmation modal
		Then  File should get deleted successfully from lab drive
		
	Scenario: Delete file from list view from Unassociated lab drive home page
		Given User is logged in to the application and is in Unassociated lab drive home page
		Given User has selected List view in Unassociated lab drive home page
		When  User clicks on browser link
		And   Selects a file from the file upload modal
		And   Clicks on Open button in file upload modal
		Then  Success message should display and file should get uploaded successfully in lab drive
		When  User clicks on delete option corresponding to file
		Then  Delete file confirmation modal should appear
		When  Users clicks on Yes button in Delete file confirmation modal
		Then  File should get deleted successfully from lab drive
		
	Scenario: Open folder from list view from Research lab drive home page
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		When  User clicks on New Folder link
		And   Enters folder name
		And   Clicks on Create button
		Then  Success message should display and folder should get created successfully in lab drive
		When  User clicks on open folder option corresponding to folder
		Then  User should get navigated to folder page successfully
		
	Scenario: Open folder from list view from Inventory lab drive home page
		Given User is logged in to the application and is in Inventory lab drive home page
		Given User has selected List view in Inventory lab drive home page
		When  User clicks on New Folder link
		And   Enters folder name
		And   Clicks on Create button
		Then  Success message should display and folder should get created successfully in lab drive
		When  User clicks on open folder option corresponding to folder
		Then  User should get navigated to folder page successfully
		
	Scenario: Open folder from list view from Unassociated lab drive home page
		Given User is logged in to the application and is in Unassociated lab drive home page
		Given User has selected List view in Unassociated lab drive home page
		When  User clicks on New Folder link
		And   Enters folder name
		And   Clicks on Create button
		Then  Success message should display and folder should get created successfully in lab drive
		When  User clicks on open folder option corresponding to folder
		Then  User should get navigated to folder page successfully
		
	#Negative scenario	
	Scenario: Delete folder created from back end during project creation in Research lab drive home page
		Given User creates a project
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		Then  Delete option should not display for folders created during project creation
	
	#Negative scenario	
	Scenario: Delete folder created from back end during experiment creation in Research lab drive home page
		Given User creates a project and an experiment
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		Then  Delete option should not display for folders created during experiment creation
		
	#Negative scenario
	Scenario: Delete folder created from back end during protocol template creation in Research lab drive home page
		Given User creates a protocol template
		Given User is logged in to the application and is in Research lab drive home page
		Given User has selected List view in Research lab drive home page
		Then  Delete option should not display for folders created during protocol template creation		