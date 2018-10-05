
@SwitchToDiffLab_Action
Feature: Switching to different Lab

Scenario: Switching to different Lab
Given Login to Application with specific user Two
When Click on User Settings and go to Switch Context
And Create New Lab then Go to Newly created Lab and Verify the Lab Name in User Settings
Then Click on User Settings->logout and Close the application
