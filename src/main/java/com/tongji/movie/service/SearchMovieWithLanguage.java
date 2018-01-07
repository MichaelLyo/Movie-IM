package com.tongji.movie.service;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class SearchMovieWithLanguage {
    @Autowired
    private ConToHive conObj;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

    public JSONArray search(String language) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a  join LANGUAGE l on( a.movieid= l.movieid) where l.name=?");
        pstmt.setString(1,language);
        ResultSet set =  pstmt.executeQuery();
        while(set.next()){
            JSONObject movie = new JSONObject();
            movie.put("movieId",set.getString("movieId"));
            movie.put("title",set.getString("title"));
            movie.put("releaseDate",set.getString("releaseDate"));
            movie.put("runTime",set.getString("runTime"));
            movie.put("studio",set.getString("studio"));
            movie.put("publicationDate",set.getString("publicationDate"));
            movie.put("publisher",set.getString("publisher"));
            movies.add(movie);
        }
        return movies;
    }

    public JSONArray searchInOracle(String language) throws SQLException {
        JSONArray movies = new JSONArray();
        List<AmazonFact> amazonFacts =  amazonFactRepository.findAmazonFactsByTitle(language);
        for(AmazonFact a : amazonFacts){
            JSONObject movie = new JSONObject();
            movie.put("movieId",a.getMovieId());
            movie.put("title",a.getTitle());
            movie.put("releaseDate",a.getReleaseDate());
            movie.put("runTime",a.getRunTime());
            movie.put("studio",a.getStudio());
            movie.put("publicationDate",a.getPublicationDate());
            movie.put("publisher",a.getPublishier());
            movies.add(movie);
        }
        return movies;
    }


    public JSONArray searchLanuage(String language) throws SQLException {
        JSONArray movies = new JSONArray();
        List<AmazonFact> amazonFacts = amazonFactRepository.findAmazonFactByLanguage(language);
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
        return movies;
    }
}