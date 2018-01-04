package com.tongji.movie.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Genre")
public class Genre {
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    private String movieId;

    private String genreType;
}
