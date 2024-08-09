package com.neo.resources;

import com.neo.model.Movie;
import com.neo.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @GET
    public Response getAll(){
        List<Movie> movieList = movieRepository.listAll();
        return Response.ok(movieList).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
       return movieRepository.findByIdOptional(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("title/{title}")
    public Response getByTitle(@PathParam("title") String title){
        return movieRepository.find("title", title)
                .singleResultOptional()
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("country/{country}")
    public Response getByCountry(@PathParam("country") String country){
        List<Movie> moviesCountry = movieRepository.findByCountry(country);
        return Response.ok(moviesCountry).build();
    }

    @POST
    @Transactional
    public Response createMovie(Movie movie){
        movieRepository.persist(movie);
        if(movieRepository.isPersistent(movie)){
            return Response.created(URI.create("/movies" + movie.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = movieRepository.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id);
        if (existingMovie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setCountry(movie.getCountry());

        movieRepository.persist(existingMovie);

        return Response.ok(existingMovie).build();
    }

}
