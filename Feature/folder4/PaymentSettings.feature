
@PaymentSettings_Action
Feature: Payment Settings

Scenario: Verify Payment Settings page
Given Login to Application
When Click on User Settings and go to My Profile
And Click on Profile Settings and Select Payment Settings
And Add New PI and PO and click on Save
Then Click on User Settings->logout and Close the application
