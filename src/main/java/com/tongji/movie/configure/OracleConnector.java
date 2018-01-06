package com.tongji.movie.configure;

import java.sql.*;

/**
 * Created by LSL on 2018/1/6
 */
public class OracleConnector
{
	public Connection getConnection(){
		try{
			String url="jdbc:oracle:thin:@10.60.42.201:1521:XE";
			String username="movies";
			String password="movies";
			Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection(url,username,password);

			return connection;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
