package fr.loicmathieu.bookmarkit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Bookmark extends PanacheEntity {
    public String title;
    public String url;
    public String description;
    public String location;
}
