package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithLanguage {
    @Autowired
    private ConToOracle conObj;
    @Autowired
    private ConnectToTimesten connectToTimesten;



    public JSONArray searchInOracle(String language, Boolean isOracle) throws SQLException {
        Connection con = null;
        if(isOracle){
            con = conObj.getConnection();
        }
        else{
            con = connectToTimesten.getConnection();
        }
        CallableStatement proc = con.prepareCall("{call search_by_language_p(?,?)}");
        proc.setString(1,language);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();
        ResultSet set =(ResultSet)proc.getObject(2);
        return OperationTool.getResult(set,"title","title","language","language_name","releaseDate","release_date","director","director_name");
    }

}
