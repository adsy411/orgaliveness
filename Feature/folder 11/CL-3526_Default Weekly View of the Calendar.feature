Feature: Default Weekly View of the Calendar
	As a lab user, When ever user logs in to the application he should be able to view Calendar in weekly format by default

Scenario: Verify Default Weekly View of the Calendar in dashboard
	When User logs in to the application
	Then User should be able to view the calendar in weekly format by default in dashboard
	And  Click on User Settings->logout and Close the application

Scenario: Verify Default Weekly View of the Calendar in calendar pop up
	Given User logs in to the application
	Given User creates an equipment
	When  User clicks on Calendar icon from card view
	Then  User should be able to view the calendar in weekly format by default in calendar pop up
	And   Click on User Settings->logout and Close the application

Scenario: Verify Default Weekly View of the Calendar in equipment detail page
	Given User logs in to the application
	Given User creates an equipment
	When  User clicks on equipment name link from equipment list page
	Then  User should be able to view the calendar in weekly format by default in equipment detail page
	And   Click on User Settings->logout and Close the application

Scenario: Verify Default Weekly View of the Calendar in dashboard if user changes the calendar view format
	When User logs in to the application
	Then User should be able to view the calendar in weekly format by default in dashboard
	When User changes the calendar format
	And  Click on User Settings->logout and Close the application
	And  User logs in to the application
	Then User should be able to view the calendar in weekly format by default in dashboard
	And  Click on User Settings->logout and Close the application