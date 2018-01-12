package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithRunTime {

    @Autowired
    private ConToHive conObj;


    public JSONArray search(String time1, String time2) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.runTime BETWEEN ? and ?");
        pstmt.setString(1,time1);
        pstmt.setString(2,time2);
        ResultSet set =  pstmt.executeQuery();
        try
        {
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
        }
        catch (Exception e)
        {
            
        }
        return movies;
    }

    public JSONArray searchInOracle(String time1, String time2) throws SQLException {
        JSONArray movies = new JSONArray();
        try{
            Connection con =  conObj.getConnection();
            CallableStatement proc = con.prepareCall("{call search_by_duration_p(?,?,?)}");

            proc.setInt(1,Integer.parseInt(time1));
            proc.setInt(2,Integer.parseInt(time2));
            proc.registerOutParameter(3, OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(3);
            movies = procTool.getResult(set,"title","title","runTime","duration","director","director_name","releaseDate","release_date");
        }
        catch (Exception e){
            System.out.println("searchByDuration");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }
}
