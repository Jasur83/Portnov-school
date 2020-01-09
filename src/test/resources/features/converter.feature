@converter
Feature: Converter functions

    Background:
      Given I go to "converter" page

    @converter1
      Scenario: Validate Fahrenheit to Celsius
      When I click on "Temperature"
      And I set "Fahrenheit" to "Celsius"
      Then I enter into From field "54" and verify "12.2" result

   @converter2
      Scenario: Validate Pound to Kilogram
      When I click on "Weight"
      And I set "Pound" to "Kilogram"
      Then I enter into From field "170" and verify "77" result

   @converter3
      Scenario Outline: Validate from one Unit to another Unit
      When I click on "<tab>"
      And I set "<from_unit>" to "<to_unit>"
      Then I enter into From field "<from_value>" and verify "<expected_value>" result
      Examples:
       | tab | from_unit | to_unit | from_value| expected_value |
       | Temperature | Fahrenheit | Celsius | 54 | 12.2 |
       | Weight | Pound | Kilogram | 170 | 77 |
       | Length | Mile | Kilometer | 50 | 80.4 |