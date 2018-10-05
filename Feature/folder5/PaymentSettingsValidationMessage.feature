
@PaymentSettingsValidationMessage_Action
Feature: PaymentSettingsValidationMessage

Scenario: Verify Payment Settings Validation Message
Given Login to Application
When Click on User Settings and go to My Profile
And Click on Profile Settings and Select Payment Settings then verify the Validation messages
Then Click on User Settings->logout and Close the application