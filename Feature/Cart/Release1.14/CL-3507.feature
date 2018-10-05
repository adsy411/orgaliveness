Feature: Affiliation - Onboarding Flow

Background:
Given User should be a lab owner

Scenario: Affiliation during onboaring  
Given User is logged in to the application as lab owner
And   In Affiliate your profile tab, provide customer number and order number
And   Provide customer pin and invoice number
When  Click on Link your Account 
Then   User should be affiliated and should be able to see the cart icon

Scenario: Affiliation during onboaring  
Given User is logged in to the application as lab owner
And   In Affiliate your profile tab, provide customer number and order number
And   Provide customer pin and packing slip number
When  Click on Link your Account 
Then  User should be affiliated and should be able to see the cart icon

Scenario: Affiliating as a Lab Manager/Lab Member
Given User should be logged in as a lab manager/lab member
When  Landed to onboarding page
Then  Affiliate your profile section should not be available
And   Other Onboarding activities should be available





