Feature: This feature written to test the apis developed in node-rest-api

  @sum @addition @test
  Scenario: verify addition of two numbers
    Given I define the endpoint "http://localhost:3000"
    And I store the below query parameters
      | num1 | 5  |
      | num2 | 10 |
    When I execute the "calculator/addition" endpoint with GET request
    Then I verify the status code "200"
    And I verify response using JsonPath
      | result | 15 |

  @sub @subtraction @test
  Scenario: verify subtraction of two numbers
    Given I define the endpoint "http://localhost:3000"
    And I store the below query parameters
      | num1 | 5  |
      | num2 | 10 |
    When I execute the "calculator/subtraction" endpoint with GET request
    Then I verify the status code "200"
    And I verify response using JsonPath
      | result | -5 |

  @mul @multiplication @test
  Scenario: verify multiplication of two numbers
    Given I define the endpoint "http://localhost:3000"
    And I store the below query parameters
      | num1 | 5  |
      | num2 | 10 |
    When I execute the "calculator/multiplication" endpoint with GET request
    Then I verify the status code "200"
    And I verify response using JsonPath
      | result | 50 |

  @del @deletion @test
  Scenario: verify division of two numbers
    Given I define the endpoint "http://localhost:3000"
    And I store the below query parameters
      | num1 | 5  |
      | num2 | 10 |
    When I execute the "calculator/division" endpoint with GET request
    Then I verify the status code "200"
    And I verify response using JsonPath
      | result | 0.5 |