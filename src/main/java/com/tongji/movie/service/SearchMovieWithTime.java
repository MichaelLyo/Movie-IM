package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SearchMovieWithTime {
    @Autowired
    private ConToOracle conObj;
    private ConnectToTimesten connectToTimesten;

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


    public JSONArray searchInOracle(String dateType,String date,String year,String[] monthArray, Boolean isOracle) throws SQLException {
        Connection con = null;
        JSONArray movies = new JSONArray();
        ResultSet set = null;

        if(isOracle){
            con = conObj.getConnection();
        }
        else {
            con = connectToTimesten.getConnection();
        }
        CallableStatement proc = con.prepareCall("{call select_time(?,?,?,?,?)}");

        for (int i = 0; i < monthArray.length; i++) {
            proc.setString(1, date);
            proc.setString(2, year);
            proc.setString(3, monthArray[i]);
            proc.setString(4, dateType);
            proc.registerOutParameter(5, OracleTypes.CURSOR);

            proc.execute();
            set = (ResultSet) proc.getObject(5);
            movies.addAll(OperationTool.getResult(set, "title", "title", "publicationDate", "publication_date", "releaseDate", "release_date", "runTime", "duration", "director", "director_name","formatName","format_name"));
        }

        if (0 == monthArray.length && !date.isEmpty()) {
            proc.setString(1,date);
            proc.setString(2, "");
            proc.setString(3, "");
            proc.setString(4, dateType);
            proc.registerOutParameter(5, OracleTypes.CURSOR);

            proc.execute();
            set = (ResultSet) proc.getObject(5);
            movies.addAll(OperationTool.getResult(set, "title", "title", "publicationDate", "publication_date", "releaseDate", "release_date", "runTime", "duration", "director", "director_name","formatName","format_name"));
        }
        return movies;
    }
}

