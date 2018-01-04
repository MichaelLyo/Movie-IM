package com.tongji.movie.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DirectorDim")
public class DirectorDim {
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    private String movieId;

    private String directorName;
}
