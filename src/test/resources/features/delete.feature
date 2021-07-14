@smoke
Feature: delete the product
@delete
  Scenario: user is able to delete the product

    When user create a product
    And user can delete the product
    Then verify the product is deleted