
@LabMembersErrorAndValidationMessage_Action
Feature: LabMembersErrorAndValidationMessage

Scenario: Verify the Lab Members Error and Validation Message
Given Login to Application
When Click on User Settings and go to Lab Members
And Click Add New Member
And Verify Validation and Error messages in Add New Member
Then Click on User Settings->logout and Close the application