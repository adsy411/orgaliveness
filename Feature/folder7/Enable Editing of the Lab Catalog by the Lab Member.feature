Feature: Edit lab catalog by lab member
	As a Lab member,User should be able to edit lab catalog product which may be created by lab manager, lab owner 
	or by lab member himself.

Scenario: Edit lab catalog product created by lab member
	Given Lab member is logged in to the application And is in lab catalog list
	Given Lab catalog product is created by lab member
	When  Lab member clicks on product name link of any product created by lab member
	And   Edits the product details except Catalog SKU and Vendor field
	And   Clicks on update product details button
	Then  Success message "Success! Catalog updated successfully!" should display
	Then  Click on User Settings->logout and Close the application

Scenario: Edit lab catalog product created by lab manager
	Given Lab member is logged in to the application And is in lab catalog list
	Given Lab catalog product is created by lab manager
	When  Lab member clicks on product name link of any product created by lab manager
	And   Edits the product details except Catalog SKU and Vendor field
	And   Clicks on update product details button
	Then  Success message "Success! Catalog updated successfully!" should display
	Then  Click on User Settings->logout and Close the application

Scenario: Edit lab catalog product created by lab owner
	Given Lab member is logged in to the application And is in lab catalog list
	Given Lab catalog product is created by lab owner
	When  Lab member clicks on product name link of any product created by lab owner
	And   Edits the product details except Catalog SKU and Vendor field
	And   Clicks on update product details button
	Then  Success message "Success! Catalog updated successfully!" should display
	Then  Click on User Settings->logout and Close the application