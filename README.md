# bookmarkit - Quarkus coding dojo

You can use the `docker-compose.yml` file located on the root of this repository to launch all dependencies.

So going inside the root directory and launching the following command will get you up and running :)

```
docker-compose up
```

There is a Postman collection that can help to test your application during you developement, it is located [here](Bookmarkit.postman_collection.json).

## Step 1 - Quarkus basics

Goal: implements a bookmark service that pass the test from `fr.loicmathieu.bookmarkit.BookmarkStep1Test`.

Steps:

- Open `bookmark-service` inside your favorite IDE
- Implements the `fr.loicmathieu.bookmarkit.BookmarkResource` service, it's a classical CRUD service to allow to create/read/update/delete a bookmark. 
  You must implement the service as a JAX-RS resource ([WRITING JSON REST SERVICES](https://quarkus.io/guides/rest-json-guide)).
  You must use Hibernate ORM with Panache to access the database ([SIMPLIFIED HIBERNATE ORM WITH PANACHE](https://quarkus.io/guides/hibernate-orm-panache-guide)).
- Run the service with `mvn compile quarkus:dev` it has livereload !
- Test that everything works with `mvn test -Dtest=BookmarkStep1Test `.

NOTE: Hibernate is configured to drop and recreate the schema each time you restart your application. You can change this behaviour inside your `application.properties`.


## Step 2 - Observablity: OpenAPI and OpenMetrics

Goal: implements Observalibily principles: metrology and discoverability

Steps:

- Add [OpenAPI annotations](https://github.com/eclipse/microprofile-open-api/blob/master/spec/src/main/asciidoc/microprofile-openapi-spec.adoc#annotations) to document your API, it will be availabe at http://localhost:8080/openapi and there is an embedded swagger UI at http://localhost:8080/swagger-ui
  - Use `@Operation` to document each operation.
- Add OpenMetrics annotations to create metrics for each method of your endpoints, you should provide counted and timed metrics via `@Counted` and `@Timed`, 
be careful that the name of the metric must be unique. You can access your metrics at http://localhost:8080/metrics.
- Test that everything works with `mvn test -Dtest=BookmarkStep2Test `.

NOTE: OpenAPI and OpenMetrics works as soon as you integrate the extension inside your application as it provides default documentation and metrics.



