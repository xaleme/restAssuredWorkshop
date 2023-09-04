package ro.restassuredtests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParameterizedTests {

    static final String SEARCH_REPOS = "https://api.github.com/search/repositories";

    @DataProvider
    public Object[][] queryParams() {
        return new Object[][] {
                {Map.of("q", "java", "per_page", "1"), 1},
                {Map.of("q", "python", "per_page", "2"), 2}
        };
    }

    @Test(dataProvider = "queryParams")
    void dataDrivenTests(Map<String,String> params, int expectedRez) {

        var json = RestAssured
                .given()
                .params(params)
                .get(SEARCH_REPOS).jsonPath();

        Assert.assertEquals(json.getInt("items.size()"), expectedRez);
    }
}
