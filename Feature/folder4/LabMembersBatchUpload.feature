
@LabMembersBatchUpload_Action
Feature: Lab Members BatchUpload

Scenario: Lab Members BatchUpload
Given Login to Application with specific User Four
When Click on User Settings and go to Lab Members
And Click Add New Member
Then Click Batchupload from Owner Login and upload the Lab Members
Then Register the uploaded Lab Members and Verify First and Last Name
Then Click on User Settings->logout and Close the application
