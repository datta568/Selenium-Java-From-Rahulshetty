@tag
Feature: To purchase an item from Ecommerce website
  I want to use this template for my feature file

  Background: 
    Given I landed on the Ecommerce page

  @regression
  Scenario Outline: Title of your scenario outline
    Given Login to application with username <userName> and password <password>
    When I add product <productName> to cart
    And checkout <prodcutName> from cart and submit the order
    Then "THANKYOU FOR THE ORDER." message will be displayed in the confirmation page

    Examples: 
      | userName           | password    | productName |
      | datta568@gmail.com | Password123 | ZARA COAT 3 |
