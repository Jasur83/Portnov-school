@git
Feature: Smoke steps

  @git1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "(//input[@name='btnK'])[1]"
    Then I wait for element with xpath "//*[@id='res']" to be present
    Then element with xpath "//*[@id='res']" should contain text "Cucumber"

  @git2
  Scenario: Predefined steps for Bing
    Given I open url "https://www.bingmn.com/"
    Then I should see page title contains "Bing"
    Then element with xpath "//input[@id='sb_form_q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='sb_form_q']"
    Then I click on element with xpath "//input[@id='sb_form_go']"
    Then I wait for element with xpath "//h2[1]" to be present
    Then element with xpath "//ol[@id='b_results']" should contain text "cucumber"