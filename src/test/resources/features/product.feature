Feature: Product controller test

  @product
  Scenario: Create product and validate data
    Given use RequestBody and create new product "/api/myaccount/products"
   When get same product by id and validate data
    Then delete same product by id
