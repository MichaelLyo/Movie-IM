package com.tongji.movie.service;

import com.tongji.movie.configure.OracleConnector;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithCombination {
    @Autowired
    private ConToOracle conObj;
    @Autowired ConnectToTimesten connectToTimesten;

    public JSONArray searchInOracle(String date,String name,String actor,String director,String genre,Boolean isOracle) throws SQLException {
        Connection con = null;
        if(isOracle){
            con = conObj.getConnection();
        }
        else{
           con = connectToTimesten.getConnection();
        }

        CallableStatement proc = con.prepareCall("{call	search_advanced_p(?,?,?,?,?,?)}");

        proc.setString(1,date);
        proc.setString(2,name);
        proc.setString(3,director);
        proc.setString(4,actor);
        proc.setString(5,genre);
        proc.registerOutParameter(6, OracleTypes.CURSOR);
        proc.execute();

        ResultSet set = (ResultSet)proc.getObject(6);
        return OperationTool.getResult(set,"title","title","actor","actor_name" ,"director","director_name", "genre","genre_name","releaseDate","release_date","runTime","duration");
    }



}
