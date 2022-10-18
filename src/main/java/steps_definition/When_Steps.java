package steps_definition;

import commonUtils.context.GlobalVariables;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class When_Steps extends GlobalVariables {

    private final GlobalVariables GlobalVariables;

    public When_Steps(GlobalVariables GlobalVariables) {
        this.GlobalVariables = GlobalVariables;
    }

    @When("^I execute the endpoint with POST request$")
    public void iExecutedServices() {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().post().then().extract().response();
    }

    @When("^I execute the \"(.*)\" endpoint with GET request$")
    public void iExecutedGetServices(String apiName) {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().get(apiName).then().extract().response();
    }

    @When("^I execute the \"(.*)\" endpoint with POST request$")
    public void iExecutedPOSTServices(String apiName) {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().post(apiName).then().extract().response();
    }

    @When("^I execute the endpoint with PUT request$")
    public void iExecutedPUTServices() {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().put().then().extract().response();
    }

    @When("^I execute the endpoint with GET request$")
    public void iExecuteGETServices() {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().get().then().extract().response();
    }

    @When("^I execute the \"(.*)\" endpoint with PUT request$")
    public void iExecutedPUTServices(String apiName) {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().put(apiName).then().extract().response();
    }

    @When("^I execute the endpoint with DELETE request$")
    public void iExecutedDeleteServices() {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().delete().then().extract().response();
    }

    @When("^I execute the \"(.*)\" endpoint with DELETE request$")
    public void iExecutedDeleteServices(String apiName) {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().delete(apiName).then().extract().response();
    }

    @When("I execute the {string} endpoint with PATCH request")
    public void iExecutedTheEndpointWithPATCHRequestWithPK(String apiName) {
        GlobalVariables.response = RestAssured.given().spec(GlobalVariables.requestSpec.build()).when().patch(apiName).then().extract().response();
    }
}
