
@ForgotPasswordErrorAndValidationMessage_Action
Feature: ForgotPasswordErrorAndValidationMessage

Scenario: Verify Forgot Password Error and Validation Message
Given Click on Login page -> Forgot Password
When Enter Invalid Email Address and Click on Submit then verify the message
Then Close the Browser