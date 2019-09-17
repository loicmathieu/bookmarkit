# bookmarkit - Quarkus coding dojo

You can use the `docker-compose.yml` file located on the root of this repository to launch all dependencies.

So going inside the root directory and launching the following command will get you up and running :)

```
docker-compose up
```

## Step 1 - Quarkus basics

Goal: implements a bookmark service that pass the test from `fr.loicmathieu.bookmarkit.BookmarkStep1Test`.

Steps:

- Open `bookmark-service` inside your favorite IDE
- Implements the `fr.loicmathieu.bookmarkit.BookmarkResource` service, it's a classical CRUD service to allow to create/read/update/delete a bookmark. 
  You must implement the service as a JAX-RS resource ([WRITING JSON REST SERVICES](https://quarkus.io/guides/rest-json-guide)).
  You must use Hibernate ORM with Panache to access the database ([SIMPLIFIED HIBERNATE ORM WITH PANACHE](https://quarkus.io/guides/hibernate-orm-panache-guide)).
- Run the service with `mvn compile quarkus:dev` it has livereload !
- Test that everything works with `mvn test -Dtest=BookmarkStep1Test ` : the step1() test should pass.

NOTE: Hibernate is configured to drop and recreate the schema each time you restart your application. You can change this behaviour inside your `application.properties`.


## Step 2 - Observablity: OpenAPI and OpenMetrics

