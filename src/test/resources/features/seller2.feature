Feature: get list of direct Sellers
  
  #//https://backend.cashwise.us/api/myaccount/sellers/all

  @seller2
  Scenario: get list of Sellers, print their names, validate they are not Null
    Given a path "/api/myaccount/sellers/all"
    When get their names, print and validate they are not Null