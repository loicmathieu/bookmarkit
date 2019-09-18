package fr.loicmathieu.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BookmarkStep2Test {
    @Test
    public void step2() {
        RestAssured.defaultParser = Parser.JSON;

        //check that a summary has been added to the default OpenAPI
        given().accept("application/json")
                .when().get("/openapi")
                .then()
                .statusCode(200)
                .body("paths.'/bookmarks'.get.summary", CoreMatchers.is(CoreMatchers.notNullValue()));

        //check that metrics exist for each operations
        given().accept("application/json")
                .when().get("/metrics/application")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(10));
    }
}
