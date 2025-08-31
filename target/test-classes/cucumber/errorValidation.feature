
Feature: verify login page
	@smoke
  Scenario Outline: I perform negative test cases
   Given I landed on E-commerce website
   When I logged in with username <name> and password <pwd>
   Then I verify "Incorrect email or password." message is displayed

Examples: 
      |        name                    |    pwd         |
      | padmanabh.lakshmaiah@gmail.com | Pady@9731      |
