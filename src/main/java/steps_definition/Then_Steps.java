package steps_definition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonUtils.configUtils.PropertiesUtil;
import commonUtils.context.GlobalVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.List;

public class Then_Steps {

    private final GlobalVariables GlobalVariables;

    public Then_Steps(GlobalVariables GlobalVariables) {
        this.GlobalVariables = GlobalVariables;
    }

    @Given("^I verify the status code \"(.*)\"$")
    public void iVerifyStatusCode(String code) {
        GlobalVariables.response.then().assertThat().statusCode(Integer.parseInt(code));
    }

    @Given("^I verify response by extracting value using JsonPath \"[(.*)]\" with expected value \"[(.*)]\"$")
    public void iComparedValue(String jsonPath, String expectedValue) {
        Assert.assertEquals(GlobalVariables.response.jsonPath().getString(jsonPath), expectedValue);
    }

    @Given("^I verify response using JsonPath$")
    public void iComparedValue(List<List<String>> list) {
        JsonPath path = GlobalVariables.response.jsonPath();
        list.forEach(x -> Assert.assertEquals(path.getString(x.get(0)), x.get(1)));
    }

    @Given("^I verify response using json-schema-validator \"(.*)\"$")
    public void iPerformedSchemaValidator(String fileName) {
        GlobalVariables.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(PropertiesUtil.loadResource("schema/" + fileName)));
    }

    @Then("I store response field {string} to GlobalVariables.dynamicData map with key as {string}")
    public void iStoreResponseFieldToDynamicDataMap(String field, String variable) {
        GlobalVariables.dynamicData.put(variable, GlobalVariables.response.path(field));
    }

    @Then("I store response object {string} to GlobalVariables.dynamicData map with key as {string}")
    public void iStoreResponseObjectToDynamicDataMap(String object, String variable) throws JsonProcessingException {
        GlobalVariables.dynamicData.put(variable, new ObjectMapper().writeValueAsString(GlobalVariables.response.path(object)));
    }
}
