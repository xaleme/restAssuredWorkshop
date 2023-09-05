package ro.restassuredtests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecTests {

    static final String BASE_URL =  "https://api.github.com";

    @BeforeClass
    void setup(){
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .build();
    }

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .build();


    @Test
    void testWithSpecOne() {
        RestAssured.get(BASE_URL+ "/invalid")
                .then()
                .spec(responseSpecification);
            // taking from the spec declared above
    }


    @Test
    void testWithSpecTwo() {
        RestAssured.get(BASE_URL+ "invalid");
                //then expected is taken from before class
    }

    @Test
    void testReqSpec(){
        RestAssured.given()
                .spec(requestSpecification)
                .log().all() // logs for request
                .then()
                .log().headers()  // logs for response
                .statusCode(200);
    }
}
