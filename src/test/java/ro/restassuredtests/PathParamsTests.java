package ro.restassuredtests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PathParamsTests {

    static final String BASE_URL = "https://api.github.com/";

    @Test
    void withoutParamHardcoded(){
        RestAssured.get("");
    }
}
