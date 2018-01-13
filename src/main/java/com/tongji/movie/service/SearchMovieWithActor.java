package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.*;

@Component
public class SearchMovieWithActor
{
    @Autowired
    ConToOracle conObj;
    @Autowired
    ConnectToTimesten connectToTimesten;
    public JSONArray searchInOracle(String actorName,Boolean isOracle) throws SQLException {
        Connection con = null;
        if(isOracle){
           con = conObj.getConnection();
        }
        else{
            con = connectToTimesten.getConnection();
        }
        System.out.println(actorName);
        CallableStatement proc = con.prepareCall("{call select_actor(?,?)}");
        proc.setString(1,actorName);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();
        ResultSet set = (ResultSet) proc.getObject(2);
        return OperationTool.getResult(set,"title","title","actor","actor_name","releaseDate","release_date","runTime","duration","type","genre_name");
    }

}