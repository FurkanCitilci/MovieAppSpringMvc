package com.furkancitilci.service;


import com.furkancitilci.dto.request.MovieCommentCreateRequestDto;
import com.furkancitilci.mapper.IMovieCommentMapper;
import com.furkancitilci.repository.IMovieCommentRepository;
import com.furkancitilci.repository.entity.Movie;
import com.furkancitilci.repository.entity.MovieComment;
import com.furkancitilci.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCommentService {

    private final IMovieCommentRepository movieCommentRepository;
    private final UserService userService;
    private final MovieService movieService;

    public List<MovieComment> findAllByMyMovies(Long movieId){
        return movieCommentRepository.findAllByMyMovies(movieId);
    }

    public   List<MovieComment> findAllByMovie_Id(Long movieId){
        return movieCommentRepository.findAllByMovie_Id(movieId);
    }

    public  List<MovieComment> findAllByUser_Id(Long userId){

        return movieCommentRepository.findAllByUser_Id(userId);
    }

    public List<MovieComment> findAllByDateBetweenAndUser_Id(String start, String end, Long userId ){
        return movieCommentRepository.findAllByDateBetweenAndUser_Id(LocalDate.parse(start)
                ,LocalDate.parse(end),userId);
    }

    public List<MovieComment> findAllByDateBetweenAndMovie_Id(String start, String end, Long movieId ){
        return movieCommentRepository.findAllByDateBetweenAndMovie_Id(LocalDate.parse(start)
                ,LocalDate.parse(end),movieId);
    }


    public  List<MovieComment> findAllByContentContainingIgnoreCase(String value){

        return movieCommentRepository.findAllByContentContainingIgnoreCase(value);
    }

    public    List<MovieComment> findAllByContentSize(int length){

        return movieCommentRepository.findAllByContentSize(length);
    }

    public   List<String> findAllByContentSizeString(int length){

        return movieCommentRepository.findAllByContentSizeString(length);
    }

    public void save(MovieCommentCreateRequestDto dto) {
        Movie movie =movieService.findbyId(dto.getMovieId());
        User user = userService.findById(dto.getUserId()).get();
        MovieComment movieComment= IMovieCommentMapper.INSTANCE.toMovieComment(dto);

        movieComment.setMovie(movie);
        movieComment.setUser(user);
        movieCommentRepository.save(movieComment);
    }
}
