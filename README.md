# webServiceREST

[![Java CI with Maven](https://github.com/onFilm/webServiceREST/actions/workflows/maven.yml/badge.svg)](https://github.com/onFilm/webServiceREST/actions/workflows/maven.yml)

# REST Assured  </br>
stands for Representational State Transfer. (It is sometimes spelled "ReST".) </br>
A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data.

### Maven Dependency
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.2.0</version>
    </dependency>

### Import statement
    import static io.restassured.RestAssured.*;

### Basic test

    @Test
    public void testREST() {

        String baseURL = "https://www.baseURL.com";
        RestAssured.baseURI=baseURL;

        given().
              header("key", "value").
              param("key", "value").
        when().
              get("resource").
        then().
              assertThat().
              statusCode(200).
        extract().
              response();
    }

# Rest API Framework
This is a Step-driven framework which is build on Rest assured Library. There are pre-defined steps_definition, so that
engineers can use it for developing the script.
Engineer needs to find the correct step to use it.

# Setup
* Pull the docker image ```docker pull mail2prajwal12/node-express-rest-api:latest```
* Clone this repository
* Open the repository in IntelliJ IDEA
* IntelliJ IDEA will build your project automatically.

# Needs to Follow
    * Create feature files in "features" folder under directory src/main/resources
    * Add your request payload, schema files in a custom folder under directory src/main/resources

#### Example
    └───src
        └───main
            ├───java
            │   ├───commonUtils
            │   │       ├───configUtils
            │   │       ├───constants
            │   │       └───context
            │   ├───steps_definition
            │   │       CustomStepDefinition.java
            │   └───testUtils
            │           TestRunner.java
            └───resources
                ├───features
                │       node-rest-api-tests.feature
                ├───payload
                │       dummy_payload.json
                └───schema    
                        dummy_schema.json

# Examples

#### Scenario: verify addition of two numbers
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

# To execute the script:

* Run the ```TestRunner.java``` for testUtils package
* or run as jar using ```java -jar -Dtags=@test ./target/webServiceREST-1.0-SNAPSHOT.jar```

# Extraction using Groovy path

Follow Gpath way of writing paths to extract data from response. Click here to learn
more http://docs.groovy-lang.org/latest/html/documentation/#_gpath

# Issues

- no unit tests on framework
- usage of local file paths throughout tests - won't work in CI/CD jar mode