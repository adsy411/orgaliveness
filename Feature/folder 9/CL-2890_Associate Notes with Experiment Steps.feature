Feature: Associate notes with experiment steps
	As a lab user, User should be able to add notes to experiment steps,View notes and edit step notes.

Background: 
Given User is logged into the application and creates a project and a experiment

Scenario: Add a note to the experiment step
	Given User is in experiment page and creates a experiment step
	When  User clicks on note icon
	And   Enters note title and note description
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	And   Note should be successfully added to experiment step

Scenario: View the added note to the experiment step
	Given User is in experiment page and creates a experiment step
	When  User clicks on note icon
	And   Enters note title and note description
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	When  User clicks on View Notes link
	Then  Added step details should display
	
Scenario: Update the added note to the experiment step
	Given User is in experiment page and creates a experiment step
	When  User clicks on note icon
	And   Enters note title and note description
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	When  User clicks on View Notes link
	And   Click on Edit link of corresponding step note
	Then  Step note modal should display
	When  User edits the note details and clicks on add
	Then  Success message "Success! Note Updated Successfully!" should display
	When  User clicks on View Notes link
	Then  Edited step details should display
	
Scenario: Add multiple notes to the experiment step
	Given User is in experiment page and creates a experiment step
	When  User clicks on note icon
	And   Enters note title and note description
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	And   Note should be successfully added to experiment step
	When  User clicks on note icon again
	And   Enters same note title and note description details as the first note
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	When  User clicks on View Notes link
	Then  Multiple added step notes should display under that step

Scenario: Link notes of one experiment step to another
    Given User is in experiment page and creates a multiple experiment steps
	When  User clicks on note icon
	And   Enters note title and note description
	And   Clicks on Add button
	Then  Success message "Success! Note Created Successfully!" should display
	And   Note should be successfully added to experiment step
	When  User clicks on View Notes link
	And   Clicks on link feature and select different step
	Then  Added note should get removed from the previous step and gets associated to other step

Scenario: Link notes of one experiment level to experiment step
    Given User is in experiment page and adds a note at experiment level
    Given User creates a step to the experiment
	When  User clicks on link note feature at experiment level and selects an experiment step
	Then  Note should get removed from the note section at experiment level and gets associated to experiment step

Scenario: Delete step notes from experiment steps
    Given User is in experiment page and adds a note at experiment level
    Given User creates a step to the experiment
	When  User clicks on delete link corresponding to the step note
	Then  Step note should be deleted successfully			