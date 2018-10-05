
@ExistingMemberInvitation_Action
Feature: Existing Member Invitation

Scenario: Existing Member Invitation
Given Login to Application
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Invite existing Manager and Verify the alert message
Then Invite existing Member and Verify the alert message
Then Click on User Settings->logout and Close the application
