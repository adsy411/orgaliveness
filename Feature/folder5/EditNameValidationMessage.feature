
@EditNameValidationMessage_Action
Feature: EditNameValidationMessage

Scenario: Verify Edit Name Validation Message
Given Login to Application
When Click on User Settings and go to My Profile
And Clear First and Last Name field and click on Save then Verify Validation message
Then Click on User Settings->logout and Close the application