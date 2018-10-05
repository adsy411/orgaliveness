Feature: Export experiment in pdf Format
		 As a lab user,He should be able to export the experiment to be exported in pdf format.

Scenario: Export experiment in pdf format
	Given User is logged into the application as lab owner
	Given Project and experiment is created in the Lab
	When  User navigates to Research Hub -> Projects
	And   Click on the project name link and navigates to project main page
	And   Click on the experiment name link and navigates to experiment main page
	And   Clicks on Download experiment report and clicks on Export as PDF option
	Then  A Pdf file should be downloaded
	And   Downloaded Pdf should contains details such as Experiment Title,Experiment Description,Materials added to experiment,Experiment steps, Experiment notes(if associated with steps)