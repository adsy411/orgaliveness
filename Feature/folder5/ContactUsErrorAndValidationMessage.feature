
@ContactUsErrorAndValidationMessage_Action
Feature: ContactUsErrorAndValidationMessage

Scenario: Verify Contact Us Error and Validation Message
Given Open Application 
When Click on Home page Contact Us
And Click on Send and Verify the Validation messages in Contact us popup
And Enter Invalid Email address and Verify the Error message in Contact us popup
Then Close the Application