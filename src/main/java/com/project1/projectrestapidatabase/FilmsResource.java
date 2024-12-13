package com.project1.projectrestapidatabase;

import com.project1.projectrestapidatabase.db.FilmsRepository;
import com.project1.projectrestapidatabase.model.Films;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


/**
 * This is controller layer in the MVC(MODEL, VIEW, CONTROLLER).
 * It handles HTTP requests and responses, such as when a user wants such as:
 * it interacts  with database like get,put,post and delete CRUD()CREATE, RETRIEVE, UPDATE AND DELETE.
 * Role: Exposes REST endpoints for interacting with User data. Fetch date according to request.
 * Convert HTTP replies to different client formats, such as JSON.
 */

@Path("/films") //connecting the class to the /films endpoints, Methods withing this class respond to d request as path
public class FilmsResource {
    //For dependency management with CDI (Context and Dependency Injection), an instance of FilmsRepository is injected.
    //There's no need to create New or create =new instance.
    @Inject
    FilmsRepository filmsRepository;

    //Maps/connecting the getFilms method to handle HTTP GET requests.

    @GET
    //Specifies that the response will be in JSON format.
    @Produces(MediaType.APPLICATION_JSON)
    //Calls filmsRepository.findAllFilms() to fetch the list of films.
    public List<Films> getFilms() {
        //This will return the list, automatically serialized/converting to JSON for the HTTP response.
        return filmsRepository.findAllFilms();


    }

    @POST //This method handles HTTP POST requests. Create
    @Produces(MediaType.APPLICATION_JSON) //Specifies that the method will return a response in JSON format.
    @Consumes(MediaType.APPLICATION_JSON)//method expects the incoming request payload to be in JSON format.
    public Response addNewFilm(Films film) { // para repse films and connect 2 DB and entity java persistence
        filmsRepository.createFilm(film);// call creatFilm method 4rm client and http respond when it gr8
        return Response.status(Response.Status.CREATED).entity("Film created").build();// build return end points or final responds

    }

    @PUT //update film, it marks both put and path as restful endpoints
    @Path("/{id}") //to specifies the id need to update parameter to identify the film
    @Produces(MediaType.APPLICATION_JSON) //specify  the method will return a response in JSON format.
    @Consumes(MediaType.APPLICATION_JSON)
    //The @PathParam annotation binds the id from the URL path and match with Variable
    public Response updateFilm(@PathParam("id") Long id, Films updatedFilm) {//update exist film Db
        //Calls the repository to find a film with the given id.
        Films existFilms = filmsRepository.findById(id);
        //condition if not find, respond with 404 erroe.
        if (existFilms == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film not found").build();

        }//Copies the updated values (title, genre, etc.) Update properties if film exist. Only for exist film, if found.

        existFilms.setTitle(updatedFilm.getTitle());
        existFilms.setGenre(updatedFilm.getGenre());
        existFilms.setReleaseYear(updatedFilm.getReleaseYear());
        existFilms.setDescription(updatedFilm.getDescription());
        existFilms.setDirector(updatedFilm.getDirector());
        //Calls filmsRepository.update(existFilms); to persist the changes.
        filmsRepository.update(existFilms);
        //respond HTTP 200 ok.
        return Response.status(Response.Status.OK).entity("Film updated").build();
    }

    @DELETE//to delete
    @Path("/{id}")//mark specify id we want to delete
    public Response removeFilm(@PathParam("id") Long id) {// this @PathParam mapping d id 4rm url and matches with variable
        Films existFilms = filmsRepository.findById(id);
        if (existFilms == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film not found").build();
        }
        filmsRepository.deleteFilm(id);
        return Response.status(Response.Status.OK).entity("Film removed").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilm(@PathParam("id") Long id) {
        Films films = filmsRepository.findById(id); // call findByid
        if (films == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film not found").build();
        }
        return Response.ok(films).build();

    }
}