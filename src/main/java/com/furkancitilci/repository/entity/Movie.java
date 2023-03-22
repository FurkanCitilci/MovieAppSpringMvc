package com.furkancitilci.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {

    //Manytomany
    //List<Long> genresId;      -->microservice de göreceğiz

    /*
        eĞER BİR Entity sınıfı yazmış ve içinde başka sınıf kullanıyorsan
        ilişkisini vermek ZORUNDASIN
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Many to many
    //  List<Long> genresId;
    @ManyToMany(fetch = FetchType.LAZY)
    private   List<Genre> genres;
    private  String language;
    private  String image;
    private  String name;
    private  String country;
    private  double rating;
    @Column(length = 2048)
    private  String summary;
    private LocalDate premiered;
    private String url;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<MovieComment> comments;

}

