
@EditLabValidationMessage_Action
Feature: EditLabValidationMessage

Scenario: Verify Edit Lab Validation Message
Given Login to Application
When Click on User Settings and go to Lab Settings
And Clear Labname and Click on Save then verify Validation message
Then Click on User Settings->logout and Close the application