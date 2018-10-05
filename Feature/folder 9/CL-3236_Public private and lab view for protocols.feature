Feature: Public private and lab view for protocols.
	As an user he should be able to create protocol templates with visiblity as Public or Private or Lab
	and user shall be able to access the corresponding protocol templates based on visiblity provided by user.
	
	Scenario: Validation of protocol template visiblity with scope as Public under different lab of same organisation
	Given User is logged in to the application as Lab owner and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in with different user id of different lab but belonging to same organisation
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list in that lab
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template visiblity with scope as Public under same lab of same organisation
	Given User is logged in to the application as Lab owner and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in with different user id of same lab of same organisation
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list in that lab
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template visiblity with scope as Public under different organisation
	Given User is logged in to the application as Lab owner and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in with different user id belonging to different organisation
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should not display in the Protocol template list in that lab
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template visiblity with scope as Private for protocol creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list for creator
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template visiblity with scope as Private for lab owner
	Given User is logged in to the application as Lab manager and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template visiblity with scope as Private for lab member
	Given User is logged in to the application as Lab manager and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab member
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should not display in the Protocol template list for lab member
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template with scope as lab only for member belonging to different lab
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner of different lab
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should not display in the Protocol template list for lab owner
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of protocol template with scope as lab only for member belonging to same lab
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab manager of same lab
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab manager
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as public by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as private by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as lab only by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as public by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as private by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Update protocol template with scope as lab only by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner belonging to same lab
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Updates protocol template details
	Then  Protocol template details should get updated successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as public by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as private by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as lab only by protocol template creator
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as public by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as Public
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as private by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as private
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Delete protocol template with scope as lab only by lab owner
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	And   Enters protocol name, protocol description
	And   Selects the visiblity scope as lab only
	And   Clicks on save protocol button
	Then  Protocol template should be created successfully and visible in protocol template list
	Then  Click on User Settings->logout and Close the application
	When  User logs in as lab owner belonging to same lab
	And   Navigates to Research Hub -> Protocol template list
	Then  Protocol template should display in the Protocol template list for lab owner
	When  User clicks on protocol template name link
	And   Clicks on protocol template settings
	And   Clicks on Delete Template option
	Then  Delete confimation modal should display
	When  User clicks on Delete button
	Then  Protocol template details should get deleted successfully
	Then  Click on User Settings->logout and Close the application
	
	Scenario: Validation of default visiblity scope for protocol template
	Given User is logged in to the application as Lab member and is in protocol template list page
	When  User clicks on create new button
	Then  Visiblity scope for protocol template should be selected as Private by default in add protocol modal
	
	Scenario: Validation of default visiblity scope for experiment
	Given User is logged in to the application as Lab member and is in project list page
	When  User clicks on create new button
	And   Enters project name and description
	And   Clicks on Create button
	Then  Project should be created successfully
	When  User clicks on project name link
	And   Clicks on Create button and selects Add clicks on experiment option
	Then  Visiblity scope for experiment should be selected as Private by default in add experiment modal
	
	Scenario: Validation of visiblity scope for experiment while exporting protocol template with scope as public
	Given User is logged in to the application as Lab member and is in project list page
	Given User creates a project
	Given User creates a protocol template with visiblity scope as Public
	When  User clicks on import into a project option
	And   Selects the created project from Export Protocol Template modal
	And   Clicks on Export Protocol Template button
	Then  Success message "Success! Protocol Template Exported Successfully!" should display
	When  User navigates to Research Hub -> Projects
	And   Clicks on project name link from project list
	And   Clicks on experiment name link which is exported from protocol template
	Then  Visiblity scope for experiment should be selected as Private by default in experiment detail page
	
	Scenario: Validation of visiblity scope for experiment while exporting protocol template with scope as private
	Given User is logged in to the application as Lab member and is in project list page
	Given User creates a project
	Given User creates a protocol template with visiblity scope as private
	When  User clicks on import into a project option
	And   Selects the created project from Export Protocol Template modal
	And   Clicks on Export Protocol Template button
	Then  Success message "Success! Protocol Template Exported Successfully!" should display
	When  User navigates to Research Hub -> Projects
	And   Clicks on project name link from project list
	And   Clicks on experiment name link which is exported from protocol template
	Then  Visiblity scope for experiment should be selected as Private by default in experiment detail page
	
	Scenario: Validation of visiblity scope for experiment while exporting protocol template with scope as lab only
	Given User is logged in to the application as Lab member and is in project list page
	Given User creates a project
	Given User creates a protocol template with visiblity scope as lab only
	When  User clicks on import into a project option
	And   Selects the created project from Export Protocol Template modal
	And   Clicks on Export Protocol Template button
	Then  Success message "Success! Protocol Template Exported Successfully!" should display
	When  User navigates to Research Hub -> Projects
	And   Clicks on project name link from project list
	And   Clicks on experiment name link which is exported from protocol template
	Then  Visiblity scope for experiment should be selected as Private by default in experiment detail page