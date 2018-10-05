Feature: Equipment type and manufacture should be autopopulated in add equipment modal
	Equipment type and manufacture should be autopopulated with predetermined options and user should
	be able to add new equipment type and manufacturer in add equipment modal.
	
Scenario: Verify equipment type and manufacturer is autopoulated in add equipment modal
	Given User is logged in to application
	When  User navigates to Equipment
	And   Clicks on Add new Equipment button
	Then  Equipment type and manufacturer should be autopopulated
	And   Click on User Settings->logout and Close the application
	
Scenario: Create new equipment type in add equipment modal
	Given User is logged in to application
	When  User navigates to Equipment
	And   Clicks on Add new Equipment button
	When  User selects Add Equipment Type option
	And   Enters unique equipment type
	Then  Entered equipment type should get added as option under equipment type dropdown
	And   Click on User Settings->logout and Close the application
	
Scenario: Create new manufacturer in add equipment modal
	Given User is logged in to application
	When  User navigates to Equipment
	And   Clicks on Add new Equipment button
	When  User selects Add Manufacturer option
	And   Enters unique manufacturer name
	Then  Entered manufacturer should get added as option under manufacturer dropdown
	And   Click on User Settings->logout and Close the application
	
Scenario: Validate duplicate equipment type creation in add equipment modal
	Given User is logged in to application
	When  User navigates to Equipment
	And   Clicks on Add new Equipment button
	When  User selects Add Equipment Type option
	And   Enters duplicate equipment type
	Then  Warning message "Equipment Type already present" should display and add equipment type action should be blocked
	And   Click on User Settings->logout and Close the application
	
Scenario: Validate duplicate manufacturer creation in add equipment modal
	Given User is logged in to application
	When  User navigates to Equipment
	And   Clicks on Add new Equipment button
	When  User selects Add Manufacturer option
	And   Enters duplicate manufacturer name
	Then  Warning message "Equipment Manufacturer present already" should display and add manufacturer action should be blocked
	And   Click on User Settings->logout and Close the application
	
Scenario: Verify equipment type and manufacturer is autopoulated in equipment detail page
	Given User creates a equipment in application
	When  User clicks on equipment name link
	And   Clicks on edit equipment details
	Then  Equipment type and manufacturer should be autopopulated
	And   Click on User Settings->logout and Close the application
	
Scenario: Create new equipment type in equipment detail page
	Given User creates a equipment in application
	When  User clicks on equipment name link
	And   Clicks on edit equipment details
	When  User selects Add Equipment Type option
	And   Enters unique equipment type
	Then  Entered equipment type should get added as option under equipment type dropdown
	And   Click on User Settings->logout and Close the application
	
Scenario: Create new manufacturer in equipment detail page
	Given User creates a equipment in application
	When  User clicks on equipment name link
	And   Clicks on edit equipment details
	When  User selects Add Manufacturer option
	And   Enters unique manufacturer name
	Then  Entered manufacturer should get added as option under manufacturer dropdown
	And   Click on User Settings->logout and Close the application
	
Scenario: Validate duplicate equipment type creation in equipment detail page
	Given User creates a equipment in application
	When  User clicks on equipment name link
	And   Clicks on edit equipment details
	When  User selects Add Equipment Type option
	And   Enters duplicate equipment type
	Then  Warning message "Equipment Type already present" should display and add equipment type action should be blocked
	And   Click on User Settings->logout and Close the application
	
Scenario: Validate duplicate manufacturer creation in equipment detail page
	Given User creates a equipment in application
	When  User clicks on equipment name link
	And   Clicks on edit equipment details
	When  User selects Add Manufacturer option
	And   Enters duplicate manufacturer name
	Then  Warning message "Equipment Manufacturer present already" should display and add manufacturer action should be blocked
	And   Click on User Settings->logout and Close the application