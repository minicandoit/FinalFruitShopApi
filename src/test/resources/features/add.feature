
@new
Feature: add new product

  Scenario:
    When create a vendor for the product
    When user add a new product
    Then user is able to verify the new
  Scenario: user is able to update a name and a price of the fruit

    When user is update a  name and a price
    Then user is able to verify the updated name and a price
    Then list the product
