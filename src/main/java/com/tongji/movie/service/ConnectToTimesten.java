package com.tongji.movie.service;

import com.timesten.jdbc.TimesTenDataSource;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.timesten.jdbc.TimesTenDriver;
//import com.timesten.jdbc.
import com.timesten.jdbc.TimesTenDataSource;
import com.timesten.jdbc.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by LSL on 2018/1/13
 */
@Component
public class ConnectToTimesten
{
	public ConnectToTimesten() {

	}


	 public Connection getConnection()
	 {
		 try {
			 TimesTenDataSource ttds = new TimesTenDataSource();
			 ttds.setUrl("jdbc:timesten:client:TTC_SERVER_DSN=movie_IM;TTC_SERVER=192.168.118.198;TCP_PORT=53393;uid=movies;pwd=movies");

			 // connect to the TimesTen database
			 Connection ttcon = ttds.getConnection();
			 return ttcon;
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
			 return null;
		 }
	 }
}
