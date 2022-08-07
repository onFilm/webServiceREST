package commonUtils.context;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public class GlobalVariables {
    public RequestSpecBuilder requestSpec;
    public ResponseSpecBuilder responseSpec;
    public Response response;
    public StringBuffer requestPayload = new StringBuffer();
    public Header header;
    public Map<String,Object> requestHeaders;
}
