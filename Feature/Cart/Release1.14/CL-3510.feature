Feature: Add Products to Cart

Background:
Given Logged in user should have Cart Access

Scenario: Add Products to Cart tab  
Given User is logged in to the application 
When  User should be navigated to Request page
Then  Add products to cart tab should be present at the right of the application
And   Search box should be present
And   Please Search using Product Name / Numberto view Results from sigmaaldrich.com message should be displayed
 
Scenario: Update SKU 
Given User is logged in to the application 
And   User should be navigated to Request page
And   Add products to cart tab should be present at the right of the application
When  Update SKU of the product 
Then  SKU should be updated and receive an updated price

Scenario: Add product to cart functionality 
Given User is logged in to the application 
And   User should be navigated to Request page
And   Add products to cart tab should be present at the right of the application
When  Pressed the button for Add product to cart 
Then  Count of that product should be added to the cart
And   The number by the cart should increase by one
And   The product should then appear on the Cart Preview drop-down. 

Scenario: Visibility criteria 
Given User is logged in to the application who doesnt have cart access
When  User should be navigated to Request page
Then  Cart icon and its related data should be visible only to the users who have cart access 
 
Scenario: Visibility criteria for cart access
Given User is logged in to the application 
When  Navigated to Dashboard page 
Then  Cart tab should be visible
When  Navigated to all inventory pages
Then  Cart tab should be visible

Scenario: Tab expansion and collapse
Given User is logged in to the application 
When  Navigate to Request page 
Then  The tab should be expanded
When  Navigated to all other pages
Then  The tab should be collapsed

Scenario: Search functionality
Given User is logged in to the application 
And   Navigate to Request page 
When  Search for the product
Then  Should return autocomplete results, similar to what we have in Add Material
And   Also support a term search, if they don't select an autocomplete result

Scenario: Unique Catalog Numbers
Given User is logged in to the application 
And   Navigate to Request page 
When  Search for the product 
And   Add Product to cart
Then  Only unique catalog numbers should be displayed

Scenario: Price unavailable
Given User is logged in to the application 
And   Navigate to Request page 
When  Search for the product 
Then  Products with no price and which is unavailable is not shown

Scenario: Top <x> results
Given User is logged in to the application 
And   Navigate to Request page 
When  Search for the product 
And   Add  3 to 4 products to cart
Then  Top <x> results should be displayed with a Load More function to show the next x results












