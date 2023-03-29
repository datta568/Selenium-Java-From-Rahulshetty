@tag
Feature: Error Validation
 
  @errorValidation
  Scenario Outline: Error Validation for Login Page
    Given I landed on the Ecommerce page
   	When Login to application with username <userName> and password <password>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | userName           | password   |
      | datta568@gmail.com | Password12 |
