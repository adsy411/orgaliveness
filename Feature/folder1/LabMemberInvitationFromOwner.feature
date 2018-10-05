
@LabMemberInvitationFromOwner_Action
Feature: Invite Lab Member from Owner Login

Scenario: Invite Lab Member from Owner Login
Given Login to Application with specific user Two
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Register the Member from Owner Login and Verify First and Last Name
Then Click on User Settings->logout and Close the application
