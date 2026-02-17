package org.example.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies = new HashSet<>();

    public Actor() {}

    public Actor(String name) {
        this.name = name;
    }

    // Getters & Setters
    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Movie> getMovies() { return movies; }

    public void setMovies(Set<Movie> movies) { this.movies = movies; }
}
