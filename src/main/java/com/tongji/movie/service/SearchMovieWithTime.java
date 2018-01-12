package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class SearchMovieWithTime {
    @Autowired
    private ConToOracle conObj;

    public String generateSQLParameter(Integer[] item)
    {
        if(item!=null)
        {
            String result = "(";
            for (int i = 0; i < item.length; i++)
            {
                if (i + 1 == item.length) {
                    result+="?)";
                } else {
                    result+="?,";
                }

            }
            return result;
        }
        return "()";
    }
    public void addParameter(PreparedStatement pstmt,Integer[] months,Integer[] days) throws SQLException
    {
        int count = 2;
        if(months!=null)
        {
            int leng = months.length;
            for(int i=0;i<months.length;i++)
            {
                if(pstmt!=null)
                {
                    if(months[i]!=null){
                        pstmt.setInt(count++,months[i]);
                    }
                    else {
                        pstmt.setInt(count++,-1);
                    }
                }
            }
        }
        if(days!=null)
        {
            for(int i=0;i<days.length;i++)
            {
                if(pstmt!=null)
                {
                    if(days[i]!=null){
                        pstmt.setInt(count++,days[i]);
                    }
                    else {
                        pstmt.setInt(count++,-1);
                    }
                }
            }
        }
    }

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
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.publicationdate = d.timeid where d.year = ?");
                        pstmt.setInt(1,nyear);
                    }
                    //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationMonthIn(nyear, months);
                    pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.publicationdate = d.timeid where d.year = ? and d.month IN "+generateSQLParameter(months));
                    pstmt.setInt(1,nyear);
                    addParameter(pstmt,months,null);
                }
                else{
                    if(months != null) {
                        //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, months, days);
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.publicationdate = d.timeid where d.year = ? and d.month IN "+generateSQLParameter(months)+" and d.week IN "+generateSQLParameter(days));
                        pstmt.setInt(1,nyear);
                        addParameter(pstmt,months,days);
                    }
                    else{
                        //amazonFacts = amazonFactRepository.findAmazonFactsByPublicationDayIn(nyear, days);
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.publicationdate = d.timeid where d.year = ? and d.week IN "+generateSQLParameter(days));
                        pstmt.setInt(1,nyear);
                        addParameter(pstmt,null,days);
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
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.releasedate = d.timeid where d.year = ?");
                        pstmt.setInt(1,nyear);

                    }
                    //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseMonthIn(nyear, months);
                    pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.releasedate = d.timeid where d.year = ? and d.month IN "+generateSQLParameter(months));
                    pstmt.setInt(1,nyear);
                    addParameter(pstmt,months,null);
                }
                else{
                    if(months != null) {
                        //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, months, days);
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.releasedate = d.timeid where d.year = ? and d.month IN "+generateSQLParameter(months)+ "and d.week IN "+generateSQLParameter(days));
                        pstmt.setInt(1,nyear);
                        addParameter(pstmt,months,days);
                    }
                    else{
                        //amazonFacts = amazonFactRepository.findAmazonFactsByReleaseDayIn(nyear, days);
                        pstmt = con.prepareStatement("select * from amazonfact a join timedim d on a.releasedate = d.timeid where d.year = ? and d.week IN "+generateSQLParameter(days));
                        addParameter(pstmt,null,days);
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

    public JSONArray searchInOracle(String dateType,String date,String year,String[] monthArray) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        ResultSet set = null;
        CallableStatement proc = con.prepareCall("{call select_time(?,?,?,?,?)}");

        for (int i = 0; i < monthArray.length; i++) {
            proc.setString(1, date);
            proc.setString(2, year);
            proc.setString(3, monthArray[i]);
            proc.setString(4, dateType);
            proc.registerOutParameter(5, OracleTypes.CURSOR);

            proc.execute();
            set = (ResultSet) proc.getObject(5);
            movies.addAll(procTool.getResult(set, "title", "title", "publicationDate", "publication_date", "releaseDate", "release_date", "runTime", "duration", "director", "director_name","formatName","format_name"));
        }

        if (0 == monthArray.length && !date.isEmpty()) {
            proc.setString(1,date);
            proc.setString(2, "");
            proc.setString(3, "");
            proc.setString(4, dateType);
            proc.registerOutParameter(5, OracleTypes.CURSOR);

            proc.execute();
            set = (ResultSet) proc.getObject(5);
            movies.addAll(procTool.getResult(set, "title", "title", "publicationDate", "publication_date", "releaseDate", "release_date", "runTime", "duration", "director", "director_name","formatName","format_name"));
        }
        return movies;
    }
}

