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
	public void searchBylanguage(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call search_by_language_p(?,?)}");
			proc.setString(1,"Spanish");
			proc.registerOutParameter(2,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set =(ResultSet)proc.getObject(2);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("language_name"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("director_name"));
			}
//			return procTool.getResult(set,"");

		}
		catch(Exception e){
				System.out.println("searchByLanguage");
				e.printStackTrace();
//				return null;
		}
	}

	public void selectByGenre(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	search_by_genre_p(?,?)}");
			proc.setString(1,"Comedy");
			proc.registerOutParameter(2,OracleTypes.CURSOR);
			proc.execute();


			ResultSet set = (ResultSet) proc.getObject(2);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("edition"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("duration"));
			}
//			return procTool.getResult(set,"");
		}
		catch (Exception e){
			System.out.println("selectByGenre");
			e.printStackTrace();
//			return null;
		}
	}

	public void searchByDuration()
	{
		try{
			Connection con =  conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call search_by_duration_p(?,?,?)}");

			proc.setInt(1,110);
			proc.setInt(2,150);
			proc.registerOutParameter(3,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet) proc.getObject(3);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("duration"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("release_date"));
			}
//			return procTool.getResult(set,"title","title","duration","duration","director_name","director_name","release_date","release_date");
	}
	catch (Exception e){
			System.out.println("searchByDuration");
			e.printStackTrace();
//			return null;
	}
	}

	public void searchAdvance(){
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call	search_advanced_p(?,?,?,?,?,?)}");

			proc.setString(1,"");
			proc.setString(2,"");
			proc.setString(3,"");
			proc.setString(4,"");
			proc.setString(5,"");
			proc.registerOutParameter(6,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet)proc.getObject(6);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("actor_name"));
				System.out.println(set.getString("genre_name"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("duration"));

			}
//			return procTool.getResult(set,"title","title","director_name","director_name","actor_name","actor_name","genre_name","genre_name","release_date","release_date");
		}
		catch (Exception e){
			System.out.println("searchAdvance");
			e.printStackTrace();
//			return null;
		}
	}

	public void selectActor()
	{
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call select_actor(?,?)}");
			proc.setString(1,"Joseph Campbell");
			proc.registerOutParameter(2,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet) proc.getObject(2);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("actor_name"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("duration"));
				System.out.println(set.getString("genre_name"));
			}
//			return procTool.getResult(set,"");
		}
		catch (Exception e){
			System.out.println("selectActor");
			e.printStackTrace();
//			return null;
		}
	}

	public void selectCoActor()
	{
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call select_coactor(?,?)}");
			proc.setString(1,"Richard LaGravenese");
			proc.registerOutParameter(2,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet) proc.getObject(2);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("actor_name"));
				System.out.println(set.getString("genre_name"));
			}
//			return procTool.getResult(set,"");
		}
		catch (Exception e){
			System.out.println("selectCoActor");
			e.printStackTrace();
//			return null;
		}
	}

	public void selectDirector()
	{
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call select_director(?,?)}");
			proc.setString(1,"Mitch Rochon");
			proc.registerOutParameter(2,OracleTypes.CURSOR);
			proc.execute();

			ResultSet set = (ResultSet) proc.getObject(2);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("duration"));
				System.out.println(set.getString("format_name"));
			}
//			return procTool.getResult(set,"");
		}
		catch (Exception e){
			System.out.println("selectDirector");
			e.printStackTrace();
//			return null;
		}
	}

	public void selectTime()
	{
		try{
			Connection con = conObj.getConnection();
			CallableStatement proc = con.prepareCall("{call select_time(?,?,?,?,?)}");
			proc.setString(1,"2000-07-19");
			proc.setString(2,"2000");
			proc.setString(3,"7");
			proc.setString(4,"1");

			proc.registerOutParameter(5,OracleTypes.CURSOR);
			proc.execute();
			ResultSet set = (ResultSet) proc.getObject(5);
			while(set.next()){
				System.out.println(set.getString("title"));
				System.out.println(set.getString("release_date"));
				System.out.println(set.getString("publication_date"));
				System.out.println(set.getString("duration"));
				System.out.println(set.getString("director_name"));
				System.out.println(set.getString("format_name"));
			}
//			return procTool.getResult(set,"title","title","release_date","release_date","duration","duration","director","director","studio","studio");
		}
		catch (Exception e){
			System.out.println("selectTime");
			e.printStackTrace();
//			return null;
		}
	}

	public void selectMovieByName(){
			try{
				Connection con = conObj.getConnection();
				CallableStatement proc = con.prepareCall("{call select_moviename(?,?)}");

				proc.setString(1,"Vaada");
				proc.registerOutParameter(2,OracleTypes.CURSOR);

				proc.execute();
				ResultSet set = (ResultSet)proc.getObject(2);
				while(set.next())
				{
					System.out.println(set.getString("title"));
					System.out.println(set.getString("release_date"));
					System.out.println(set.getString("duration"));
					System.out.println(set.getString("director_name"));
					System.out.println(set.getString("actor_name"));
					System.out.println(set.getString("studio_name"));
				}
//				return procTool.getResult(set, "title","title","release_date","release_date","duration","duration","director","director","studio","studio");
			}
			catch (Exception e){
                     System.out.println("selectMovieByName");
                     e.printStackTrace();
//                     return null;
			}
	}
}
