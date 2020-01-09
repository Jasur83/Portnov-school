@form
  Feature: Form scenarios

    @form1
    Scenario: Submit and verify page object form
      Given I open sample page
      When I fill out all page object fields
      And I submit page object form
      Then I verify all page object fields

    @form2
    Scenario: Validate ZIP code page object
      Given I go to usps page object
      When I go to Lookup ZIP page object by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state page object fields
      Then I validate "94022" zip code exists in the result page object

    @form3
    Scenario: Calculate price logic page object
      Given I go to usps page object
      When I go to Calculate Price page object
      And I select "United Kingdom" with "Postcard" shape and I define "2" quantity page object
      Then I calculate the price and validate cost is "$2.30" page object