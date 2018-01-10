package com.tongji.movie;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.tongji.movie.service.ConToOracle;
import com.tongji.movie.service.SearchMovieWithDirector;
import com.tongji.movie.service.procTool;
import net.minidev.json.JSONArray;
import oracle.jdbc.internal.OracleTypes;
import org.hsqldb.lib.HsqlHeap;
import org.junit.Test;
import org.junit.runner.Result;
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



	public JSONArray searchBylanguage(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call search_by_language_p(?)}");
			proc.registerOutParameter(1,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set =(ResultSet)proc.getObject(1);
			return procTool.getResult(set,"");

		}
		catch(Exception e){
				System.out.println("searchByLanguage");
				e.printStackTrace();
				return null;
		}
	}


	public JSONArray selectByGenre(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	search_by_genre_p(?)}");
			proc.registerOutParameter(1,OracleTypes.CURSOR);
			proc.execute();


			ResultSet set = (ResultSet) proc.getObject(1);
			return procTool.getResult(set,"");
		}
		catch (Exception e){
			System.out.println("selectByGenre");
			e.printStackTrace();
			return null;
		}
	}


	public JSONArray searchByDuration(int minDuration, int maxDuraion)
	{
		try{
			Connection con =  conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call search_by_duration_p(?,?,?)}");

			proc.setInt("minDuration",minDuration);
			proc.setInt("maxDuration",maxDuraion);
			proc.registerOutParameter("movies_out",OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet) proc.getObject("movies_out");
			return procTool.getResult(set,"title","title","duration","duration","director_name","director_name","release_date","release_date");
	}
	catch (Exception e){
			System.out.println("searchByDuration");
			e.printStackTrace();
			return null;
	}
	}

	public JSONArray searchAdvance(String i_pubdate, String i_title, String i_director, String i_actor, String i_genre){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	search_advanced_p(?,?,?,?,?,?)}");

			proc.setString("i_pubdate",i_pubdate);
			proc.setString("i_title",i_title);
			proc.setString("i_director",i_director);
			proc.setString("i_actor",i_actor);
			proc.setString("i_genre",i_genre);
			proc.registerOutParameter("movies",OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet)proc.getObject("movies");
			return procTool.getResult(set,"title","title","director_name","director_name","actor_name","actor_name","genre_name","genre_name","release_date","release_date");
		}
		catch (Exception e){
			System.out.println("searchAdvance");
			e.printStackTrace();
			return null;
		}
	}
}
