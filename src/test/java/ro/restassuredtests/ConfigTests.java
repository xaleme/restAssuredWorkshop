package ro.restassuredtests;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ConfigTests {

    static final String BASE_URL = "https://api.github.com/";

    @Test
    void maxRedirectTest() {
        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(false));

        RestAssured.get(BASE_URL+ "repos/twitter/bootstrap")
                .then()
                .statusCode(equalTo(301));
    }
}
