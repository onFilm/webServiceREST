package commonUtils.context;

import commonUtils.SpecBuilder;
import commonUtils.configUtils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GlobalVariables {
    public RequestSpecBuilder requestSpec;
    public ResponseSpecBuilder responseSpec;
    public Response response;
    public StringBuffer request;
    public Header header;
    public Headers headers;
    public Map<String, Object> requestHeaders;
    public ConfigLoader config;
    public Map<String, Object> requestHeader;
    public JSONObject jsonbody;
    public Map<String, Object> dynamicData;

    public GlobalVariables() {
        config = ConfigLoader.getInstance();
        requestSpec = SpecBuilder.getRequestSpecBuilder();
        headers = new Headers();
        requestHeader = new HashMap<>();
        request = new StringBuffer();
        jsonbody = new JSONObject();
        dynamicData = new HashMap<>();
    }
}
