package fr.loicmathieu.bookmarkit;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkConsumer {

    private static final Logger LOG = Logger.getLogger(BookmarkConsumer.class);

    @Incoming("bookmarks")
    public void process(String bookmark) {
        LOG.infof("Indexing a bookmark: %s", bookmark);
    }
}
