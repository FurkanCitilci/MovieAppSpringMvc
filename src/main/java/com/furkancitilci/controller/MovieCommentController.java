package com.furkancitilci.controller;

import com.furkancitilci.dto.request.MovieCommentCreateRequestDto;
import com.furkancitilci.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/moviecomment")
@RequiredArgsConstructor
public class MovieCommentController {

    private final MovieCommentService movieCommentService;

    @PostMapping("/save")
    public ModelAndView save(MovieCommentCreateRequestDto dto){

        movieCommentService.save(dto);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("id",dto.getMovieId());
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/movie/findbyid");
        // modelAndView.setViewName("redirect:/movie/findbyid?id="+dto.getMovieId()+"&userId="+dto.getUserId());
        return modelAndView;


    }
}
