package com.tongji.movie.service;
import com.tongji.movie.configure.OracleConnector;
import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SearchMovieWithDirector {
    @Autowired
    private ConToHive conObj;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OracleConnector oracleConnector;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

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

        List<AmazonFact> amazonFacts =  amazonFactRepository.findAmazonFactsByDirector(directorName);
        if(amazonFacts != null) {
            for (AmazonFact a : amazonFacts) {
                JSONObject movie = new JSONObject();
                movie.put("movieId", a.getMovieId());
                movie.put("title", a.getTitle());
                movie.put("releaseDate", a.getReleaseDate());
                movie.put("runTime", a.getRunTime());
                movie.put("studio", a.getStudio());
                movie.put("publicationDate", a.getPublicationDate());
                movie.put("publisher", a.getPublishier());
                movie.put("director", directorName);
                movies.add(movie);
            }

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
        Connection con = oracleConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement("select a.movie_id,a.title,d.name as director_name,g.name as genre_name,ac.name as actor_name from amazon_fact a inner join director d on (a.movie_id = d.movie_id) inner JOIN genre g on (a.movie_id = g.movie_id) inner join actor ac on (a.movie_id = ac.movie_id) WHERE d.name=?");
        pstmt.setString(1,directorName);
        ResultSet set =  pstmt.executeQuery();

        while(set.next()){

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("movieId",set.getString("movie_id"));
            jsonObject.put("title",set.getString("title"));
            jsonObject.put("director",set.getString("director_name"));
            jsonObject.put("actor",set.getString("actor_name"));
            jsonObject.put("genre",set.getString("genre_name"));
            movies.add(jsonObject);
        }
        return movies;
    }
}
