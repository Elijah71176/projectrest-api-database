package com.project1.projectrestapidatabase.db;


import com.project1.projectrestapidatabase.model.Films;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Access data, Simply handles data operation, I call it manipulate data,
 * Is interact with database which is responsible for CRUD
 * Create,Retrieves, Update and delete.
 * Also express the essential futures of database queries. It called by service layer or resource class.
 * * also does no required to write SQL code manually.
 */
//Makes the repository into a singleton for the duration of the application's life circle.
@ApplicationScoped
//Ensures all database operations are handled in a transaction, automatically rolling back as necessary.
@Transactional
public class FilmsRepository {
    //TO managing  entityManager 4 interact with persistence context.
    @PersistenceContext
    //Handles interactions with the persistence context (e.g., querying, saving).
    private EntityManager entityManager;

    public List<Films> findAllFilms() {
        //Executes the SQL query SELECT * FROM appuser using EntityManager.createNativeQuery.
        String sql = "SELECT * FROM films";
        Query query = entityManager.createNativeQuery(sql, Films.class);
        //Retrieves all records from the appuser table and stores the result in a List<Films>.
        List<Films> film = query.getResultList();
        return film;

    }

    public void createFilm(Films film) { //The film object passed from the addNewFilm method.
        entityManager.persist(film); //Uses the JPA EntityManager to persist (save) the Films entity in the database.
    }

    //update films in Db if one found, parameter help to update films object to save.
    public void update(Films film) {
        entityManager.merge(film); //persist changes and update a new record in one exit.
    }

    //this method helps to delete by id method,
    public void deleteFilm(Long id) {
        Films film = findById(id);
        if (film != null) {
            entityManager.remove(film);
        }
    }

    public Films findById(Long id) {
        return entityManager.find(Films.class, id);
    }
}
