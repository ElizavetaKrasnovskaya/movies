package com.bsuir.spring.service.movie;

import com.bsuir.spring.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    void saveMovie(Movie movie);

    Movie getMovieById(int id);

    void deleteMovieById(int id);

    Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
