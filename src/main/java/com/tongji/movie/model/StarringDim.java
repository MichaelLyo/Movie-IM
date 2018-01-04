package com.tongji.movie.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="StarringDim")
public class StarringDim {
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getStarringName() {
        return starringName;
    }

    public void setStarringName(String starringName) {
        this.starringName = starringName;
    }

    private String movieId;

    private String starringName;
}
