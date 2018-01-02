package com.tongji.movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiveTest {
    private static  String driveName = "org.apache.hive.jdbc.HiveDriver";
    public static final String DRIVE_NAME = driveName;


    private Connection getConnection(){
        try {
            Class.forName(DRIVE_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("not found "+driveName);
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:hive2://192.168.1.100:10000/testdb","hive","hive");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Test
    public  void select() throws SQLException{
        //select
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("select * from usertable");

        System.out.println("result:");
        while(res.next()){
            System.out.print("name:"+res.getString(1));
            System.out.print("id:"+res.getInt(2));
            System.out.print("name:"+res.getString(3));
            System.out.println("\n");
        }
        con.close();
    }

    @Test
    public  void load() throws  SQLException{
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        stmt.execute("load data local inpath '/home/ibm/a.txt'"+"overwrite into TABLE usertable");
        System.out.println("create succeed");
        con.close();
    }
}
