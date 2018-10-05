Feature: Include Organization in Shipping/Billing Address Modal

Background:
Given Shipping and Billing addresses available in cart page

Scenario: Include Organization in Shipping Address Modal 
Given User is logged in to the application
And   User should be navigated to cart checkout page
When  Click on edit shipping address 
And   All the shipping address fields should be displayed
Then  Organization name field should also be dsiplayed along with other fields
And   Organization name should not be editable
And   Same organization name should be available in the cart page as well

Scenario: Include Organization in Billing Address Modal 
Given User is logged in to the application
And   User should be navigated to cart checkout page
When  Click on edit billing address 
And   All the billing address fields should be displayed
Then  Organization name field should also be dsiplayed along with other fields
And   Organization name should not be editable
And   Same organization name should be available in the cart page as well


