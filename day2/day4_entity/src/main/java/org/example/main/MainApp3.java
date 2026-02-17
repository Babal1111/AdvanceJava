package org.example.main;

import org.example.entity.Actor;
import org.example.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp3 {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Actor a1 = new Actor("Robert Downey Jr");
        Actor a2 = new Actor("Chris Evans");

        Movie m1 = new Movie("Avengers");
        Movie m2 = new Movie("Iron Man");

        // Set relationships
        a1.getMovies().add(m1);
        a1.getMovies().add(m2);

        a2.getMovies().add(m1);

        session.persist(m1);
        session.persist(m2);
        session.persist(a1);
        session.persist(a2);

        session.getTransaction().commit();

        session.close();
        factory.close();
    }
}
