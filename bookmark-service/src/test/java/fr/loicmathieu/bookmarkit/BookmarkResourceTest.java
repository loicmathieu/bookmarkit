package fr.loicmathieu.bookmarkit;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BookmarkResourceTest {

    @Test
    void listAllBookmarksTest() {
        given()
                .when()
                .get("/bookmarks")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.is(3));
    }

    @Test
    void getBookmarkTest() {
        given()
                .when()
                .get("/bookmarks/{id}",1)
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.is(1));
    }

    @Test
    void createBookmarkTest() {
        Bookmark bookmark = new Bookmark();
        bookmark.title = "Twitter FR";
        bookmark.url = "https://twitter.fr/";
        bookmark.description = "Twitter est un service de microblogging et un réseau social";

        given()
                .body(bookmark)
                .contentType(ContentType.JSON)
                .when().post("/bookmarks/")
                .then()
                .statusCode(201);
    }

    @Test
    void updateBookmarkTest() {
        Bookmark bookmark = new Bookmark();
        bookmark.description = "Coding the welsh";
        bookmark.id = 1L;

        given()
                .body(bookmark)
                .contentType(ContentType.JSON)
                .when().put("/bookmarks/{id}",1)
                .then()
                .statusCode(201);
    }

    @Test
    void deleteBookmarkTest() {
        given()
                .when()
                .delete("/bookmarks/{id}",2)
                .then()
                .statusCode(204);
    }

}
