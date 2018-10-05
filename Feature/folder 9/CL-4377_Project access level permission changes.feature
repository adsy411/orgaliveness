Feature: Project permissions access level changes

Background:
	Given	User has logged into the application
	Given	User has created a project and is in project detail page
	
	# Positive
	Scenario: Validations of permissions in project permissions modal
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	Role of the Project creator will be owner
	And		Role of the Lab owner will be owner
	And		Role of the Project members will be viewer
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Change role of lab member from viewer to owner in project permissions modal
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Changes the role of the Project members from viewer to owner
	And		Clicks on save
	And		Role of the project viewer should get updated successfully
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Validation of Lab level project scope
	Given	User navigates to project list
	When	User clicks on Add Project button
	And		Enters project details
	And		Selects Lab level scope radio button
	And		Clicks on Create button
	Then	Project should get created successfully
	When	User switch to other lab
	And		Navigates to project list
	Then	Project should not display in project list
	Then  	Click on User Settings->logout and Close the application
	When 	Another lab member logs into system
	And		Navigates to project list
	Then	Project should display in project list
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Validation of Lab level project scope when a new member is added after project creation
	When	User navigates to lab members page
	And		Invites another lab member and Registers the member into the system
	Then  	Click on User Settings->logout and Close the application
	When 	Newly registered lab member logs into system
	And		Navigates to project list
	Then	Project should display in project list
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Change role of Project member from owner to viewer in project permissions modal
	*		Click on User Settings->logout and Close the application
	When	User logs in to system as project owner
	And		Navigates to project list
	And		Navigate to project detail page
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Changes the role of the Project members from owner to viewer
	And		Clicks on save
	And		Role of the project owner should get updated successfully
	Then  	Click on User Settings->logout and Close the application
	
	# Negative
	Scenario: Changing project permissions of a project creator
	*		Click on User Settings->logout and Close the application
	When	User logs in to system as project owner
	And		Navigates to project list
	And		Navigate to project detail page
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Tries to change the role of the Project creator from project permissions
	Then	User should not be able to change the Project creator's role
	Then  	Click on User Settings->logout and Close the application
	
	# Negative
	Scenario: Changing project permissions of a Lab owner
	*		Click on User Settings->logout and Close the application
	When	User logs in to system as project owner
	And		Navigates to project list
	And		Navigate to project detail page
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Tries to change the role of the lab owner from project permissions
	Then	User should not be able to change the Lab owner's role
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Remove lab member from project permissions
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Removes any lab member from project permissions
	Then	Warning modal for project scope changes should appear
	And		User clicks on Continue button
	Then	Project permission changes should get saved successfully
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	Changes should get reflected successfully in the project permissions modal
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Remove project owner by another project owner from project permissions
	*		Click on User Settings->logout and Close the application
	When	User logs in to system as project owner
	And		Navigates to project list
	And		Navigate to project detail page
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	And		Removes project owner from project permissions
	And		Clicks on save button
	Then	Project owner should get removed successfully from project permissions
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Change project permissions from lab to restricted with retaining project viewers
	When	User clicks on Project access visibility icon
	Then	Warning modal for project scope changes should appear
	And		User clicks on Continue button
	Then	Project permission changes should get saved successfully
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	All the lab members should display in the project permissions modal
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Change project permissions from lab to restricted without retaining project viewers
	When	User clicks on Project access visibility icon
	Then	Warning modal for project scope changes should appear
	When	User checks "This project has Lab-level visibility.By removing this person the visibility will be changed to Restricted and new lab members will have to be manually added for access" checkbox
	And		User clicks on Continue button
	Then	Project permission changes should get saved successfully
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	Only project owners should be there in the project permissions modal
	Then  	Click on User Settings->logout and Close the application
	
	# Positive
	Scenario: Create Restricted level project
	Given	User navigates to project list
	When	User clicks on Add Project button
	And		Enters project details
	And		Selects Restricted level scope radio button
	And		Clicks on Create button
	Then	Project should get created successfully
	When	User navigates to project detail page
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	project creator should be there in the project permissions modal
	And		Lab owner should be there in the project permissions modal
	
	# Positive
	Scenario: Change project permissions from restricted to lab
	Given	User navigates to project list
	When	User clicks on Add Project button
	And		Enters project details
	And		Selects Restricted level scope radio button
	And		Clicks on Create button
	Then	Project should get created successfully
	When	User navigates to project detail page
	And		Changes project visibility scope from restricted to lab
	Then	Warning modal for project scope changes should appear
	And		User clicks on Continue button
	Then 	Project visiblity changes shoudl get saved successfully
	When	User clicks on Project actions
	And		Clicks on Project manage permissions
	Then	All the lab members should display in the project permissions modal
	Then  	Click on User Settings->logout and Close the application
	