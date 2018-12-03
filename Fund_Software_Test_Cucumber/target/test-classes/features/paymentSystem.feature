Feature: Payment system verification

  I want to fill in a form and reset

  Scenario:  Reset form
    Given I am a user trying to process a payment
    When I fill in the form
    And click on the clear button
    Then the form data should be cleared


  Scenario Outline: Missing information
      Given I am a user trying to process a payment
      When I submit a form with all data except "<fieldname>"
      Then I should be told that "<fieldname>" is required

      Examples:
      | fieldname |
      |Name       |
      |Address    |
      |CardNumber |
      |ExpiryDate |
      |CVVCode    |
      |Amount     |


  Scenario:  Valid process
    Given I am a user trying to process a payment
    When I submit correct details
    Then I should be told that the payment was successful
