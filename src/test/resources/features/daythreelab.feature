@quote_pre
Feature: Quote Predefined

  @quote_pre1
  Scenario: Responsive behavior
    Given I open url "https://skryabin.com/market/quote.html"
    When I resize window to 1024 and 1000
    Then element with xpath "//b[@id='location']" should be displayed
    And element with xpath "//b[@id='currentDate']" should be displayed
    And element with xpath "//b[@id='currentTime']" should be displayed
    When I resize window to 800 and 1000
    Then element with xpath "//b[@id='location']" should be displayed
    And element with xpath "//b[@id='currentDate']" should be displayed
    And element with xpath "//b[@id='currentTime']" should be displayed
    When I resize window to 600 and 1000
    Then element with xpath "//b[@id='location']" should not be displayed
    And element with xpath "//b[@id='currentDate']" should not be displayed
    And element with xpath "//b[@id='currentTime']" should not be displayed


  @quote_pre2
  Scenario: Username field length
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "a" into element with xpath "//input[@name='username']"
    And I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//label[@id='username-error']" should contain text "2 characters"
    When I type "b" into element with xpath "//input[@name='username']"
    Then element with xpath "//label[@id='username-error']" should not be displayed

  @quote_pre3
  Scenario: Email field validation
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "jasur838383@@mail.ru" into element with xpath "//input[@name='email']"
    And I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//label[@id='email-error']" should contain text "Please enter"
    And I clear element with xpath "//input[@name='email']"
    And I type "jasur838383@mail.ru" into element with xpath "//input[@name='email']"
    Then element with xpath "//label[@id='email-error']" should not be displayed

  @quote_pre4
  Scenario: Password set of fields validation
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "Jasu" into element with xpath "//input[@id='password']"
    Then I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//label[@id='password-error']" should contain text "5 characters"
    And element with xpath "//label[@id='confirmPassword-error']" should contain text "required"
    When I clear element with xpath "//input[@type='password']"
    And I type "Jasur" into element with xpath "//input[@id='password']"
    Then element with xpath "//label[@id='password-error']" should not be displayed
    But element with xpath "//label[@id='confirmPassword-error']" should contain text "required"
    When I clear element with xpath "//input[@id='password']"
    Then element with xpath "//input[@id='confirmPassword']" should be disabled

  @quote_pre5
  Scenario: Name field validation
    Given I open url "https://skryabin.com/market/quote.html"
    When I click on element with xpath "//input[@id='name']"
    Then element with xpath "//div[contains(@class,'dialog ')]" should be displayed
    When I type "Jasur" into element with xpath "//input[@id='firstName']"
    And I type "NMN" into element with xpath "//input[@id='middleName']"
    And I type "Botirov" into element with xpath "//input[@id='lastName']"
    And I click on element using JavaScript with xpath "//span[contains(text(),'Save')]"
    Then element with xpath "//input[@id='name']" should be displayed

  @quote_pre6
  Scenario: Accepting Privacy Policy validation
    Given I open url "https://skryabin.com/market/quote.html"
    When I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should contain text "Must check"
    And I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
    Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should not be displayed

  @quote_pre7
  Scenario: Non-required field validation
    #Phone Number
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "012345678910111213141" into element with xpath "//input[@name='phone']"
    And I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//label[@id='phone-error']" should contain text "20 characters"

    #Country of Origin
    When I click on element with xpath "//select[@name='countryOfOrigin']"
    And I click on element with xpath "//option[contains(text(),'Uzbekistan')]"
    Then element with xpath "//select[@name='countryOfOrigin']" should contain text "Uzbekistan"

    #Gender
    When I click on element with xpath "//input[@value='male']"
    Then element with xpath "//input[@value='male']" should be selected
    When I click on element with xpath "//input[@value='female']"
    Then element with xpath "//input[@value='male']" should not be selected

    #Allowed to Contact?
    When I click on element with xpath "//*[contains(text(),'I allow')]"
    Then element with xpath "//input[@name='allowedToContact']" should be selected

    #Address
    When I type "Colorado Springs" into element with xpath "//*[@id='address']"
    And I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then element with xpath "//*[@id='address']" should be displayed

    #Car Make
    When I click on element with xpath "//option[contains(text(),'BMW')]"
    Then element with xpath "//option[contains(text(),'BMW')]" should contain text "W"

    #Third party agreement
    When I click on element using JavaScript with xpath "//button[@id='thirdPartyButton']"
    Then I wait for 3 sec
    Then I accept alert

    #Date of Birth
    When I click on element with xpath "//input[@id='dateOfBirth']"
    Then element with xpath "//div[@id='ui-datepicker-div']" should be displayed
    When I click on element with xpath "//select[@class='ui-datepicker-month']"
    And I click on element with xpath "//option[contains(text(),'Jul')]"
    And I click on element with xpath "//option[contains(text(),'1983')]"
    And I click on element with xpath "//a[contains(text(),'31')]"
    Then element with xpath "//input[@id='dateOfBirth']" should be displayed

    #iFrame
    And I switch to iframe with xpath "//iframe[@name='additionalInfo']"
    And I type "John Doe" into element with xpath "//input[@id='contactPersonName']"
    And I type "12345678" into element with xpath "//input[@id='contactPersonPhone']"
    And I switch to default content


  @quote_pre8
  Scenario: Submit the form and verify the data
   Given I open url "https://skryabin.com/market/quote.html"
    When I click on element with xpath "//input[@id='name']"
    Then element with xpath "//div[contains(@class,'dialog ')]" should be displayed
    When I type "Jasur" into element with xpath "//input[@id='firstName']"
    And I type "NMN" into element with xpath "//input[@id='middleName']"
    And I type "Botirov" into element with xpath "//input[@id='lastName']"
    And I click on element using JavaScript with xpath "//span[contains(text(),'Save')]"
    When I type "abc" into element with xpath "//input[@name='username']"
    And I type "jasur838383@mail.ru" into element with xpath "//input[@name='email']"
    And I type "Jasur" into element with xpath "//input[@id='password']"
    And I type "Jasur" into element with xpath "//input[@id='confirmPassword']"
    And I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
    When I click on element using JavaScript with xpath "//button[@id='formSubmit']"
    Then I wait for 4 sec
    Then element with xpath "//div[@class='well form-container container-fluid']" should be present
    Then element with xpath "//b[@name='password']" should not contain text "Jasur"

    
  #Simple scenario for the site of my choice  
  @quote_pre9
  Scenario: MailRu email field validation
    Given I open url "https://mail.ru/"
    Then element with xpath "//div[@class='mailbox__body']" should be displayed
    When I type "жасур838383" into element with xpath "//input[@id='mailbox:login']"
    And I click on element with xpath "//input[@class='o-control']"
    Then element with xpath "//div[@id='mailbox:error']" should be present
    When I clear element with xpath "//input[@id='mailbox:login']"
    Then I wait for 3 sec
    And I type "jasur838383" into element with xpath "//input[@id='mailbox:login']"
    And I click on element with xpath "//input[@class='o-control']"
    Then I wait for 3 sec
    Then element with xpath "//input[@id='mailbox:password']" should be displayed


  @quote_pre10
  Scenario: Swap values in a Map
    Given I print values in the map
