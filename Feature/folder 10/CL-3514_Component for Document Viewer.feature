Feature: Component for Document Viewer
	Whenever user views a file in lab drive, It should be viewable to user inside document viewer in a new tab.
	
Scenario: View research files in document viewer
	Given User logs in to the application
	When  User navigates to lab drive dashboard page
	And   Navigates to Research lab drive home page
	And   Uploads a file
	Then  File should be uploaded successfully
	When  User clicks on file options
	And   Selects option preview
	Then  Application should open document viewer in new tab and file contents should display in it successfully
	And  Click on User Settings->logout and Close the application
	
Scenario: View inventory files in document viewer
	Given User logs in to the application
	When  User navigates to lab drive dashboard page
	And   Navigates to inventory lab drive home page
	And   Uploads a file
	Then  File should be uploaded successfully
	When  User clicks on file options
	And   Selects option preview
	Then  Application should open document viewer in new tab and file contents should display in it successfully
	And  Click on User Settings->logout and Close the application
	
Scenario: View unassociated files in document viewer
	Given User logs in to the application
	When  User navigates to lab drive dashboard page
	And   Navigates to unassociated lab drive home page
	And   Uploads a file
	Then  File should be uploaded successfully
	When  User clicks on file options
	And   Selects option preview
	Then  Application should open document viewer in new tab and file contents should display in it successfully
	And  Click on User Settings->logout and Close the application
	
Scenario: View recently uploaded files in document viewer
	Given User logs in to the application
	When  User navigates to lab drive dashboard page
	And   Navigates to Research lab drive home page
	And   Uploads a file
	Then  File should be uploaded successfully
	When  User navigates to lab drive dashboard page
	And   Selects option preview for recent file uploaded
	Then  Application should open document viewer in new tab and file contents should display in it successfully
	And  Click on User Settings->logout and Close the application