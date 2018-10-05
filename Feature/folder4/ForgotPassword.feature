
@ForgotPassword_Action
Feature: Forgot Password

Scenario: Verify Forgot Password Link
Given Click on Login page -> Forgot Password
When Enter Email Address and Click on Submit then verify the information message in Login page
Then Close the Browser

