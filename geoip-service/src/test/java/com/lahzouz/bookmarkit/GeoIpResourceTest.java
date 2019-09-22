package com.lahzouz.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GeoIpResourceTest {

    @Test
    public void testFindGeoIpInfo() {
        given()
                .when().get("/geoip/8.8.8.8")
                .then()
                .log().ifValidationFails()
                .contentType(JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("ip", is("8.8.8.8"),
                        "country_code", is("US"),
                        "country_name", is("United States"),
                        "latitude", is(37.751f),
                        "longitude", is(-97.822f));

        given()
                .when()
                .get("/geoip/0")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testFindGeoIpInfoNotFound() {
        given()
                .when()
                .get("/geoip/0")
                .then()
                .log().ifValidationFails()
                .contentType(TEXT)
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}