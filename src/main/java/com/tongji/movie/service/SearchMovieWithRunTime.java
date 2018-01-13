package com.tongji.movie.service;

import javolution.io.Struct;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithRunTime {

    @Autowired
    private ConToHive conObj;
    @Autowired
    private ConnectToTimesten connectToTimesten;


    public JSONArray searchInOracle(String time1, String time2, Boolean isOracle) throws SQLException {
        JSONArray movies = new JSONArray();
        Connection con =  null;
        if(isOracle){
            con = conObj.getConnection();
        }
        else {
            con = connectToTimesten.getConnection();
        }
        try{

            CallableStatement proc = con.prepareCall("{call search_by_duration_p(?,?,?)}");

            proc.setInt(1,Integer.parseInt(time1));
            proc.setInt(2,Integer.parseInt(time2));
            proc.registerOutParameter(3, OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(3);
            movies = OperationTool.getResult(set,"title","title","runTime","duration","director","director_name","releaseDate","release_date");
        }
        catch (Exception e){
            System.out.println("searchByDuration");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }
}
