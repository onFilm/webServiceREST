package steps_definition;

import commonUtils.JsonParser;
import commonUtils.context.GlobalVariables;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;

import java.util.List;

public class Given_Steps {
    private final GlobalVariables GlobalVariables;

    public Given_Steps(GlobalVariables GlobalVariables) {
        this.GlobalVariables = GlobalVariables;
    }

    @Given("^I use the base uri key \"(.*)\" with base path \"(.*)\"$")
    public void iDefinedBasePath(String key, String basePath) {
        GlobalVariables.requestSpec = new RequestSpecBuilder().setBaseUri(GlobalVariables.config.getValue(key)).setBasePath(basePath);
    }

    @Given("^I define the endpoint \"(.*)\"$")
    public void iDefinedEndpoint(String endpoint) {
        GlobalVariables.requestSpec = GlobalVariables.requestSpec.setBaseUri(endpoint);
    }

    @Given("^I configure the endpoint using specs base url \"(.*)\" , port \"(.*)\" ,path \"(.*)\"$")
    public void iDefinedEndpointSpecs(String baseURI, String port, String basePath) {
        GlobalVariables.requestSpec = new RequestSpecBuilder().setBaseUri(baseURI).setPort(Integer.parseInt(port)).setBasePath(basePath);
    }

    @Given("^I store the header with key \"(.*)\" and value \"(.*)\"$")
    public void iStoredHeaders(String key, String value) {
        GlobalVariables.requestSpec.addHeader(key, value);
    }

    @Given("^I store the below headers$")
    public void iStoredHeaders(List<List<String>> list) {
        list.forEach(x -> GlobalVariables.requestSpec.addHeader(x.get(0), x.get(1)));
    }

    @Given("^I store the path parameter with key \"(.*)\" and value \"(.*)\"$")
    public void iStoredPathParameter(String key, String value) {
        GlobalVariables.requestSpec.addPathParam(key, value);
    }

    @Given("^I store the below path parameters$")
    public void iStoredPathParameter(List<List<String>> list) {
        list.forEach(x -> GlobalVariables.requestSpec.addPathParam(x.get(0), x.get(1)));
    }

    @Given("^I store the query parameter with key \"(.*)\" and value \"(.*)\"$")
    public void iStoredQueryParameter(String key, String value) {
        GlobalVariables.requestSpec.addQueryParam(key, value);
    }

    @Given("^I store the below query parameters$")
    public void iStoredQueryParameter(List<List<String>> list) {
        list.forEach(x -> GlobalVariables.requestSpec.addQueryParam(x.get(0), x.get(1)));
    }

    @Given("^I load the request body \"(.*)\"$")
    public void irequest(String payload) {
        GlobalVariables.requestSpec.setBody(payload);
        GlobalVariables.request = new StringBuffer(payload);
    }

    @Given("^I load the request body from \"(.*)\" file$")
    public void irequestJsonFile(String jsonFile) {
        GlobalVariables.jsonbody = JsonParser.jsonParser("payload/" + jsonFile);
        GlobalVariables.request = new StringBuffer(GlobalVariables.jsonbody.toString());
        GlobalVariables.requestSpec.setBody(GlobalVariables.jsonbody.toString());
    }

    @Given("^I modify the loaded jsonbody with below key-pair details$")
    public void irequestJsonFile(List<List<String>> list) {
        for (List<String> strings : list) {
            JsonParser.updateJson(GlobalVariables.jsonbody, strings.get(0), strings.get(1));
        }
        GlobalVariables.requestSpec.setBody(GlobalVariables.jsonbody.toString());
        GlobalVariables.request = new StringBuffer(GlobalVariables.jsonbody.toString());
    }

    @Given("I load below request body")
    public void iLoadrequest(String payload) {
        GlobalVariables.requestSpec.setBody(payload);
        GlobalVariables.request = new StringBuffer(payload);
    }

    @Given("I set path param {string} from GlobalVariables.dynamicData map with key {string} to the request specification")
    public void iSetPathParamFromMap(String pathParamKey, String pathParamValue) {
        GlobalVariables.requestSpec.addPathParam(pathParamKey, GlobalVariables.dynamicData.getOrDefault(pathParamValue, pathParamValue));
    }

    @Given("I set request body from GlobalVariables.dynamicData map with key {string} to the request specification")
    public void iSetRequestBodyFromMap(String requestBodyKey) {
        try {
            GlobalVariables.requestSpec.setBody(GlobalVariables.dynamicData.get(requestBodyKey).toString());
        } catch (NullPointerException nullPointerException) {
            throw new IllegalStateException("Specified key not found in GlobalVariables.dynamicData map");
        }
    }

}
