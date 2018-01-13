package com.tongji.movie.service;

import javolution.io.Struct;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithName {
    @Autowired
    private ConToOracle conObj;
    @Autowired
    private ConnectToTimesten connectToTimesten;



    public JSONArray searchInOracle(String title, Boolean isOracle) throws SQLException {
        Connection con = null;
        JSONArray movies = new JSONArray();

        if(isOracle){
            con = conObj.getConnection();
        }
        else {
            con = connectToTimesten.getConnection();
        }
        CallableStatement proc = con.prepareCall("{call select_moviename(?,?)}");

        proc.setString(1,title);
        proc.registerOutParameter(2, OracleTypes.CURSOR);

        proc.execute();
        ResultSet set = (ResultSet)proc.getObject(2);
        return OperationTool.getResult(set, "title","title","releaseDate","release_date","duration","duration","director","director_name","actor","actor_name","studio","studio_name");
    }

}
