package com.bsuir.spring.service.movie;

import com.bsuir.spring.model.Movie;
import com.bsuir.spring.model.Rating;
import com.bsuir.spring.repository.MovieRepository;
import com.bsuir.spring.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingService ratingService;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        double rating = 0;
        for (Movie movie : movies) {
            for (Rating ratings : movie.getRatings()) {
                rating += ratings.getRating();
            }
            if (!movie.getRatings().isEmpty()) {
                rating /= movie.getRatings().size();
            }
            movie.setRating(rating);
        }
        return movies;
    }

    @Override
    public void saveMovie(Movie movie) {
        if(movie.getRatings() == null) {
            Rating rating = new Rating(movie.getRating(), movie);
            movie.setRatings(Set.of(rating));
            movieRepository.save(movie);
            ratingService.saveRating(rating);
        }else{
            movieRepository.save(movie);
        }
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.getById(id);
    }

    @Override
    public void deleteMovieById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return movieRepository.findAll(pageable);
    }
}
