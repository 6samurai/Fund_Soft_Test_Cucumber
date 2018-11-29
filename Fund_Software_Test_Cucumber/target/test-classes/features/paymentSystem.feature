Feature: Search functionality

  I want to fill in a form and reset

    Scenario:  Reset form
      Given I am a user trying to process a payment
      When I fill in the form
      And click on the clear button
      Then the form data should be cleared