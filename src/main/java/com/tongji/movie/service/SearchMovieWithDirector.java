package com.tongji.movie.service;
import com.tongji.movie.configure.OracleConnector;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithDirector {
    @Autowired
    private ConToHive conObj;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OracleConnector oracleConnector;

    @Autowired
    ConToOracle conToOracle;


    public JSONArray search(String directorName) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact  a join director g on (a.movieid = g.movieid)   WHERE g.name=?");
        pstmt.setString(1,directorName);
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
            movie.put("director",directorName);
            movies.add(movie);
        }
        return movies;
    }


    public JSONArray searchInOracle(String directorName) throws SQLException {

        JSONArray movies = new JSONArray();
        try{
            Connection con = conToOracle.getConnection();
            CallableStatement proc = con.prepareCall("{call select_director(?,?)}");
            proc.setString(1,directorName);
            proc.registerOutParameter(2, OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(2);
            movies = OperationTool.getResult(set,"title","title","director","director_name","releaseDate","release_date","runTime","duration","formatName","format_name");
            //			return OperationTool.getResult(set,"");
        }
        catch (Exception e){
            System.out.println("selectDirector");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }

    public JSONArray searchCoActor(String directorName) throws SQLException
    {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a inner join director d on (a.movieid = d.movieid) inner JOIN Generes g on (a.movieid = g.movieid) inner join actor ac on (a.movieid = ac.movieid) WHERE d.name=?");
        pstmt.setString(1,directorName);
        ResultSet set =  pstmt.executeQuery();
        int i = 0;
        while(set.next()){
            i++;
            if(i>100)
                break;
            JSONObject movie = new JSONObject();

            movie.put("movieId",set.getString("movieId"));
            movie.put("title",set.getString("title"));
            movie.put("director",set.getString("d.name"));
            movie.put("actor",set.getString("ac.name"));
            movie.put("genre",set.getString("g.name"));
            movies.add(movie);
        }
        return movies;
    }

    public JSONArray searchCoActorInOracle(String directorName) throws SQLException
    {
        JSONArray movies = new JSONArray();
        try{
            Connection con = conToOracle.getConnection();
            CallableStatement proc = con.prepareCall("{call select_coactor(?,?)}");
            proc.setString(1,directorName);
            proc.registerOutParameter(2,OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(2);
            movies = OperationTool.getResult(set,"title","title","director","director_name","actor","actor_name","genre","genre_name");
        }
        catch (Exception e){
            System.out.println("selectCoActor");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }
}
