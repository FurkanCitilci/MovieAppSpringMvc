package com.furkancitilci.repository;


import com.furkancitilci.repository.entity.Movie;
import com.furkancitilci.repository.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional                              //@Lob üzerinden hata alıyorum, aldığım hata auto commit olduğu için bu anotasyon işimi görüyoru.
public interface IMovieCommentRepository extends JpaRepository<MovieComment,Long> {

    @Query("select  m from MovieComment  as m where m.movie.id=?1")
    List<MovieComment> findAllByMyMovies(Long movieId);

    /**
     * Aşağıdaki kullanım
     * findAllByMovie_Id
     * findAllByMovie_Name
     * daha çok manytomany,manytoone ilişkide kullanılır
     * @param movieId
     * @return
     */
    List<MovieComment> findAllByMovie_Id(Long movieId);
    List<MovieComment> findAllByMovie_Name(String name);
    List<MovieComment> findAllByMovie(Movie movie);

    List<MovieComment> findAllByUser_Id(Long userId);

    List<MovieComment> findAllByDateBetweenAndUser_Id(LocalDate start, LocalDate end, Long userId );

    List<MovieComment> findAllByDateBetweenAndMovie_Id(LocalDate start,LocalDate end,Long movieId);

    List<MovieComment> findAllByContentContainingIgnoreCase(String value);

    @Query("select m from MovieComment as m where length(m.content)>?1")
    List<MovieComment> findAllByContentSize(int length);

    @Query("select distinct (m.content) from MovieComment as m where length(m.content)>?1")
    List<String> findAllByContentSizeString(int length);
}
