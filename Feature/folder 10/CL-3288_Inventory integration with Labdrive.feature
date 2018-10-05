Feature: Lab drive changes for Inventory module
	Integration of Inventory module with Lab drive, Attachments uploaded in Inventory should reflect in lab drive
	and changes done like replace, Remove file should reflect in lab drive
	
	# Positive test case
	Scenario: Verify inventory lab drive integration
		Given User is logged in to the application and is in Material list page
		Given User creates a non sigma vendor material and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should display in inventory lab drive home page
		And   Click on User Settings->logout and Close the application
		
	# Positive test case
	Scenario: Verify inventory lab drive integration when user replace uploaded attachment in inventory
		Given User is logged in to the application and is in Material list page
		Given User creates a non sigma vendor material and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should not display in inventory lab drive home page
		When  User navigates to material list page
		And   Clicks on material name link
		And   Replaces the sds file
		And   User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should be replaced by Replaced sds file in inventory lab drive home page
		And   Click on User Settings->logout and Close the application
		
	# Negative test case
	Scenario: Delete attachments in lab drive uploaded from inventory
		Given User is logged in to the application and is in Material list page
		Given User creates a non sigma vendor material and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		And   Clicks on inventory attachment options
		Then  Delete option should not display for attachments done from inventory
		And   Click on User Settings->logout and Close the application
		
	# Adhoc negative scernario (Not automatable)
	Scenario: Verify inventory lab drive integration when user removes uploaded attachments while adding material
		Given User is logged in to the application and is in Material list page
		Given User clicks on add material button
		And   Enters all details
		And   Uploads a sds attachments
		And   Removes the uploaded attachment
		And   Clicks on add to inventory
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should not display in inventory lab drive home page
		And   Click on User Settings->logout and Close the application