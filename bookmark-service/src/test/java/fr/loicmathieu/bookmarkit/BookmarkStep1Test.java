package fr.loicmathieu.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookmarkStep1Test {

    @Test
    public void step1() {
        RestAssured.defaultParser = Parser.JSON;

        Bookmark bookmark1 = new Bookmark();
        bookmark1.title = "Google";
        bookmark1.url = "https://google.com";
        bookmark1.description = "You known, for search";
        Bookmark bookmark2 = new Bookmark();
        bookmark2.title = "Zenika";
        bookmark2.url = "https://zenika.com";
        bookmark2.description = "Coding the world";

        //create two bookmarks
        String location = given().body(bookmark1).contentType("application/json")
                .when().post("/bookmarks")
                .then()
                .statusCode(201)
                .extract()
                .header("Location");
        Assertions.assertNotNull(location);
        String id = location.substring(location.lastIndexOf('/') + 1);
        given().body(bookmark2).contentType("application/json")
                .when().post("/bookmarks")
                .then()
                .statusCode(201);

        //check that the list has 2 bookmarks
        given()
                .when().get("/bookmarks")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(2));

        //get the first one
        given()
                .when().get("/bookmarks/" + id)
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.is(1));

        //update the first one
        bookmark1.title = "Google search";
        bookmark1.id = Long.parseLong(id);
        given().body(bookmark1).contentType("application/json")
                .when().put("/bookmarks/" + id)
                .then()
                .statusCode(204);

        //check that the title has been updated
        given()
                .when().get("/bookmarks/" + id)
                .then()
                .statusCode(200)
                .body("title", CoreMatchers.is("Google search"));

        //delete the first one
        given()
                .when().delete("/bookmarks/" + id)
                .then()
                .statusCode(204);

        //check that the list has 1 bookmarks
        given()
                .when().get("/bookmarks")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(1));
    }

}