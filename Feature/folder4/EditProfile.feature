
@EditProfile_Action
Feature: Edit Profile

Scenario: Edit the Profile and Save
Given Login to Application
When Click on User Settings and go to My Profile
Then Edit the Username and Save then Verify the Username from My Profile page and Header Drop Down
Then Edit the Profile and Save
Then Click on User Settings->logout and Close the application
