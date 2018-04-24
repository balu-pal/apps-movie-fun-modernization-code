package org.superbiz.movieservice;

import org.springframework.web.bind.annotation.*;
import org.superbiz.movieservice.movies.Movie;
import org.superbiz.movieservice.movies.MoviesBean;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class ApiController {
    private MoviesBean moviesBean;

    public ApiController(MoviesBean moviesBean) {
        this.moviesBean = moviesBean;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        moviesBean.addMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieId(@PathVariable Long movieId) {
        moviesBean.deleteMovieId(movieId);
    }

    @GetMapping("/count")
    public int count(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "key", required = false) String key
    ) {
        if (field != null && key != null) {
            return moviesBean.count(field, key);
        } else {
            return moviesBean.countAll();
        }
    }

    @GetMapping
    public List<Movie> find(
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        if (field != null && key != null) {
            return moviesBean.findRange(field, key, start, pageSize);
        } else if (start != null && pageSize != null) {
            return moviesBean.findAll(start, pageSize);
        } else {
            return moviesBean.getMovies();
        }
    }
}
