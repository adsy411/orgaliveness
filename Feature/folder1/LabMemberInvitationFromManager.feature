
@LabMemberInvitationFromManager_Action
Feature: Invite Lab Member from Manager Login

Scenario: Invite Lab Member from Manager Login
Given Login to Application with specific user Three
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Register the Member from Manager Login and Verify First and Last Name
Then Click on User Settings->logout and Close the application
