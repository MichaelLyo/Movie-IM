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

    public JSONArray search(String date,String name,String actor,String director,String genre) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        String nameLike = '%' + name + '%';
        String actorLike = '%' + actor + '%';
        String directorLike = '%' + director + '%';
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a " +
                                                            "left join Actor ac on (a.movieid = ac.movieid) " +
                                                            "left join director d on (a.movieid = d.movieid) " +
                                                            "left join Generes g on (a.movieid = g.movieid) " +
                                                            "where a.releaseDate = ? and a.title = ? and ac.name = ? and d.name = ? and g.name = ?");
        pstmt.setString(1,date);
        pstmt.setString(2,nameLike);
        pstmt.setString(3,actorLike);
        pstmt.setString(4,directorLike);
        pstmt.setString(5,genre);
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
