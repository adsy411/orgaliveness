Feature: Assigning code to a project
	Assigning code to a project by lab owner
	
	Scenario: Assign a code to a project
		Given User is logged in to the application and is in project main page
		When  User clicks on project settings
		And   Selects the assign a code option
		And   Enters project code
		And   Clicks on Save button
		Then  Success message should display
		And   Project code should be added to the project
		And   Click on User Settings->logout and Close the application