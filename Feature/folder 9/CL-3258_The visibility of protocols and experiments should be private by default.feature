Feature: The visibility of protocols n experiments should be private by default.
	Whenever we create a experiment or a protocol template, The visiblity by default should be private but user
	can later change the visiblity to public
	
	# Automation scenario
	Scenario: Validation of default scope for protocol template
	Given User is logged in to the application as lab owner
	When User navigate to Research Hub -> Protocol Templates
	And  Clicks on Create New button
	Then The visiblity should be selected as private by default in add protocol modal
	
	# Automation scenario
	Scenario: Validation of default scope for experiments
	Given User is logged in to the application as lab owner
	When User navigate to Research Hub -> Projects
	And  Clicks on Create New button in Projects list page
	And  Creates a new project
	When Users clicks on project name link from Projects list page
	And  Clicks on add new experiment
	Then The visiblity should be selected as private by default in add experiment modal
	
	# Automation scenario
	Scenario: Validation of scope for experiment loaded from protocol template
	Given User is logged in to the application as lab owner
	Given User has created project and protocol template
	Given User is in project detail page
	When  User clicks on load protocol template option
	And   Selects a protocol template from load template modal
	And   Navigates to experiment detail page
	Then  The visiblity scope for experiment should be selected as private by default in experiment detail page
	
	# Automation scenario
	Scenario: Changing visiblity scope for the experiment after creation
	Given User is logged in to the application as lab owner
	Given User has created project and is in project detail page
	When  User clicks on experiment name link and navigates to experiment detail page
	And   Changes the visiblity from private to public
	Then  The visiblity scope for experiment should get changed to public successfully
	
	# Automation scenario
	Scenario: Changing visiblity scope for the protocol template after creation
	Given User is logged in to the application as lab owner
	Given User has created protocol templates and is in Protocol Templates detail page
	When  User clicks on visiblity scope feature 
	Then  The visiblity scope for protocol template should get changed to public successfully