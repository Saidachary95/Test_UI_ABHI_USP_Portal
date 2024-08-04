Feature: Mozart login Module.

  Background: 
    When Agent launched the browser and entered url.
    Then Verify agent landed on "Aditya Birla Health Insurance" page.
    And Agent entered "Username".
      | Username                      |
      | pratik@adityabirlacapital.com |
    And Agent entered "Password".
      | Password |
      |    12345 |
    And Agent clicked on button "login-button".

  Scenario: TC_001_Punch policy for Active Health product with new bussines for Enhanced, Familyfloater, relation as self for tenure 1 year.
    Then User selected productItem as "Activ Health Enhanced".
    #Then Verify the "Product Variant".
    #And Verify the "Activ Health Plan".
    And User selected "Policy Type" for test case "TC_002".
    And User entered "Date of Birth" for test case "TC_002".
    And User selected "Gender" for test case "TC_002".
    And User selected "Select Policy Type" for test case "TC_002".
    And User clicked on "Self" for test case "TC_002".
    
	
	