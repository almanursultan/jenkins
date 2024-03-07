Feature: Test bank Accounts
  @bankaccount

  Scenario Outline: Create many bank accounts
    Given  Create bank accounts "/api/myaccount/bankaccount" Data: "<type_of_pay>" and "<bank_account_name>" and "<description>" and "<balance>"
    When Get same bank accounts by id and validate "<type_of_pay>" and "<bank_account_name>" and "<description>" and <balance>
    Then Delete bank accounts by id
    Examples:
      | bank_account_name | description| type_of_pay|balance|
      | first new Bank    |Financial LLC| CASH      |100 |
      | second new Bank    |Financial LLC| CASH      |335250 |
      | third new Bank    |Financial LLC| CASH      |1000 |
      | forth new Bank    |Financial LLC| CASH      |7076 |
      | fifth new Bank    |Financial LLC| CASH      |104640 |
      | sixth new Bank    |Financial LLC| CASH      |1075 |
