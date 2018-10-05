
@ChangePassword_Action
Feature: ChangePassword

Scenario: Verify the changed password
Given Login to Application with Specific User One
When Click on User Settings and go to My Profile
And Click on Profile Settings and Select Change Password
And Enter the required field and click on Save
Then Click on User Settings and logout
And Verify the Password change by logging to Application
And Revert Previous Password
Then Click on User Settings->logout and Close the application
