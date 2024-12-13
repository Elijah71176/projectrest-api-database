package com.project1.projectrestapidatabase.model;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This is the JPA entity or model representing a table in the database.
 * Each instance of this class corresponds to a row in the database.
 * The class represent the data structure and mapping directly to the database table
 * The data objects can be saved and retrieves in Db automatic
 * JPA will manage d life circle, del,update and saving,
 * it points to data table. tagged with annotation Entity.
 */

//due to  JSON libraries serialize objects by default, I used this annotation to
// serailize as I want it.
@JsonbPropertyOrder({"title", "genre", "releaseYear", "description", "director"})

@Entity
@Table(name = "films") //Here is table name in database.


public class Films {


    @Id
    @GeneratedValue // Automatically generates and increments the ID.

    //variable attributes
    private Long id;


    private String title;
    private String genre;
    private int releaseYear;
    private String description;
    private String director;

    public Films() {

    }

    public Long getId() { // empty constructor to be able to work proper.
        return id;
    }

    public void setId(Long id) {// is void bcus it does not return, but 4 d JPA and others frames
        // work to set id when require
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
