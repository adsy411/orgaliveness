
@ChangePasswordErrorAndValidationMessage_Action
Feature: ChangePasswordErrorAndValidationMessage

Scenario: Verify the Change Password Error and Validation Message
Given Login to Application
When Click on User Settings and go to My Profile
And Click on Profile Settings and Select Change Password
And Click on Save and Verify Validation and Error messages in Change Password popup
Then Click on User Settings->logout and Close the application