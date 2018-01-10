package com.tongji.movie;

import com.tongji.movie.service.ConToOracle;
import com.tongji.movie.service.SearchMovieWithDirector;
import oracle.jdbc.internal.OracleTypes;
import org.hsqldb.lib.HsqlHeap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by LSL on 2018/1/6
 */
import java.sql.*;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTest {

	@Autowired
	ConToOracle conObj;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void createTable() {
		Connection con = conObj.getConnection();
	}

	@Test
	public void searchBylanguage(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call search_by_language_p(?)}");
			proc.registerOutParameter(1,OracleTypes.CURSOR);
			proc.execute();
			ResultSet set =(ResultSet)proc.getObject(1);
			while(set.next()){
				System.out.println(set.getString("title"));
			}
		}
		catch(Exception e){

		}
	}


	public void selectByGenre(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	search_by_genre_p(?)}");
			proc.registerOutParameter(1,OracleTypes.CURSOR);
			proc.execute();
			ResultSet set = (ResultSet) proc.getObject(1);
			while(set.next()){
				System.out.println(set.getString(1));
			}
			set.close();
		}
		catch (Exception e){
			System.out.println("selectByGenre");
			e.printStackTrace();
		}
	}

	public void insertActor() {
		try {
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	select_actor(?,?)}");
			proc.setString(1, "jonathan");
			proc.registerOutParameter(2, OracleTypes.CURSOR);
			proc.execute();
			ResultSet set = (ResultSet) proc.getObject(2);
			while (set.next()) {
				System.out.println(set.getString(1));
				System.out.println(set.getString(2));
			}
			set.close();
		} catch (Exception e) {
			System.out.println("insert_actor");
			e.printStackTrace();
		}
	}


}
