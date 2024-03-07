Feature: product price

  Scenario: verify product prices are within range of 1 to 10000
    Given I set up connection to database
    And I retrieve all products prices
    Then verify prices are between 1 and 10000