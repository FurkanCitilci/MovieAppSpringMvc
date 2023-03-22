package com.furkancitilci.service;


import com.furkancitilci.repository.IGenreRepository;
import com.furkancitilci.repository.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * String name dışarıdan alınan veri, örn(Drama) Bu veriye uygun bir metot, yani findByName
 * genre name ine bir genre getiren metodu repositoryde oluşturalım
 */
@Service
@RequiredArgsConstructor
public class GenreService {

    private final IGenreRepository genreRepository;

    public List<Genre> createGenresWithNames(List<String> genres) {

        List<Genre> genreList=new ArrayList<>();

        for(String name:genres){
            Optional<Genre> genre =genreRepository.findOptionalByName(name);
            if(genre.isPresent()){
                genreList.add(genre.get());        //databasedeki nesneyi ekleyeceğiz
            }else{
                Genre genre1=Genre.builder().name(name).build();
                genreRepository.save(genre1);
                genreList.add(genre1);           //yeni bir genre oluşturup onu ekleyeceğiz
            }

        }

        return genreList;
    }

    public List<Genre> findAll() {

        return genreRepository.findAll();
    }
}
