package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
@Component
public class SearchMovieWithLanguage {
    @Autowired
    private ConToOracle conObj;


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
        Connection con = conObj.getConnection();
        CallableStatement proc = con.prepareCall("{call search_by_language_p(?,?)}");
        proc.setString(1,language);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();
        ResultSet set =(ResultSet)proc.getObject(2);
        return procTool.getResult(set,"title","title","language","language_name","releaseDate","release_date","director","director_name");
    }


    public JSONArray searchLanuage(String language) throws SQLException {
        JSONArray movies = new JSONArray();
        return movies;
    }
}
