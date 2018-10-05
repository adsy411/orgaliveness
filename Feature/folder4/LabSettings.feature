
@LabSettings_Action
Feature: Lab Settings

Scenario: Verify Lab Settings page
Given Login to Application
When Click on User Settings and go to Lab Settings
Then Edit Labname and Save then Verify the Lab Name in Lab Settings page and Header Drop Down
And Revert Previous Labname and Save
And Add description and Save
When Select different Timezone
And Enable Manager and Member as Approver
And Enable Manager and Member as Orderer
And Disable and Enable Research Module
And Clear the Description and Save
And Select previous Timezone
And Disable Manager and Member as Approver
And Disable Manager and Member as Orderer
And Disable and Enable Inventory Module
Then Click on User Settings->logout and Close the application






