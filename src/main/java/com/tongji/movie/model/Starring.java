package com.tongji.movie.model;

import javax.persistence.*;

@Entity
@Table(name="Starring")
public class Starring {

    @Id
    private String movieId;

    private String name;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}