
@UserProfileUpdateEmailVerification_Action
Feature: UserProfileUpdateEmailVerification

Scenario: User Profile Update Email Template Verification
Given Login to Application with specific user4
When Click on User Settings and go to My Profile
And Edit the First Name and Save
Then Open Mailinator in new tab and verify User Profile Update Email Template
And Click on User Settings and go to My Profile
And Edit the First Name same as before and Save
Then Click on User Settings->logout and Close the application
