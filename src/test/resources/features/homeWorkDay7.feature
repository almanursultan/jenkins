
  Feature: Test all sellers

    @seller3

    Scenario: Create a new seller and verify its details

      Given All necessary variables are initialized "/api/myaccount/products"

      Then A new seller is created with name, email, address, and phone number

      Then Get the seller ID, and validate it
