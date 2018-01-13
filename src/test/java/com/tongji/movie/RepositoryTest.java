package com.tongji.movie;

import com.timesten.jdbc.TimesTenConnection;
import com.timesten.jdbc.TimesTenDataSource;
import com.tongji.movie.service.SearchMovieWithCombination;
import com.tongji.movie.service.SearchMovieWithRunTime;
import net.minidev.json.JSONArray;
import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RepositoryTest {


    @Autowired
    private SearchMovieWithRunTime searchMovieWithRunTime;

    @Autowired
    private SearchMovieWithCombination searchMovieWithCombination;


    public void testAmazonFact() throws Exception
    {
        JSONArray array = searchMovieWithCombination.searchInOracle("2000-12-03","Fire on the Amazon","Luis Llosa","Craig Sheffer","Adventure");
    }
    @Test
    public void getConnection()
    {
        //try {
        // Class.forName ("com.timesten.jdbc.TimesTenDriver");
        //} catch (ClassNotFoundException ex)
        //{
        // // 输出错误信息
        //}
        //
        //try {
        //
        // String URL = "jdbc:timesten:direct:DSN=movie_IM;uid=movies;pwd=movies";
        //
        // Connection conn = DriverManager.getConnection (URL);
        //
        // // 执行SQL语句
        //
        // //return conn;
        //
        // conn.close();
        //
        //} catch (SQLException ex) {
        // // 输出错误信息
        // ex.printStackTrace();
        //}
        ////return null;
        try {
            // create the TimesTen data source and set the connection URL

            System.load("/Users/lsl/Documents/Study/Timesten/TimesTen/IM_movie/lib/ttjdbc6.jar");
            TimesTenDataSource ttds = new TimesTenDataSource();
            //ttds.setUrl("jdbc:timesten:direct:DSN=movie_IM;uid=movies;pwd=movies");
            ttds.setUrl("jdbc:timesten:client:ttc_sever_dsn=movie_IM;ttc_server=192.168.118.198;tcp_port=53397;uid=movies;pwd=movies");

            // connect to the TimesTen database
            TimesTenConnection ttcon = (TimesTenConnection) ttds.getConnection();

            // create and execute the statement
            Statement stmt = ttcon.createStatement();
            ResultSet rset = stmt.executeQuery("select * from ACTOR");

            // process the result set
            while(rset.next()) {
                System.out.println("Value: " + rset.getInt(1));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
