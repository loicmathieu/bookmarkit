package com.lahzouz.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GeoIpResourceTest {

    @Test
    void testFindCurrentGeoIpInfo() {
        given()
                .when()
                .get("/geoip/")
                .then()
                .log().ifValidationFails()
                .statusCode(500);

        given()
                .when().get("/geoip/")
                .then()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }


    @Test
    void testFindGeoIpInfo() {
        given()
                .when()
                .get("/geoip/0")
                .then()
                .log().ifValidationFails()
                .statusCode(500);

        given()
                .when()
                .get("/geoip/8.8.8.8")
                .then()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("query", is("8.8.8.8"),
                        "countryCode", is("US"),
                        "country", is("United States"),
                        "lat", is(39.0438f),
                        "lon", is(-77.4874f));

    }

    @Test
    void testFindGeoIpInfoNotFound() {
        given()
                .when()
                .get("/geoip/0")
                .then()
                .log().ifValidationFails()
                .statusCode(500);

        given()
                .when()
                .get("/geoip/0")
                .then()
                .log().ifValidationFails()
                .contentType(ContentType.HTML)
                .statusCode(500);
    }

}
