package com.tongji.movie.service;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConToHive {
    private static  String driveName = "org.apache.hive.jdbc.HiveDriver";
    public static final String DRIVE_NAME = driveName;

    public ConToHive() {

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
            con = DriverManager.getConnection("jdbc:hive2://10.60.42.201:10000/movies","hive","hive");
        } catch (SQLException e) {
            System.out.println("连接hive失败");
            e.printStackTrace();
        }
        return con;
    }
}
