package ro.restassuredtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


public class FirstTestWithRest {

    static final String BASE_URL = "https://api.github.com";
    @Test
     void firstTest(){
        RestAssured.get("https://api.github.com")
                .then()
                .statusCode(200);
    }

    @Test
    void peek() {
        Response response = RestAssured.get(BASE_URL)
                .peek();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }

    @Test
    void getHeader() {
        Response response = RestAssured.get(BASE_URL);

        Assert.assertEquals(response.getHeader("server"), "GitHub.com");
    }

    @Test
    void basicValidationExample(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("x-ratelimit-limit", "60");
    }

    @Test
    void simpleHamcrestMatcher(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(lessThan(300))
                .header("server", containsStringIgnoringCase("github.com"))
                .time(lessThan(2L), TimeUnit.SECONDS)
                .header("etag", not(emptyString()));
    }
}
