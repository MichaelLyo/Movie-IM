package com.tongji.movie.service;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class ConToOracle {
    private static  String driveName = "oracle.jdbc.OracleDriver";
    public static final String DRIVE_NAME = driveName;

    public ConToOracle(){

    }

    public  Connection getConnection(){
        try {
            Class.forName(DRIVE_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("not found "+driveName);
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@10.60.42.201:1521:XE","movies","movies");
        } catch (SQLException e) {
            System.out.println("连接Oracle失败");
            e.printStackTrace();
        }
        return con;
    }
}
