Feature: Activity Stream for Equipment schedule
	As a lab user, When ever user does any actions Device Added , Device Updated, Device Removed, Calendar entry scheduled for this device
	Calendar entry updated for the device, Calendar entry removed, File Attachment, Notes added, An Entry should be registered
	in the Activity stream in dashboard.
	
Scenario: Verify dashboard activity stream for device addition
	Given User logs in to the application
	When  User navigates to Equipment
	And   Clicks on Add New Equipment
	And   Enters all mandatory details of the equipment
	And   Clicks on Add button
	Then  Equipment should get added successfully
	When  User navigates to application dashboard
	Then  An entry must be created for adding equipment action in activity stream
	And  Click on User Settings->logout and Close the application
	
Scenario: Verify dashboard activity stream for updating device details
	Given User logs in to the application
	When  User navigates to Equipment
	And   Clicks on Add New Equipment
	And   Enters all mandatory details of the equipment
	And   Clicks on Add button
	Then  Equipment should get added successfully
	When  User clicks on equipment name link from equipment list page
	And   Updates equipment details
	And   User navigates to application dashboard
	Then  An entry must be created for updating equipment action in activity stream
	And  Click on User Settings->logout and Close the application

Scenario: Verify dashboard activity stream for device removal
	Given User logs in to the application
	When  User navigates to Equipment
	And   Clicks on Add New Equipment
	And   Enters all mandatory details of the equipment
	And   Clicks on Add button
	Then  Equipment should get added successfully
	When  User clicks on equipment name link from equipment list page
	And   Click on Delete button
	And   Click on Yes button in delete confirmation modal
	Then  Equipment should get removed successfully
	When  User navigates to application dashboard
	Then  An entry must be created for removing equipment action in activity stream
	And  Click on User Settings->logout and Close the application

Scenario: Verify dashboard activity stream for scheduling device in calendar
	Given User logs in to the application
	Given User has created an Equipment
	When  User clicks on equipment name link from equipment list page
	And   Schedule the equipment
	Then  Equipment should be scheduled successfully and an entry must be registered in calendar for that slot 
	And   User navigates to application dashboard
	Then  An entry must be created for scheduling equipment action in activity stream
	And  Click on User Settings->logout and Close the application
	
Scenario: Verify dashboard activity stream for updating device schedule in calendar entry
	Given User logs in to the application
	Given User has created an Equipment
	When  User clicks on equipment name link from equipment list page
	And   Schedule the equipment
	Then  Equipment should be scheduled successfully and an entry must be registered in calendar for that slot
	When  User clicks on equipment schedule on calendar
	And   Updates the equipment schedule
	Then  Equipment should get rescheduled successfully
	When  User navigates to application dashboard
	Then  An entry must be created for updating equipment schedule action in activity stream
	And  Click on User Settings->logout and Close the application

Scenario: Verify dashboard activity stream for device schedule removal in calendar entry
	Given User logs in to the application
	Given User has created an Equipment
	When  User clicks on equipment name link from equipment list page
	And   Schedule the equipment
	Then  Equipment should be scheduled successfully and an entry must be registered in calendar for that slot
	When  User clicks on equipment schedule on calendar
	And   Removes the equipment schedule
	And   User navigates to application dashboard
	Then  An entry must be created for removing equipment schedule action in activity stream
	And  Click on User Settings->logout and Close the application

Scenario: Verify dashboard activity stream for file attachments to device
	Given User logs in to the application
	When  User navigates to Equipment
	And   Clicks on Add New Equipment
	And   Enters all mandatory details of the equipment
	And   Clicks on Add button
	Then  Equipment should get added successfully
	When  User clicks on equipment name link from equipment list page
	And   Uploads a file to the equipment
	And   User navigates to application dashboard
	Then  An entry must be created for uploading file to equipment action in activity stream
	And  Click on User Settings->logout and Close the application

Scenario: Verify dashboard activity stream for adding note to device
	Given User logs in to the application
	When  User navigates to Equipment
	And   Clicks on Add New Equipment
	And   Enters all mandatory details of the equipment
	And   Clicks on Add button
	Then  Equipment should get added successfully
	When  User clicks on equipment name link from equipment list page
	And   Click on add note button
	And   Enters note title and note descriptions
	And   Clicks on Add button
	Then  Note should get added successfully
	When  User navigates to application dashboard
	Then  An entry must be created for adding note to equipment action in activity stream
	And  Click on User Settings->logout and Close the application