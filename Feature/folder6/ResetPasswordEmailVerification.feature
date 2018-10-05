
@ResetPasswordEmailVerification_Action
Feature: ResetPasswordEmailVerification

Scenario: Reset Password Email Template Verification
Given Click on Forgot Password link
When Enter New Email Address and Click on Submit
And Open Mailinator in new tab and verify Reset Password Email Template then Reset Password and login
Then Click on User Settings and go to My Profile
And Click on Profile Settings and Select Change Password
And Enter the required field in Change Password popup and click on Save
Then Click on User Settings->logout and Close the application
