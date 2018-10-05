Feature: Implement Export to Doc feature for protocol
	As a lab user,I should be able to export protocol template in Word format

	Background:
	Given User is logged in to the application and is in protocol template page
	
	Scenario: Export protocol template in doc format
		Given User creates a protocol template
		When  User clicks on protocol template name link from protocol template list
		And   Enters protocol template details like description, Steps, Notes
		And   Clicks on Download protocol template button
		And   Selects Export as Word document option
		Then  Protocol template should get downloaded in .docx format
		And   Protocol template details like protocol template name,Document generated date,Protocol template Document generated user,Steps,Notes should display in exported document successfully