
@UserActivatedEmailVerification_Action
Feature: UserActivatedEmailVerification

Scenario: User Activated Email Template Verification
Given Login to Application with specific user4
When Click on User Settings and go to Lab Members
And Change the Member Status to Inactive then change to Active again
Then Open Mailinator in new tab and verify User Activated Email Template
Then Click on User Settings->logout and Close the application

