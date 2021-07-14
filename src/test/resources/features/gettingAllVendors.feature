@smoke
Feature: Add a new product for an existing vendor

@AddingNewProduct
Scenario: Adding new product to an existing vendor
  Given user has chosen a vendor
  When user adds a product to chosen existing vendor
  Then user should see the added product



