package com.tongji.movie.service;

import com.tongji.movie.configure.OracleConnector;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithCombination {
    @Autowired
    private ConToOracle conObj;
    @Autowired
    OracleConnector oracleConnector;


    public JSONArray search(int opType,String date,String name,String actor,String director,String genre) throws SQLException {
        Connection con ;
        String sql;
        //hive连接
        if (opType==0)
        {
            con = conObj.getConnection();
            sql = "select * from AmazonFact a " +
                    "INNER join Actor ac on (a.movieid = ac.movieid) " +
                    "INNER join director d on (a.movieid = d.movieid) "+
                    "INNER join Generes g on (a.movieid = g.movieid) "+
                    "where 1=1 ";
        }
        //oracle连接
        else {
            con = oracleConnector.getConnection();
            sql = "select a.movie_Id as movie_id,a.title as title,ac.name as act,g.name as gen,d.name as dir,a.release_Date as release_Date,a.run_Time as run_Time  from Amazon_Fact a " +
                    "INNER join Actor ac on (a.movie_id = ac.movie_id) " +
                    "INNER join director d on (a.movie_id = d.movie_id) "+
                    "INNER join genre g on (a.movie_id = g.movie_id) "+
                    "where 1=1 ";
        }

        JSONArray movies = new JSONArray();
        String nameLike = '%' + name + '%';
        String actorLike = '%' + actor + '%';
        String directorLike = '%' + director + '%';


        int count=1;
        if (!date.isEmpty())
        {
            if(opType==0)
            {
                sql+="and a.releasedate = ? ";
            }
            else {
                sql+="and a.release_date = ? ";
            }
        }
        if (!name.isEmpty())
        {
            sql+="and a.title = ? ";
        }
        if(!actor.isEmpty())
        {
            sql+= "and ac.name = ? ";
        }
        if (!director.isEmpty())
        {
            sql+="and d.name = ?";
        }
        if (!genre.isEmpty())
        {
            sql+="and g.name = ?";
        }
        PreparedStatement pstmt = con.prepareStatement(sql);
        if (!date.isEmpty())
        {
            pstmt.setString(count++,date);
        }
        if (!name.isEmpty())
        {
            pstmt.setString(count++,name);
        }
        if(!actor.isEmpty())
        {
            pstmt.setString(count++,actor);
        }
        if (!director.isEmpty())
        {
            pstmt.setString(count++,director);
        }
        if (!genre.isEmpty())
        {
            pstmt.setString(count++,genre);
        }

        ResultSet set =  pstmt.executeQuery();
        while(set.next()){
            JSONObject movie = new JSONObject();

            if(opType==0)
            {
                movie.put("movieId",set.getString("movieId"));
                movie.put("releaseDate",set.getString("releaseDate"));
                movie.put("runTime",set.getString("runTime"));
                movie.put("genre", set.getString("g.name"));

                movie.put("title",set.getString("title"));
                movie.put("director",set.getString("d.name"));
                movie.put("actor",set.getString("ac.name"));
            }
            else {
                movie.put("movieId",set.getString("movie_Id"));
                movie.put("releaseDate",set.getString("release_Date"));
                movie.put("runTime",set.getString("run_Time"));
                movie.put("genre", set.getString("gen"));

                movie.put("title",set.getString("title"));
                movie.put("director",set.getString("dir"));
                movie.put("actor",set.getString("act"));
            }

            movies.add(movie);
        }
        return movies;
    }

    public JSONArray searchInOracle(String date,String name,String actor,String director,String genre) throws SQLException {

        Connection con = conObj.getConnection();
        CallableStatement proc = con.prepareCall("{call	search_advanced_p(?,?,?,?,?,?)}");

        proc.setString(1,date);
        proc.setString(2,name);
        proc.setString(3,director);
        proc.setString(4,actor);
        proc.setString(5,genre);
        proc.registerOutParameter(6, OracleTypes.CURSOR);
        proc.execute();

        ResultSet set = (ResultSet)proc.getObject(6);
        return procTool.getResult(set,"title","title","actor","actor_name" ,"director","director_name", "genre","genre_name","releaseDate","release_date","runTime","duration");
    }



}
