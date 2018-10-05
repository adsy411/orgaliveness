
@EmailNotificationSettings_Action
Feature: Email Notification Settings

Scenario: Verify Email Notification Settings
Given Login to Application
When Click on User Settings and go to My Profile
When Click on Profile Settings
Then Uncheck all the Notification and click on Save
When Click on Profile Settings
Then Check all the Notification and click on Save
Then Click on User Settings->logout and Close the application




