package com.tongji.movie.service;

import javolution.io.Struct;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SearchMovieWithTime {
    @Autowired
    private ConToHive conObj;
    @Autowired
    private PutAttributesToJson putAttributesToJson;
    public JSONArray searchMVByDate(Boolean isPublicationDate, String date) throws SQLException {
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = null;
        if(isPublicationDate){
            pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.publicationDate=?");
            pstmt.setString(1,date);
        }
        else{
            pstmt = con.prepareStatement("select * from AmazonFact a  WHERE a.releaseDate=?");
            pstmt.setString(1,date);
        }

        ResultSet set =  pstmt.executeQuery();
        movies = putAttributesToJson.put(set,"");
        return movies;
    }
    public JSONArray searchMvByYearAndMon (Boolean isPublicationDate,int year, int mon) throws SQLException{
        Connection con = conObj.getConnection();
        JSONArray movies = new JSONArray();
        PreparedStatement pstmt = null;

        if(isPublicationDate){
            pstmt = con.prepareStatement("select * from amazonfact a join timedim b on (a.publicationDate = b.timeid) where b.year=? AND  b.month = ?");
            pstmt.setInt(1,year);
            pstmt.setInt(2,mon);
        }
        else{
            pstmt = con.prepareStatement("select * from amazonfact a join timedim b on (a.publicationDate = b.timeid) where b.year=? AND  b.month = ?");
            pstmt.setInt(1,year);
            pstmt.setInt(2,mon);
        }

        ResultSet set =  pstmt.executeQuery();
        movies = putAttributesToJson.put(set,"");
        return movies;
    }
}
