Feature: Cart Icon, Cart Preview, and prompt for affiliation

Scenario: Cart icon on top bar  
Given User is logged in to the application 
And   Logged in person should have access to cart
When  Landing to home page 
Then  User should be able to see the cart icon in the top bar

Scenario: Change count  
Given User is logged in to the application as lab owner
And   Cart icon should appear on the top bar
When  Clicked on the cart icon and change the count
Then  User should be able to change the count successfully

Scenario: Cart link
Given User is logged in to the application as lab owner
And   Cart icon should appear on the top bar
When  Clicked on the cart icon and then on the View cart link
Then  Link should be able to bring the user to cart page

Scenario: Lab is not affiliated
Given User is logged in to the application as lab owner
When  Verify the presence of active cart icon
Then  Cart icon should be available to the lab owner even not affiliated

Scenario: Lab is not affiliated-Lab Manager/Member
Given User is logged in to the application as lab manager/member
When  Verify the cart icon
Then  Cart icon should appear gray
And   Message should be displayed on clicking on the cart"To use cart please Click hereto Affiliate your profile fromSIGMA-ALDRICHIf you don't have this information,call Customer Support at1 (800) 325-3010"
And   Clicking on 'Here' should bring us to the Lab Settings page in focus with Affiliation details.







