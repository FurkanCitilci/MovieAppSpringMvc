package com.furkancitilci.repository;


import com.furkancitilci.repository.entity.Genre;
import com.furkancitilci.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional          //yaptığımız işlkemlerde hatamıoz varsa geri döndürmeyi sağlıyor
public interface IMovieRepository extends JpaRepository<Movie,Long> {



    List<Movie> findAllByRatingGreaterThan(double rate);
    List<Movie> findAllByGenresInOrderByRatingDesc(List<Genre> genres);
    List<Movie> findAllByPremieredBefore(LocalDate date);
    @Query("select count(m.rating) , m.rating from Movie as m group by m.rating having m.rating=?1")
    Object [] searchByRating(double rating);

    @Query("select count(m.rating) , m.rating from Movie as m group by m.rating ")
    List<Object> searchByRating();

    int countAllByRating(double rating);

    /**
     * Birden fazla veri alacağım zaman In Kullanırım!!!!
     * @param ratings
     * @return
     */
    List<Movie> findAllByRatingIn(List<Double> ratings);

    /**
     * Dışarıdan veriyi List olarak alamayız 500 hatası alırız
     * Dışarıdan veriyi array olarak almamız lazım
     * @param names
     * @return
     */
    List<Movie> findAllByNameIn(String[] names);

    @Query("select  m.country,  count (m.country) from Movie  as m group by  m.country")
    Object [] searchByCountry();

    List<Movie> findAllByGenres_NameIn(String[] genre);

}
