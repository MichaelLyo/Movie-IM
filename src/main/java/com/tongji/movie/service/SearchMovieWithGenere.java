package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithGenere {
   @Autowired
   private ConToOracle conObj;


    public JSONArray search(String typeName) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact  a join generes g on (a.movieid = g.movieid)   WHERE g.name=?");
        pstmt.setString(1,typeName);
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
            movie.put("type",typeName);
            movies.add(movie);
        }
        return movies;
    }

    public JSONArray searchInOracle(String typeName) throws SQLException {
        JSONArray movies = null;
        Connection con = conObj.getConnection();
        CallableStatement proc = con.prepareCall("{call	search_by_genre_p(?,?)}");
        proc.setString(1,typeName);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();

        ResultSet set = (ResultSet) proc.getObject(2);
        movies = OperationTool.getResult(set,"title","title","releaseDate","release_date","director","director_name","runTime","duration","type","genre_name");
        return movies;
    }
}
