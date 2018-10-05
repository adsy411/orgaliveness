Feature: Public protocol should be available to whole organisation, not only for lab

# Automation scenario
Scenario: Public protocol should be available to whole organisation
Given User is logged in to the application as lab owner and is in protocol templates list page
When  User clicks on Create new button and enters protocol details
And   Clicks on Create button
Then  Protocol should be created successfully
And   Click on User Settings->logout and Close the application
And   User logs into another lab of same organisation
And   Navigates to protocol templates list page
Then  Protocol created from original lab should be displayed in the protocol templates list
And   Click on User Settings->logout and Close the application

# Scenario not applicable as of now (Cannot be automatable)
#Scenario: Public protocol should be available to whole organization but not for another organization with same credentials
#Given User is logged in to the application as lab owner and is in protocol templates list page
#When  User clicks on Create new button and enters protocol details
#And   Clicks on Create button
#Then  Protocol should be created successfully
#And   Click on User Settings->logout and Close the application
#And   User logs into another lab of different organization
#And   Navigates to protocol templates list page
#Then  Protocol created from original lab should not be displayed in the protocol templates list of another organisation
#And   Click on User Settings->logout and Close the application