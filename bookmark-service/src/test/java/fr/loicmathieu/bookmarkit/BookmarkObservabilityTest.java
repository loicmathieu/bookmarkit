package fr.loicmathieu.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BookmarkObservabilityTest {

    @Test
    @DisplayName("check that metrics exist for each operation")
    void metricsTest() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/metrics/application")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(10));
    }

    @Test
    @DisplayName("check that a summary has been added to the default OpenAPI")
    void openApiTest() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/openapi")
                .then()
                .statusCode(200)
                .body("paths.'/bookmarks'.get.summary", CoreMatchers.is(CoreMatchers.notNullValue()));
    }

}
