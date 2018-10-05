
@UserRoleChangeEmailVerification_Action
Feature: UserRoleChangeEmailVerification

Scenario: User Role Change Email Template Verification
Given Login to Application with specific user4
When Click on User Settings and go to Lab Members
And Change the Role to Member
Then Open Mailinator in new tab and verify User Role Change Email Template
And Click on User Settings and go to Lab Members
And Change the Role to Manager
Then Click on User Settings->logout and Close the application
