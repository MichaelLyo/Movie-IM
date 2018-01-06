package com.tongji.movie.service;


import java.sql.*;

public class ConToOracle {



    public static void main(String[] args){
        try{
            String url="jdbc:oracle:thin:@10.60.42.201:1521:XE";
            String username="movies";
            String password="movies";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn =null;
            conn= DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            ResultSet set = stmt.executeQuery("select * from amazon_fact a join actor b on (a.movie_id=b.movie_id)");
            while(set.next()){
                System.out.println(set.getString(1));
            }
            conn.close();
        }
        catch (Exception e){
                e.printStackTrace();
        }
    }



}
