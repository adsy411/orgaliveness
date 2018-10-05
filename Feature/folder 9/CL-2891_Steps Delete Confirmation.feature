Feature: Steps Delete Confirmation
	As a project creator or experiment creator, Protocol template creator, User should be prompted with
	a confirmation modal when ever user tries to delete steps in project, experiments, protocol templates.
	
	# Automation scenario
	Scenario: Delete steps in experiment and verify Delete Confirmation
		Given User is logged in to the application as lab owner
		Given User has created a project and a experiment and adds steps to the experiment
		When  User clicks on Step options and clicks on delete step
		Then  A Delete confirmation modal should display
		When  User click on OK button
		Then  Success message for successfully deleting step action should display
		And   Step should be deleted successfully from experiment
		Then  Click on User Settings->logout and Close the application
	
	# Automation scenario
	Scenario: Delete steps in protocol template and verify Delete Confirmation
		Given User is logged in to the application as lab owner
		Given User has created a protocol template and adds steps to the protocol template
		When  User clicks on Step options and clicks on  delete step
		Then  A Delete confirmation modal should display
		When  User click on OK button
		Then  Success message for successfully deleting step action should display
		And   Step should be deleted successfully from protocol template
		Then  Click on User Settings->logout and Close the application