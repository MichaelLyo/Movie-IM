package com.tongji.movie.service;
import com.sun.tools.corba.se.idl.constExpr.BooleanAnd;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithGenere {
   @Autowired
   private ConToOracle conObj;
   @Autowired
   private ConnectToTimesten connectToTimesten;

    public JSONArray searchInOracle(String typeName, Boolean isOracle) throws SQLException {
        JSONArray movies = null;
        Connection con = null;
        if(isOracle){
            con = conObj.getConnection();
        }
        else {
            con = connectToTimesten.getConnection();
        }
        CallableStatement proc = con.prepareCall("{call	search_by_genre_p(?,?)}");
        proc.setString(1,typeName);
        proc.registerOutParameter(2, OracleTypes.CURSOR);
        proc.execute();

        ResultSet set = (ResultSet) proc.getObject(2);
        movies = OperationTool.getResult(set,"title","title","releaseDate","release_date","director","director_name","runTime","duration","type","genre_name");
        return movies;
    }
}
