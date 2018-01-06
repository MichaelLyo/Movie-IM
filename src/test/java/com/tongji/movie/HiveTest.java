package com.tongji.movie;

import org.hibernate.cfg.SecondaryTableSecondPass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.*;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiveTest {
    private static  String driveName = "org.apache.hive.jdbc.HiveDriver";
    public static final String DRIVE_NAME = driveName;
    String[] a  = new String[10];
    private Connection getConnection(){
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
            e.printStackTrace();
        }
        return con;
    }

//    @Test
//    public  void select() throws SQLException{
//        //select
//        Connection con = getConnection();
//        Statement stmt = con.createStatement();
//        ResultSet res = stmt.executeQuery("select * from usertable");
//
//        System.out.println("result:");
//        while(res.next()){
//            System.out.print("name:"+res.getString(1));
//            System.out.print("id:"+res.getInt(2));
//            System.out.print("name:"+res.getString(3));
//            System.out.println("\n");
//        }
//        con.close();
//    }

//    @Test
//    public  void load() throws  SQLException{
//        Connection con = getConnection();
//        Statement stmt = con.createStatem   ent();
//        stmt.execute("load data local inpath '/home/ibm/a.txt'"+"overwrite into TABLE usertable");
//        System.out.println("create succeed");
//        con.close();
//    }

    @Test
    public void createDB() throws SQLException{
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String createAmazonFact = "create table if not exists AmazonFact ( movieId  String  ,releaseDate String , publicationDate String , edition String ,title String ,format String ,publisher String ,runTime String ,studio String  )" +
                "comment 'fact table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createDirector = "create table if not exists director ( movieId String, name String )"+
                "comment 'director table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createStarring="create table if not exists Starring ( movieId String, name String)"+
                "comment 'starring table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createActor="create table if not exists Actor ( movieId String, name String)"+
                "comment 'actor table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createTimeDim = "create table if not exists TimeDim (timeId String, year int, month int, day int)" +
                "comment 'time table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createGeneres = "create table if not exists Generes ( movieId String, name String)" +
                "comment 'generes table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        String createLanguage  = "create table if not exists language( movieId String, name String, type String)" +
                "comment 'language table'" +
                "row format delimited " +
                "fields terminated by ','" +
                "lines terminated by '\n'" +
                "stored as  textfile";

        stmt.execute(createAmazonFact);
        stmt.execute(createDirector);
        stmt.execute(createStarring);
        stmt.execute(createActor);
        stmt.execute(createTimeDim);
        stmt.execute(createGeneres);
        stmt.execute(createLanguage);


        con.close();
    }

//    @Test
//    public void loadDB() throws SQLException {
//        Connection con = getConnection();
//        Statement stmt = con.createStatement();
//        stmt.executeQuery("load data local inpath '/home/ibm/a.txt' overwrite into table language");
//        con.close();
//    }

//    @Test
//    public void queryDB() throws SQLException{
//        Connection con = getConnection();
//        Statement stmt = con.createStatement();
//        ResultSet set = stmt.executeQuery("SELECT * from LANGUAGE WHERE languageid = '1'");
//        while(set.next()){
//            System.out.println("res:");
//            System.out.println(set.getMetaData());
//        }
//    }


    public ResultSet selectMovieByYear(int year) throws SQLException{
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a JOIN timedim b on(a.releaseDate = b.timeid) WHERE b.year=?");
        pstmt.setInt(1,year);
        ResultSet set =  pstmt.executeQuery();
        System.out.println(set);
        return set;
    }

    public ResultSet selectMovieByYearAndMonth(int year, int month)throws  SQLException{
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from AmazonFact a JOIN tiemdim b on(a.releaseDate = b.timeid) WHERE b.year=?and b.month=?");
        pstmt.setInt(1,year);
        pstmt.setInt(2, month);
        ResultSet set = pstmt.executeQuery();
        return set;
    }


}
