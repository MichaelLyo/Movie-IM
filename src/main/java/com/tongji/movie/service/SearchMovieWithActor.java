package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithActor
{
    @Autowired
    ConToOracle conObj;



    public JSONArray search(String actorName) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a join actor b on (a.movieid = b.movieid ) WHERE b.name = ?");
        pstmt.setString(1,actorName);
        ResultSet set =  pstmt.executeQuery();
        while(set.next()){
            JSONObject movie = new JSONObject();
            movie.put("movieId",set.getString("movieId"));
            movie.put("title",set.getString("title"));
            movie.put("actor",set.getString("b.name"));
            movie.put("releaseDate",set.getString("releaseDate"));
            movie.put("runTime",set.getString("runTime"));
            movie.put("studio",set.getString("studio"));
            movie.put("publicationDate",set.getString("publicationDate"));
            movie.put("publisher",set.getString("publisher"));
            movies.add(movie);
        }
        return movies;
    }

    public JSONArray searchInOracle(String actorName) throws SQLException {
        System.out.println(actorName);
        Connection con = conObj.getConnection();
        CallableStatement proc = con.prepareCall("{call select_actor(?,?)}");
        proc.setString(1,actorName);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();
        ResultSet set = (ResultSet) proc.getObject(2);
        return procTool.getResult(set,"title","title","actor","actor_name","releaseDate","release_date","runTime","duration","type","genre_name");
    }


    public JSONArray searchStarringInOracle(String starringName) throws SQLException{
        JSONArray movies = new JSONArray();
        return movies;
    }



}