
@VerifyHomeDashboardLinks_Action
Feature: Home Dashboard Links

Scenario: Verify Home Dashboard Links
Given Login to Application
When Verify Start Research Project Link
When Verify Increase Storage Link
When Verify Add New Material Icon
When Verify View in Inventory Link
When Verify Lab Activity Icon
When Verify View Experiments Link
Then Click on User Settings->logout and Close the application
