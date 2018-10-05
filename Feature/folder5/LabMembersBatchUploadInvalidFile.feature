
@LabMembersBatchUploadInvalidFile_Action
Feature: LabMembersBatchUploadInvalidFile

Scenario: Verify Lab Members Batch Upload Invalid File Message
Given Login to Application with specific user2
When Click on User Settings and go to Lab Members
And Click Add New Member
And Click Batchupload and upload Invalid File then verify the Error message
Then Click on User Settings->logout and Close the application