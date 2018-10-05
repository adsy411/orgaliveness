
@LabManagerInvitation_Action
Feature: Invite Lab Manager

Scenario: Invite Lab Manager from Owner Login
Given Login to Application with specific user Two
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Register the Manager and Verify First and Last Name
Then Click on User Settings->logout and Close the application
