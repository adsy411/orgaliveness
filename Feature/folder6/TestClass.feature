
@TestClass
Feature: Add Material to Inventory

Scenario: Add Material to Inventory
Given Login to Application with specific user4
When Click on Inventory and Request then navigate to Material
And Click on Add New Material
And Enter the Material Name then Add to Inventory
Then Verify the Material Name
Then Click on User Settings->logout and Close the application