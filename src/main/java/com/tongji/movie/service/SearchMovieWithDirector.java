package com.tongji.movie.service;
import com.tongji.movie.configure.OracleConnector;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithDirector {
    @Autowired
    private ConToHive conObj;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OracleConnector oracleConnector;

    @Autowired
    ConToOracle conToOracle;
    @Autowired
    ConnectToTimesten connectToTimesten;



    public JSONArray searchInOracle(String directorName, Boolean isOracle) throws SQLException {
        Connection con = null;
        if(isOracle){
            con = conObj.getConnection();
        }
        else{
            con = connectToTimesten.getConnection();
        }

        JSONArray movies = new JSONArray();
        try{
            CallableStatement proc = con.prepareCall("{call select_director(?,?)}");
            proc.setString(1,directorName);
            proc.registerOutParameter(2, OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(2);
            movies = OperationTool.getResult(set,"title","title","director","director_name","releaseDate","release_date","runTime","duration","formatName","format_name");
            //			return OperationTool.getResult(set,"");
        }
        catch (Exception e){
            System.out.println("selectDirector");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }



    public JSONArray searchCoActorInOracle(String directorName,Boolean isOracle) throws SQLException
    {
        Connection con = null;
        JSONArray movies = new JSONArray();
        if(isOracle){
            con = conObj.getConnection();
        }
        else {
            con = connectToTimesten.getConnection();
        }
        try{

            CallableStatement proc = con.prepareCall("{call select_coactor(?,?)}");
            proc.setString(1,directorName);
            proc.registerOutParameter(2,OracleTypes.CURSOR);
            proc.execute();

            ResultSet set = (ResultSet) proc.getObject(2);
            movies = OperationTool.getResult(set,"title","title","director","director_name","actor","actor_name","genre","genre_name");
        }
        catch (Exception e){
            System.out.println("selectCoActor");
            e.printStackTrace();
            //			return null;
        }
        return movies;
    }
}
