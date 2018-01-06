package com.tongji.movie.service;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component
public class SearchMovieWithTime {
    @Autowired
    private ConToHive conObj;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

    public JSONArray searchMVByPublication(String publicationDate) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.title=?");
        pstmt.setString(1,publicationDate);
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

    public JSONArray searchInOracle(String dateType,String date,String year,String[] seasonArray,String[] monthArray,String[] dayArray) throws SQLException {
        JSONArray movies = new JSONArray();
        List<AmazonFact> amazonFacts = null;
        Integer yearNum = Integer.parseInt(year);
        List<Integer> monthList = null;
        if(Integer.parseInt(dateType) == 0){
            if(!date.isEmpty()){
                amazonFacts =  amazonFactRepository.findAmazonFactsByPublicationDate(date);
            }
            else{
                if(dayArray.length == 0){
                    if(monthArray.length == 0){
                        if(seasonArray.length == 0){
                            amazonFacts = amazonFactRepository.findAmazonFactsByPublicationYear(year);
                        }
                        else {
                            amazonFacts = amazonFactRepository.findAmazonFactsByPublicationQuarter()
                        }
                    }
                    else{
                        //amazonFacts = amazonFactRepository
                    }
                }

            }
        }
        else{
            if(!date.isEmpty()){
                amazonFacts =  amazonFactRepository.findAmazonFactsByReleaseDate(date);
            }
            else{

            }
        }
        for(AmazonFact a : amazonFacts){
            JSONObject movie = new JSONObject();
            movie.put("movieId",a.getMovieId());
            movie.put("title",a.getTitle());
            movie.put("releaseDate",a.getReleaseDate());
            movie.put("runTime",a.getRunTime());
            movie.put("studio",a.getStudio());
            movie.put("publicationDate",a.getPublicationDate());
            movie.put("publisher",a.getPublishier());
            movies.add(movie);
        }
        return movies;
    }



}
