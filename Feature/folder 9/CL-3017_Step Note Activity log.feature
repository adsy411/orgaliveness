Feature: Steps note activity log
	If user does an activity on step notes such as create step note, edit note,Link note,Delete note etc,
	A log must be created in Experiment logs.
	
	# Automation scenario
	Scenario: Verify Steps note activity log when user does actions on step note
		Given User is logged in to the application as lab owner
		Given User has created a project and a experiment
		Given User adds multiple steps to the experiment
		Given User creates an experiment level note inside experiment
		When  User clicks on step note feature,And enters step note details
		And   Clicks on save button
		And   Clicks on experiment log button
		Then  Experiment log should be updated with step note activity
		When  User clicks on view notes link
		And   Clicks on link step feature and selects another step
		And   Clicks on experiment log button
		Then  Experiment log should be updated with step note activity
		When  Users clicks on link step feature in experiment level notes and selects another step
		And   Clicks on experiment log button
		Then  Experiment log should be updated with step note activity
		When  User clicks on view notes link
		And   Clicks on edit step note link
		And   Edits step note details
		And   Clicks on save button
		And   Clicks on experiment log button
		Then  Experiment log should be updated with step note activity
		When  User clicks on view notes link
		And   Clicks on delete step note feature
		And   Clicks on experiment log button
		Then  Experiment log should be updated with step note activity
		Then  Click on User Settings->logout and Close the application