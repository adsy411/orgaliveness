Feature: Block multiple equipment schedule for same time slot
	Application should block the multiple equipment scheduling for same time slot and overlapping of time slots
	but it should allow the scheduling of equipments for non overlapping time slots.
	
Scenario: Schedule equipment for valid date time slot
	Given User creates a equipment in application and is in equipment detail page
	When  User enters valid date and time in equipment schedule
	And   Clicks on Schedule button
	Then  Equipment should be scheduled successfully and Schedule calendar should get updated

Scenario: Schedule equipment for past date and time
	Given User creates a equipment in application and is in equipment detail page
	When  User enters past date and time in From field in equipment schedule
	And   User enters current date and time in To field in equipment schedule
	And   Clicks on Schedule button
	Then  Warning message should display and Schedule equipment action should be blocked
	
Scenario: Schedule equipment for current day for an elapsed time slot
	Given User creates a equipment in application and is in equipment detail page
	When  User enters current date and enters elapsed time slot in schedule timings
	And   Clicks on Schedule button
	Then  Warning message should display and Schedule equipment action should be blocked
	
Scenario: Schedule equipment multiple times for same date time slot
	Given User creates a equipment in application and is in equipment detail page
	When  User enters valid date and time in equipment schedule
	And   Clicks on Schedule button
	Then  Equipment should be scheduled successfully and Schedule calendar should get updated
	When  User enters same date and time slot in schedule timings
	And   Clicks on Schedule button
	Then  Warning message should display and schedule equipment action should be blocked
	
Scenario: Schedule equipment multiple times for different time slots
	Given User creates a equipment in application and is in equipment detail page
	When  User enters valid date and time in equipment schedule
	And   Clicks on Schedule button
	Then  Equipment should be scheduled successfully and Schedule calendar should get updated
	When  User enters different time slot for the current day in schedule timings
	And   Clicks on Schedule button
	Then  Equipment should be scheduled successfully and Schedule calendar should get updated
	
Scenario: Schedule equipment multiple times for overlapping time slots
	Given User creates a equipment in application and is in equipment detail page
	When  User enters valid date and time in equipment schedule
	And   Clicks on Schedule button
	Then  Equipment should be scheduled successfully and Schedule calendar should get updated
	When  User enters overlapping time slot for the current day in schedule timings
	And   Clicks on Schedule button
	Then  Warning message should display and schedule equipment action should be blocked