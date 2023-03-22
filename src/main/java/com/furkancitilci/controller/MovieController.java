package com.furkancitilci.controller;

import com.furkancitilci.dto.response.LoginResponseDto;
import com.furkancitilci.repository.entity.Movie;
import com.furkancitilci.repository.entity.User;
import com.furkancitilci.service.GenreService;
import com.furkancitilci.service.MovieService;
import com.furkancitilci.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final GenreService genreService;
    private final MovieService movieService;
    private final UserService userService;

    @GetMapping("")
    public ModelAndView getMoviePage(LoginResponseDto responseDto,String [] genre){
        ModelAndView modelAndView = new ModelAndView();
        List<Movie> movieList;
        if (genre==null){
            movieList =movieService.findAll();
        }else {
            movieList= movieService.findAllByGenres_NameIn(genre);

        }
        modelAndView.setViewName("movies");

        modelAndView.addObject("genres",genreService.findAll());
        modelAndView.addObject("user",responseDto);
        modelAndView.addObject("movies",movieList);
        modelAndView.setViewName("movies");
        return modelAndView;
    }

    @GetMapping("/findbyid")
    public ModelAndView getById(Long id, Long userId){
            User user=null;
            if (userId==null){
                user=User.builder().build();

            }else {
                user=userService.findById(userId).get();
            }
        Movie movie = movieService.findbyId(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("movie",movie);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("moviesDetail");
        return modelAndView;
    }
}
