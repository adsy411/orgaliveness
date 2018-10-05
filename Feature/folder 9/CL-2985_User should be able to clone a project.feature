Feature: Clone project
	As an user he should be able to clone a project if he is a lab owner or a project contributor
	
	Scenario: Validation of clone project previlage for project owner
	Given User is logged in as lab member and is in project list
	When  User clicks on Create New button
	And   Enters project name and description
	And   Clicks on save
	Then  Project should be created successfully
	When  User clicks on project name link
	Then  Clone project option should display in project detail page for project owner
	
	Scenario: Validation of clone project previlage for project contributor
	Given User is logged in as lab member and is in project list
	When  User clicks on Create New button
	And   Enters project name and description
	And   Clicks on save
	Then  Project should be created successfully
	When  User clicks on project name link
	Then  Clone project option should display in project detail page for project contributor
	
	Scenario: Validation of clone project previlage for project viewer
	Given User is logged in as lab member and is in project list
	When  User clicks on Create New button
	And   Enters project name and description
	And   Clicks on save
	Then  Project should be created successfully
	When  User clicks on project name link
	Then  Clone project option should not display in project detail page for project viewer
	
	Scenario: Clone project by project owner
	Given User is logged in as lab member and is in project list
	Given User creates a project and experiment with all field details
	When  User clicks on project name link
	And   Clicks on Clone project option
	Then  Project should get Cloned successfully
	When  User clicks on project name link for the cloned project in project list
	Then  Project title and description should be cloned
	And   Project attachments should be cloned
	And   All the experiments under the project for which user has viewer permission should be cloned
	But   Project code and project permissions should not be cloned
	When  User clicks on experiment name link for which user has viewer permission
	Then  Experiment title and description should be cloned
	Then  Experiment attachments should be cloned
	And   Experiment step and step notes should be cloned
	And   Materials under the experiment should be cloned
	But   Experiment code and Experiment permissions and Experiment level notes should not be cloned
	
	Scenario: Clone project by project contributor
	Given User is logged in as lab member and is in project list
	Given User creates a project and experiment with all field details
	Then  Click on User Settings->logout and Close the application
	When  User logs in to application as project contributor
	And   Navigates to Research Hub -> Projects
	When  User clicks on project name link
	And   Clicks on Clone project option
	Then  Project should get Cloned successfully
	When  User clicks on project name link for the cloned project in project list
	Then  Project title and description should be cloned
	And   Project attachments should be cloned
	And   All the experiments under the project for which user has viewer permission should be cloned
	But   Project code and project permissions should not be cloned
	When  User clicks on experiment name link for which user has viewer permission
	Then  Experiment title and description should be cloned
	Then  Experiment attachments should be cloned
	And   Experiment step and step notes should be cloned
	And   Materials under the experiment should be cloned
	But   Experiment code and Experiment permissions and Experiment level notes should not be cloned