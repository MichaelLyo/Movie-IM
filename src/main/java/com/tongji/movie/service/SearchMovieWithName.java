package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.hibernate.secure.internal.JaccSecurityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithName {
    @Autowired
    private ConToOracle conObj;

    public  JSONArray search(String title) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.title=?");
        pstmt.setString(1,title);
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

    public JSONArray searchInOracle(String title) throws SQLException {
        Connection con = conObj.getConnection();
        CallableStatement proc = con.prepareCall("{call select_moviename(?,?)}");

        proc.setString(1,title);
        proc.registerOutParameter(2, OracleTypes.CURSOR);

        proc.execute();
        ResultSet set = (ResultSet)proc.getObject(2);
        return procTool.getResult(set, "title","title","releaseDate","release_date","duration","duration","director","director_name","actor","actor_name","studio","studio_name");
    }

}
