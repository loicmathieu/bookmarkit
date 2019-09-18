package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkConsumer {

    @Incoming("bookmarks")
    public void process(String bookmark) {
        System.out.println("Indexing a bookmark: " +bookmark );
    }
}