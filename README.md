# webServiceREST

# REST </br>
stands for Representational State Transfer. (It is sometimes spelled "ReST".) </br>
A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data.

### Maven Dependency
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.1.1</version>
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
    
