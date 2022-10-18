package commonUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(String url) {
        return new RequestSpecBuilder().
                setBaseUri(url).
                build();
    }

    public static RequestSpecBuilder getRequestSpecBuilder(String url) {
        return new RequestSpecBuilder().setBaseUri(url).log(LogDetail.ALL);
    }

    public static RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder().log(LogDetail.ALL);
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().log(LogDetail.STATUS).build();
    }
}