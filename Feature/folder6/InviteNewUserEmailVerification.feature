
@InviteNewUserEmailVerification_Action
Feature: InviteNewUserEmailVerification

Scenario: Invite New User Email Template Verification
Given Login to Application with specific user3
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Enter Required fields and Invite
And Open Mailinator in new tab and verify Invite New User Email Template
And Delete the Invited Member
Then Click on User Settings->logout and Close the application
