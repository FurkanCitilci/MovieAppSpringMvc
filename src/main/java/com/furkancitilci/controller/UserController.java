package com.furkancitilci.controller;


import com.furkancitilci.dto.request.FavMoviesRequestDto;
import com.furkancitilci.dto.request.LoginRequestDto;
import com.furkancitilci.dto.request.UserRegisterRequestDto;
import com.furkancitilci.dto.response.LoginResponseDto;
import com.furkancitilci.dto.response.MovieAdminPageResponseDto;
import com.furkancitilci.dto.response.UserFindAllResponseDto;
import com.furkancitilci.dto.response.UserRegisterResponseDto;
import com.furkancitilci.mapper.IMovieMapper;
import com.furkancitilci.repository.entity.EUserType;
import com.furkancitilci.repository.entity.User;
import com.furkancitilci.service.GenreService;
import com.furkancitilci.service.MovieService;
import com.furkancitilci.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/*
    1-)Mvc yapısında register metotlarımızı yazalım
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GenreService genreService;
    private final MovieService movieService;
    private final UserService userService;
    private final MovieController movieController;

    @GetMapping("/register")
    public ModelAndView getRegisterPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("myerror","");
        modelAndView.setViewName("register");
        return modelAndView;

    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterRequestDto dto) {
        String error="";
        ModelAndView modelAndView = new ModelAndView();
        UserRegisterResponseDto userRegisterResponseDto = null;

        try {
            userRegisterResponseDto=userService.register2(dto);
            System.out.println("dto=>  " + userRegisterResponseDto);
            modelAndView.addObject("email",userRegisterResponseDto.getEmail());
            modelAndView.setViewName("redirect:login");
            //modelAndView.setViewName("redirect:login?email=" +dto.getEmail());
            //alternatif çözüm
            // return getLoginPage(dto.getEmail());      //method dan çağırınca url değişmez

        }catch (Exception e){
            error=e.getMessage();
            modelAndView.addObject("myerror",error);
            //modelAndView.setViewName("redirect:register");
            modelAndView.setViewName("register");
        }

        return modelAndView;

    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(String email){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email",email);
        modelAndView.setViewName("login");
        return modelAndView;

    }

    @PostMapping("/login")
    public ModelAndView login(LoginRequestDto dto){
        boolean islogin=false;
        ModelAndView modelAndView=new ModelAndView();

        try {
            LoginResponseDto responseDto= userService.login(dto);
            if (responseDto.getUserType().equals(EUserType.ADMIN)){

                    return adminPage();

            }
            //modelAndView.addObject("result","Giriş Başarılı");
            //modelAndView.addObject("id",responseDto.getId()); 1. yöntem
            //modelAndView.setViewName("redirect:/movie");

             return movieController.getMoviePage(responseDto,null);  //2. yöntem

        }catch (Exception e){
            modelAndView.addObject("result",e.getMessage());
            modelAndView.setViewName("login");

        }

        return modelAndView;
    }

    @PostMapping("/admin")
    private ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();

            List<UserFindAllResponseDto> users =
                    userService.findAll()
                            .stream().filter(x->!x.getUserType().equals(EUserType.ADMIN))
                            .collect(Collectors.toList());

            modelAndView.addObject("users",users);
            List<MovieAdminPageResponseDto> movies=  IMovieMapper.INSTANCE.toMovieAdminPageResponseDto(movieService.findAll());
            modelAndView.addObject("movies",movies);
            modelAndView.setViewName("admin");


        return modelAndView;
    }

    @GetMapping("/addfavmovies")
    public ModelAndView addFavMovies(FavMoviesRequestDto dto){

        ModelAndView modelAndView = new ModelAndView();
        userService.addFavMovies(dto);
        modelAndView.addObject("id",dto.getMovieId());
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/movie/findbyid");

        return modelAndView;


    }

    @GetMapping("/removefavmovies")
    public ModelAndView addfavmoviesFavMovies(FavMoviesRequestDto dto){

        ModelAndView modelAndView = new ModelAndView();
        userService.removeFavMovies(dto);
        modelAndView.addObject("id",dto.getMovieId());
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/movie/findbyid");

        return modelAndView;


    }


}
