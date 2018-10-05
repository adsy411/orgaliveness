
@DeleteMemberAndInviteFromOwner_Action
Feature: Delete Member and Invite From Owner

Scenario: Delete Member and Invite From Owner
Given Login to Application with specific user Two
When Click on User Settings and go to Lab Members
Then Delete Lab Member then Click Add New Member and Enter the deleted mail ID
Then Click on the Pending Status to resend the email verification
Then Click on User Settings->logout and Close the application
