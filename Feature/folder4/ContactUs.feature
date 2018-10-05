
@ContactUs_Action
Feature: Contact Us

Scenario: Verify Contact Us Link
Given Open Application
When Click on Home page Contact Us
Then Enter the required fields and Click on Send
When Click on Login page Contact Us
Then Enter the required fields and Click on Send
When Login to Application and click on Contact Us
Then Enter the Feedback and click on Submit Feedback
Then Click on User Settings->logout and Close the application
