package ro.restassuredtests;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PathParamsTests {

    static final String BASE_URL = "https://api.github.com/repos";

    @Test
    void withoutParamHardcoded(){
        RestAssured.given()
                .header("Authorization", "Bearer "+"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")
                .get(BASE_URL+ "/xaleme/restAssuredWorkshop")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(686903783));
    }

    @Test
    void withParamHardcoded(){
        RestAssured.given()
                .header("Authorization", "Bearer "+"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")
                .pathParam("user", "xaleme")
                .get(BASE_URL+ "/{user}/restAssuredWorkshop")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(686903783));
    }

    @Test
    void withParam(){
        RestAssured
                .given()
                .param("per_page", "1")
                .param("q", "java")
                .get("https://api.github.com/search/repositories")
                .then()
                .statusCode(200);
    }
}
