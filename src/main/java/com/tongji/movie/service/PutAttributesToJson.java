package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PutAttributesToJson {
    public PutAttributesToJson() {
    }

    public JSONArray put(ResultSet set, String... args)throws SQLException{
        JSONArray movies = new JSONArray();
        while(set.next()){
            JSONObject movie = new JSONObject();
            movie.put("movieId",set.getString("movieId"));
            movie.put("title",set.getString("title"));
            movie.put("releaseDate",set.getString("releaseDate"));
            movie.put("runTime",set.getString("runTime"));
            movie.put("studio",set.getString("studio"));
            movie.put("publicationDate",set.getString("publicationDate"));
            movie.put("publisher",set.getString("publisher"));
            movie.put("format", set.getString("format"));
            movie.put("edition",set.getString("edition"));

            for(int i = 0; i < args.length; i++){
                movie.put(args[i],set.getString(args[i]));
            }
            movies.appendElement(movie);
        }
        return movies;
    }
}
