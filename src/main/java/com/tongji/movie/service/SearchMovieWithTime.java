package com.tongji.movie.service;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithTime {
    @Autowired
    private ConToHive conObj;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

    public JSONArray search(String dateType,String date,String year,String[] seasonArray,String[] monthArray,String[] dayArray) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();

        Integer nyear = null;
        Integer[] days = null;
        Integer[] months = null;
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("");
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
                pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.publicationdate=?");
                pstmt.setString(1,date);
            }
            else{
                if(days == null){ //没有天数
                    if(months  == null){ //没有月份或者季度
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.publicationdate = d.timeid where d.year = ?1");
                        pstmt.setInt(1,nyear);
                    }
                    //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationMonthIn(nyear, months);
                    pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.month IN ?2");
                    pstmt.setInt(1,nyear);
                    Array intArr = con.createArrayOf("int", months);
                    pstmt.setArray(2,intArr);
                }
                else{
                    if(months != null) {
                        //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, months, days);
                        pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.month IN ?2 and d.week IN ?3");
                        pstmt.setInt(1,nyear);
                        Array intArr = con.createArrayOf("int", months);
                        pstmt.setArray(2,intArr);
                        Array intArr2 = con.createArrayOf("int", days);
                        pstmt.setArray(3,intArr2);
                    }
                    else{
                        //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, days);
                        pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.week IN ?2");
                        pstmt.setInt(1,nyear);
                        Array intArr2 = con.createArrayOf("int", days);
                        pstmt.setArray(2,intArr2);
                    }
                }
            }
        }
        else{
            if(!date.isEmpty()){
                //amazonFacts =  amazonFactRepository.findAmazonFactsByReleaseDate(date);
                pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.releasedate=?");
                pstmt.setString(1,date);
            }
            else{
                if(days == null){
                    if(months == null){
                        //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseYear(nyear);
                        pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1");
                        pstmt.setInt(1,nyear);

                    }
                    //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseMonthIn(nyear, months);
                    pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.month IN ?2");
                    pstmt.setInt(1,nyear);
                    Array intArr = con.createArrayOf("int", months);
                    pstmt.setArray(2,intArr);
                }
                else{
                    if(months != null) {
                        //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, months, days);
                        pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.month IN ?2 and d.week IN ?3");
                        pstmt.setInt(1,nyear);
                        Array intArr = con.createArrayOf("int", months);
                        pstmt.setArray(2,intArr);
                        Array intArr2 = con.createArrayOf("int", days);
                        pstmt.setArray(3,intArr2);
                    }
                    else{
                        //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, days);
                        pstmt = con.prepareStatement("select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.week IN ?2");
                        pstmt.setInt(1,nyear);
                        Array intArr2 = con.createArrayOf("int", days);
                        pstmt.setArray(2,intArr2);
                    }
                }
            }
        }




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

