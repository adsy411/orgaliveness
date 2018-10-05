Feature: Experiment Start date 
	As a lab user, He must be able to capture experiment start date and should have the ability to change the start
	date when ever required.
	
	Background:
	Given	User has logged into the application
	Given	User creates a project
	
	# Positive
	Scenario: Capture start date while creating new experiment
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	User should be able to see Experiment start date field
	And		Default date in start date field should be current date
	When	User enters experiment details
	And		Clears the Experiment start date field
	And		Clicks on Create button
	Then	Validation error message should appear in the Add Experiment modal
	When	User modify experiment start date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	Then	Start date should display modified start date
	
	# Positive
	Scenario: Modify experiment start date from experiment detail page
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	User should be able to see Experiment start date field
	And		Default date in start date field should be current date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Clicks on edit experiment start date
	Then	A modal should appear with experiment start date field
	When	User modify experiment start date and clicks on save button
	Then	Success message should appear for modified experiment start date
	And		Start date should display modified start date
	
	# Negative
	Scenario: Complete experiment step prior to experiment start date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	And		Modify experiment start date to future date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Create multiple steps in Experiment
	And		Click on Complete step link for any step
	Then	User should prompt with a Confirmation modal with Step execution date and Experiment start date
	And		Experiment start date in Confirmation modal should display correctly
	When	User enters Current date in Step execution date
	And		Clicks on OK Button
	Then	Error message should display in the confirmation modal saying "Cannot complete step before Experiment start date"
	And		Step execution date and Experiment start dates should be highlightened
	
	# Positive
	Scenario: Complete step after experiment start date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be current date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Create multiple steps in Experiment
	And		Click on Complete step link for any step
	Then	User should prompt with a Confirmation modal with Step execution date and Experiment start date
	And		Experiment start date in Confirmation modal should display correctly
	When	User enters Current date in Step execution date
	And		Clicks on OK Button
	Then	Experiment Step should get marked as completed successfully
	
	# Negative
	Scenario: Complete Experiment prior to experiment start date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	And		Modify experiment start date to future date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Mark as Completed button
	Then	Confirmation modal should appear for experiment completion
	When 	User clicks on OK button
	Then	Error message should display in the confirmation modal saying "Cannot complete experiment before experiment start date"
	
	# Positive
	Scenario: Complete Experiment after experiment start date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be current date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Mark as Completed button
	Then	Confirmation modal should appear for experiment completion
	When 	User clicks on OK button
	Then	Experiment should get completed successfully
	
	# Positive
	Scenario: Update Experiment start date while submitting experiment for review
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	And		Modify experiment start date to future date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Submit for review button
	Then	Confirmation modal should appear saying "The experiment start date is set in the future.  Would you like to update the start date or submit anyway?"
	When	User modifies experiment start date
	And		Clicks on update button
	Then	Success message should appear for updating of experiment start date
	And		Submit for review modal should display successfully
	And		Start date of the experiment should display modified date in experiment detail page
	
	# Positive
	Scenario: Submit Experiment for review prior to experiment start date and override warning
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	And		Modify experiment start date to future date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Submit for review button
	Then	Confirmation modal should appear saying "The experiment start date is set in the future.  Would you like to update the start date or submit anyway?"
	When	User clicks on Submit Anyway button
	Then	Submit for review modal should display successfully
	When	User enters reviewer details
	And		Enters authentication password
	And	 	Clicks on Submit button
	Then	Experiment should be submmitted for review successfully
	
	# Positive
	Scenario: Submit Experiment for review after experiment start date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be current date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Submit for review button
	Then	Submit for review modal should display successfully
	When	User enters reviewer details
	And		Enters authentication password
	And	 	Clicks on Submit button
	Then	Experiment should be submmitted for review successfully

	# Positive
	Scenario: Capturing Experiment start date for a cloned Experiment
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be current date
	When	User enters experiment details
	And		Modify experiment start date to past date
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Click on Clone Experiment
	Then	Experiment should get cloned successfully
	When	User navigates to experiment detail page for the cloned experiment
	Then	Experiment start date for that cloned experiment should display experiment start date of the original experiment
	
	# Positive
	Scenario: Capturing Experiment start date for a Experiment loaded as template
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Load template link
	And		Selects a protocol template and Clicks on Load template button
	Then	Protocol template should get loaded as experiment successfully
	When	User navigates to experiment detail page for Loaded template
	Then	Experiment start date for that experiment loaded from template should display current date
	
	# Negative
	Scenario: Updating Experiment start date after step completion date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be current date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Create multiple steps in Experiment
	And		Click on Complete step link for any step
	And		Enters Current date in Step execution date
	Then	Experiment Step should get marked as completed successfully
	When	User Clicks on edit experiment start date
	Then	A modal should appear with experiment start date field
	When	User modify experiment start date to a past date which is prior to Step execution date and clicks on save button
	Then	Error message saying "Experiment start date cannot be after the completion of the first step" should display
	
	# Positive
	Scenario: Updating Experiment start date prior to step completion date
	When	User navigates to project detail page
	And		Clicks on Create button and clicks on Add Experiment link
	Then	Default date in start date field should be future date
	When	User enters experiment details
	And		Clicks on Create button
	Then	Experiment should be created successfully
	When	User navigates to experiment detail page
	And		Create multiple steps in Experiment
	And		Click on Complete step link for any step
	And		Enters Current date in Step execution date
	Then	Experiment Step should get marked as completed successfully
	When	User Clicks on edit experiment start date
	Then	A modal should appear with experiment start date field
	When	User modify experiment start date to a past date which is after Step execution date and clicks on save button
	Then	Experiment Step should get marked as completed successfully
	