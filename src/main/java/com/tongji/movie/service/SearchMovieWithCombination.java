package com.tongji.movie.service;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SearchMovieWithCombination {
    @Autowired
    private ConToHive conObj;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

    public JSONArray searchInOracle(String date,String name,String actor,String director,String genre) throws SQLException {
        JSONArray movies = new JSONArray();
        String nameLike = '%' + name + '%';
        String actorLike = '%' + actor + '%';
        String directorLike = '%' + director + '%';
        List<AmazonFact> amazonFacts = amazonFactRepository.findAmazonFactsByCombination(date,nameLike,actorLike,directorLike,genre);
        if(!amazonFacts.isEmpty()) {
            for (AmazonFact a : amazonFacts) {
                JSONObject movie = new JSONObject();
                movie.put("movieId", a.getMovieId());
                movie.put("title", a.getTitle());
                movie.put("releaseDate", a.getReleaseDate());
                movie.put("runTime", a.getRunTime());
                movie.put("studio", a.getStudio());
                movie.put("publicationDate", a.getPublicationDate());
                movie.put("publisher", a.getPublishier());
                movies.add(movie);
            }
        }
        return movies;
    }



}
