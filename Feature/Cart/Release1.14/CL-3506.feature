Feature: Affiliation - Settings Page

Background:
Given Logged in user should be Lab Owner

Scenario: Account Affiliation  
Given User is logged in to the application 
And   Navigate to my settings page
When  Navigate to Affiliate Your profile section
Then  User should be able to affiliate account using Customer Number/order number
And   Customer Pin/Invoice or packing slip number
When  Click on Link Your Account button
Then  User should be able to affiliate successfully
And   Should remain on the same page after affiliation

Scenario: Account Affiliation  
Given User is logged in to the application as Lab manager/member
When  Navigate to my settings page
Then  Affiliate your profile section should not be visible to both lab manager and lab member

Scenario: Onboarding Account Affiliation  
Given User is logged in to the application after registration
When  Navigated to Onboarding page
Then  Affiliate your profile section should be available
And   User should be able to affiliate account by providing Customer Number/order number
And   Customer Pin/Invoice or packing slip number
When  Click on Link Your Account button
Then  User should be able to affiliate successfully
And   Should remain on the same page after affiliation


