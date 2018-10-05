Feature: Add material in experiment
	Improvement in add material feature in experiment,As a lab owner,User should be able to add materials by
	CAS number search and Catalog number search and after adding material, Material details like LOT number,Catalog number,Quantity,Location
	should display in material list section in experiments

# Automation scenario
Scenario: Add material in experiment by Catalog number search
	Given User is logged in to the application and creates a project and experiment
	Given User is experiment page
	When  User clicks on Add material button
	And   Enters the material Catalog number in auto suggestion search field
	And   Selects the material from auto suggestions
	And   Enters the material quantity
	And   Clicks on Save button
	Then  Success message "Success! Material Added Successfully!" should display
	And   Material should get added to experiment
	And   Material details like LOT number,Catalog number,Quantity,Location should display in the experiment
	
Scenario: Add material in experiment by CAS number search
	Given User is logged in to the application and creates a project and experiment
	Given User is experiment page
	When  User clicks on Add material button
	And   Enters the material CAS number in auto suggestion search field
	And   Selects the material from auto suggestions
	And   Enters the material quantity
	And   Clicks on Save button
	Then  Success message "Success! Material Added Successfully!" should display
	And   Material should get added to experiment
	And   Material details like LOT number,Catalog number,Quantity,Location should display in the experiment	