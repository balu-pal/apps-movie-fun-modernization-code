package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;


import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient movieClient;
    //private final AlbumsBean albumsBean;
    private final MovieFixtures movieFixtures;
    //private final AlbumFixtures albumFixtures;

//    public HomeController(MoviesClient movieClient, AlbumsBean albumsBean, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
//        this.movieClient = movieClient;
//        this.albumsBean = albumsBean;
//        this.movieFixtures = movieFixtures;
//        this.albumFixtures = albumFixtures;
//    }
    public HomeController(MoviesClient movieClient, MovieFixtures movieFixtures) {
        this.movieClient = movieClient;
        this.movieFixtures = movieFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
