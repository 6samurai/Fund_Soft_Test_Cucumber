Feature: Payment system verification

This feature contains multiple scenarios to be implemented on the Payment System

  Scenario: 1 - Valid process
    Given I am a user trying to process a payment
    When I submit correct details
    Then I should be told that the payment was successful

  Scenario Outline: 2 - Missing information
    Given I am a user trying to process a payment
    When I submit a form with all data except "<fieldname>"
    Then I should be told that "<fieldname>" is required

    Examples:
      | fieldname  |
      | Name       |
      | Address    |
      | CardNumber |
      | ExpiryDate |
      | CVVCode    |
      | Amount     |

  Scenario: 3 - Invalid data
    Given I am a user trying to process a payment
    When I submit a form with any invalid that which the processing system rejects
    Then I should be told that there was an error processing my transaction

  Scenario Outline: 4 - Valid transactions with different card types
    Given I am a user trying to process a payment
    When I submit correct details using a "<card-type>" card
    Then I should be told that the payment was successful

    Examples:
      | card-type        |
      | Mastercard       |
      | VISA             |
      | American Express |

  Scenario: 5 - Reset form
    Given I am a user trying to process a payment
    When I fill in the form
    And click on the clear button
    Then the form data should be cleared



