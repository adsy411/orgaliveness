Feature: Integration of commerce module with lab drive
	Integration of Commerce module with Lab drive, Attachments uploaded in E-Commerce should reflect in lab drive
	and changes done like replace, Remove file should reflect accordingly in lab drive
	
	# Positive test case
	Scenario: Verify commerce lab drive integration
		Given User is logged in to the application and is in lab catalog list page
		Given User creates a non sigma vendor lab catalog and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should display in inventory lab drive home page
		And   Click on User Settings->logout and Close the application
		
	# Positive test case
	Scenario: Verify commerce lab drive integration when user replace uploaded attachment in lab catalog
		Given User is logged in to the application and is in lab catalog list page
		Given User creates a non sigma vendor lab catalog and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should not display in inventory lab drive home page
		When  User navigates to lab catalog list page
		And   Clicks on lab catalog name link
		And   Replaces the sds file
		And   User navigates to lab drive home page
		And   Navigates to Inventory home page
		Then  SDS attachment file should be replaced by Replaced sds file in inventory lab drive home page
		And   Click on User Settings->logout and Close the application
		
	# Negative test case
	Scenario: Delete attachments in lab drive uploaded from lab catalog
		Given User is logged in to the application and is in lab catalog list page
		Given User creates a non sigma vendor material and uploads a sds attachment
		When  User navigates to lab drive home page
		And   Navigates to Inventory home page
		And   Clicks on file attachment options
		Then  Delete option should not display for attachments done from lab catalog
		And   Click on User Settings->logout and Close the application