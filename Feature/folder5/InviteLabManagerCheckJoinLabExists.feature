
@InviteLabManagerCheckJoinLabExists_Action
Feature: InviteLabManagerCheckJoinLabExists

Scenario: Invite Lab Manager Verify JoinLab Exists
Given Login to Application with specific user5
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Open Nadamail in New tab and create new email ID
And Navigate to Mconnected Lab and Invite the newly added Manager
And Navigate to Nadamail and Register newly added Manager
And Enter the required fields in the Register page and Signup
And Login to the registered EmailID and Logout
Then Login to Application with specific user6
When Click on User Settings and go to Lab Members
And Click Add New Member
And Invite the Existing Manager
Then Navigate to Nadamail and Confirm to Join
And Signin as Manager Login and Click on Join Lab
Then Click on User Settings->logout and Close the application