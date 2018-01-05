package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.hibernate.secure.internal.JaccSecurityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SearchMovieWithName {
    @Autowired
    private ConToHive conObj;

    public  JSONArray search(String title) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.title=?");
        pstmt.setString(1,title);
        ResultSet set =  pstmt.executeQuery();
        while(set.next()){
            JSONObject movie = new JSONObject();
            movie.put("name",set.getString("title"));
            movie.put("time",set.getString("releaseDate"));
            movie.put("genre",set.getString("runTime"));
            movie.put("director",set.getString("studio"));
            movie.put("actor",set.getString("publicationDate"));
            movie.put("version",set.getString("publicationDate"));
            movies.add(movie);
            System.out.println(set.getString("releaseDate"));
        }
        return movies;
    }

}
