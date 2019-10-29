package fr.loicmathieu.bookmarkit;

import javax.persistence.Entity;

@Entity
public class Bookmark {
    public String title;
    public String url;
    public String description;
    public String location;
}
