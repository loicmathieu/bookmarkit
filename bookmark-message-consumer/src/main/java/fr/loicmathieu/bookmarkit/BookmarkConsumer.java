package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkConsumer {

    private final Logger log = LoggerFactory.getLogger(BookmarkConsumer.class);

    @Incoming("bookmarks")
    public void process(String bookmark) {
        log.info("Indexing a bookmark: {}", bookmark);
    }
}
