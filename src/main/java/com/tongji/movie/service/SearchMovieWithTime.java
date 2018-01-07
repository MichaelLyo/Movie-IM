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
        Integer nyear = null;
        Integer[] days = null;
        Integer[] months = null;
        if(date.length() == 0) {
            nyear = Integer.parseInt(year);
            if (monthArray.length == 0 && seasonArray.length != 0) {
                months = new Integer[12];
                for (int i = 0, j = 0; i < seasonArray.length; i++) {
                    if (seasonArray[i].equals("春")) {
                        months[j] = 1;
                        months[j + 1] = 2;
                        months[j + 2] = 3;
                        j = j + 3;
                    } else if (seasonArray[i].equals("夏")) {
                        months[j] = 4;
                        months[j + 1] = 5;
                        months[j + 2] = 6;
                        j = j + 3;
                    } else if (seasonArray[i].equals("秋")) {
                        months[j] = 7;
                        months[j + 1] = 8;
                        months[j + 2] = 9;
                        j = j + 3;
                    } else if (seasonArray[i].equals("冬")) {
                        months[j] = 10;
                        months[j + 1] = 11;
                        months[j + 2] = 12;
                        j = j + 3;
                    }
                }
            } else if (monthArray.length != 0 && seasonArray.length == 0){
                months = new Integer[12];
                for (int i = 0; i < monthArray.length; i++) {
                    months[i] = Integer.parseInt(monthArray[i]);
                }
            }
            if(dayArray.length != 0) {
                days = new Integer[31];
                for (int i = 0; i < dayArray.length; i++) {
                    days[i] = Integer.parseInt(dayArray[i]);
                }
            }
        }
        if(Integer.parseInt(dateType) == 0){
            if(date.length() != 0){
                amazonFacts =  amazonFactRepository.findAmazonFactsByPublicationDate(date);
            }
            else{
                if(days == null){ //没有天数
                    if(months  == null){ //没有月份或者季度
                        amazonFacts = amazonFactRepository.findAmazonFactsByPublicationYear(nyear);
                    }
                    amazonFacts = amazonFactRepository.findAmazonFactsByPublicationMonthIn(nyear, months);
                }
                else{
                    if(months != null) {
                        amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, months, days);

                    }
                    else{
                        amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, days);
                    }
                }
            }
        }
        else{
            if(!date.isEmpty()){
                amazonFacts =  amazonFactRepository.findAmazonFactsByReleaseDate(date);
            }
            else{
                if(days == null){
                    if(months == null){
                        amazonFacts = amazonFactRepository.findAmazonFactsByReleaseYear(nyear);
                    }
                    amazonFacts = amazonFactRepository.findAmazonFactsByReleaseMonthIn(nyear, months);
                }
                else{
                    if(months != null) {
                        amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, months, days);
                    }
                    else{
                        amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, days);
                    }
                }
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

