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

import java.sql.Connection;
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
        JSONArray array = searchMovieWithCombination.searchInOracle("2000-12-03","Fire on the Amazon","Luis Llosa","Craig Sheffer","Adventure",true);
    }
    @Test
    public  void  getConnection()
    {
        try {
            TimesTenDataSource ttds = new TimesTenDataSource();
            ttds.setUrl("jdbc:timesten:client:TTC_SERVER_DSN=movie_IM;TTC_SERVER=192.168.1.106;TCP_PORT=53393;uid=movies;pwd=movies");

            // connect to the TimesTen database
            Connection ttcon = ttds.getConnection();
//            return ttcon;
            // create and execute the statement
//			 Statement stmt = ttcon.createStatement();
//			 ResultSet rset = stmt.executeQuery("select * from ACTOR");
//
//			 // process the result set
//			 while(rset.next()) {
//				 System.out.println("Value: " + rset.getInt(1));
//			 }
        } catch(SQLException e) {
            System.out.println("ConnectToTimesten");
            e.printStackTrace();
//            return null;
        }
    }
}
