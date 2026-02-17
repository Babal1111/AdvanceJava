package org.example.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors = new HashSet<>();

    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    // Getters & Setters
    public int getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Set<Actor> getActors() { return actors; }

    public void setActors(Set<Actor> actors) { this.actors = actors; }
}
