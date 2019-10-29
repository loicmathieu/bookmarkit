mvn io.quarkus:quarkus-maven-plugin:0.26.1:create \
    -DprojectGroupId=fr.loicmathieu.bookmarkit \
    -DprojectArtifactId=bookmark-service \
    -DclassName="fr.loicmathieu.bookmarkit.BookmarkResource" \
    -Dpath="/bookmarks" \
    -Dextensions="resteasy-jsonb,openapi,metrics,health,rest-client,hibernate-orm-panache,jdbc-postgresql,fault-tolerance,amqp"

mvn io.quarkus:quarkus-maven-plugin:0.26.1:create \
    -DprojectGroupId=fr.loicmathieu.bookmarkit \
    -DprojectArtifactId=bookmark-message-consumer \
    -DclassName="fr.loicmathieu.bookmarkit.BookmarkConsumer" \
    -Dpath="/bookmarks" \
    -Dextensions="metrics,health,amqp"
    