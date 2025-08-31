
Feature: purchase the order from e-commerce website

 Background:
 Given I landed on E-commerce website
 @E2E
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <name> and password <pwd>
    When I add product <prodName> name to cart
    And checkout <prodName> and submit the order
    Then I verify "Thankyou for the order." the message is displayed on confirmation page

Examples: 
      |        name                    |    pwd         |    prodName     |
      | padmanabh.lakshmaiah@gmail.com | Pady@973100    |    ZARA COAT 3  |
     
