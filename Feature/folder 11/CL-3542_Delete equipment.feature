Feature: Delete equipment from application
	User should be able to delete equipment from application
	
Scenario: Delete equipment from equipment list
	Given User has created an equipment
	When  User clicks on Delete feature corresponding to equipment
	Then  Delete equipment confirmation modal should appear
	When  User clicks on Yes button
	Then  Equipment should get deleted successfully
	And   Click on User Settings->logout and Close the application
	
Scenario: Delete equipment from equipment detail page
	Given User has created an equipment
	When  User clicks on equipment name link
	And   User clicks on Delete equipment feature
	Then  Delete equipment confirmation modal should appear
	When  User clicks on Yes button
	Then  Equipment should get deleted successfully
	And   User should navigate back to equipment list page
	And   Click on User Settings->logout and Close the application
	
Scenario: Delete equipment which is RESERVED
	Given User has created an equipment
	When  User clicks on equipment name link
	And   Schedules the equipment for Usage
	And   User clicks on delete equipment feature
	Then  Delete equipment confirmation modal should appear
	When  User clicks on Yes button
	Then  Warning message should display and equipment delete should get blocked
	And   Click on User Settings->logout and Close the application
	
Scenario: Delete equipment which is scheduled for Calibration
	Given User has created an equipment
	When  User clicks on equipment name link
	And   Schedules the equipment for Calibration
	And   User clicks on delete equipment feature
	Then  Delete equipment confirmation modal should appear
	When  User clicks on Yes button
	Then  Warning message should display and equipment delete should get blocked
	And   Click on User Settings->logout and Close the application

Scenario: Delete equipment which is scheduled for Maintenance
	Given User has created an equipment
	When  User clicks on equipment name link
	And   Schedules the equipment for Maintainence
	And   User clicks on delete equipment feature
	Then  Delete equipment confirmation modal should appear
	When  User clicks on Yes button
	Then  Warning message should display and equipment delete should get blocked
	And   Click on User Settings->logout and Close the application			