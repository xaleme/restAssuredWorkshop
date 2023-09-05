package ro.restassuredtests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import java.util.Map;

public class ResponseTests {

    public static final String RATE_LIMIT = "https://api.github.com/rate_limit";


    @Test
    public void jsonPathReturnsMap() {
        RestAssured.baseURI = "https://api.github.com/rate_limit";

        Response response = RestAssured.get(RATE_LIMIT);

        ResponseBody<?> body = response.body();
        JsonPath jsonPath = body.jsonPath();

        Map<String, String> fullJson = jsonPath.get();
        Map<String, String> subMap  = jsonPath.get("wrong.path"); // will return NULL
        Map<String, String> subMap2 = jsonPath.getMap("resources.core");

        int valueLimit = jsonPath.get("resources.core.limit");

        System.out.println(fullJson);
        System.out.println(subMap2);
    }
}
