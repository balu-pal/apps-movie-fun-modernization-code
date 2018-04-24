package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.superbiz.moviefun.albumsapi.AlbumFixtures;
import org.superbiz.moviefun.albumsapi.AlbumInfo;
import org.superbiz.moviefun.albumsapi.AlbumsClient;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.superbiz.moviefun.MovieServlet.PAGE_SIZE;

@Controller
public class SetupController {
    private final MoviesClient moviesClient;
    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public SetupController(MoviesClient moviesClient, AlbumsClient albumsClient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesClient;
        this.albumsClient = albumsClient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumsClient.addAlbum(album);
        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }

    /*
    @Autowired
    private MoviesClient moviesClient;
    public static int PAGE_SIZE = 5;
    @PostMapping
    public String addMovie(MovieInfo movieInfo){
        //TODO
        moviesClient.addMovie(movieInfo);
        return format("redirect:/moviefun");
    }

    @DeleteMapping("{id}")
    public String deleteMovie(@PathVariable Long id){
        moviesClient.deleteMovieId(id);
        return format("redirect:/moviefun");
    }

    @GetMapping
    public ResponseEntity<List<MovieInfo>> movies(@RequestParam String key,@RequestParam String field,@RequestParam int page ){
        List<MovieInfo> range;

        int start = (page - 1) * PAGE_SIZE;
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            range = moviesClient.findAll(start, PAGE_SIZE);
        } else {
            range = moviesClient.findRange(field, key, start, PAGE_SIZE);
        }

        return new ResponseEntity<>(range,HttpStatus.OK);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


            String key = request.getParameter("key");
            String field = request.getParameter("field");

            int count = 0;

            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
                count = moviesClient.countAll();
                key = "";
                field = "";
            } else {
                count = moviesClient.count(field, key);
            }

            int page = 1;

            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
            }

            int pageCount = (count / PAGE_SIZE);
            if (pageCount == 0 || count % PAGE_SIZE != 0) {
                pageCount++;
            }

            if (page < 1) {
                page = 1;
            }

            if (page > pageCount) {
                page = pageCount;
            }

            int start = (page - 1) * PAGE_SIZE;
            List<MovieInfo> range;

            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
                range = moviesClient.findAll(start, PAGE_SIZE);
            } else {
                range = moviesClient.findRange(field, key, start, PAGE_SIZE);
            }

            int end = start + range.size();

            request.setAttribute("count", count);
            request.setAttribute("start", start + 1);
            request.setAttribute("end", end);
            request.setAttribute("page", page);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("movies", range);
            request.setAttribute("key", key);
            request.setAttribute("field", field);


        request.getRequestDispatcher("WEB-INF/moviefun.jsp").forward(request, response);
    }
*/
}
