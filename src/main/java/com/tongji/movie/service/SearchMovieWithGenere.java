package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SearchMovieWithGenere {
   @Autowired
   private ConToHive conObj;

    public JSONArray search(String typeName) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact  a join generes g on (a.movieid = g.movieid)   WHERE g.name=?");
        pstmt.setString(1,typeName);
        ResultSet set =  pstmt.executeQuery();
        int i = 0;
        while(set.next()){
            i++;
            if(i>100)
                break;
            JSONObject movie = new JSONObject();
            movie.put("movieId",set.getString("movieId"));
            movie.put("title",set.getString("title"));
            movie.put("releaseDate",set.getString("releaseDate"));
            movie.put("runTime",set.getString("runTime"));
            movie.put("studio",set.getString("studio"));
            movie.put("publicationDate",set.getString("publicationDate"));
            movie.put("publisher",set.getString("publisher"));
            movie.put("type",typeName);
            movies.add(movie);
        }
        return movies;
    }
}