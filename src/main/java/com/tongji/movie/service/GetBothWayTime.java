package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by LSL on 2018/1/6
 */
@Component
public class GetBothWayTime
{
	@Autowired
	SearchMovieWithActor searchMovieWithActor;
	@Autowired
	SearchMovieWithDirector searchMovieWithDirector;
	@Autowired
	SearchMovieWithGenere searchMovieWithGenere;
	@Autowired
	SearchMovieWithName searchMovieWithName;
	@Autowired
	SearchMovieWithTime searchMovieWithTime;
	@Autowired
	SearchMovieWithLanguage searchMovieWithLanguage;
	@Autowired
	SearchMovieWithRunTime searchMovieWithRunTime;
	@Autowired
	SearchMovieWithCombination searchMovieWithCombination;
	@Autowired
	SearchReview searchReview;


	public JSONArray getBothWayTime(String operation,String searchStr) throws SQLException
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		if(operation.indexOf("actor")>0)
		{
			searchMovieWithActor.searchInOracle(searchStr,true);
			oracleTime =System.currentTimeMillis();
			searchMovieWithActor.searchInOracle(operation,false);

		}
		else if(operation.indexOf("language")>0)
		{
			searchMovieWithLanguage.searchInOracle(searchStr,true	);
			oracleTime =System.currentTimeMillis();
			searchMovieWithLanguage.searchInOracle(searchStr,false	);
		}
		else if(operation.indexOf("director")>0)
		{
			searchMovieWithDirector.searchInOracle(searchStr,true);
			oracleTime =System.currentTimeMillis();
			searchMovieWithDirector.searchInOracle(searchStr,false);


		}
		else if(operation.indexOf("category")>0)
		{
			searchMovieWithGenere.searchInOracle(searchStr,true);
			oracleTime =System.currentTimeMillis();
			searchMovieWithGenere.searchInOracle(searchStr,false);


		}
		else if(operation.indexOf("movieName")>0)
		{
			searchMovieWithName.searchInOracle(searchStr,true);
			oracleTime =System.currentTimeMillis();
			searchMovieWithName.searchInOracle(searchStr,false);

		}
		else if(operation.indexOf("coactor")>0)
		{
			searchMovieWithDirector.searchCoActorInOracle(searchStr,true);
			oracleTime =System.currentTimeMillis();
			searchMovieWithDirector.searchCoActorInOracle(searchStr,false);

		}


		long endTime = System.currentTimeMillis();

		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;

	}
	public JSONArray getBothWayTimeOfDate(String dateType,String date,String year,String[] monthArray) throws SQLException
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		searchMovieWithTime.searchInOracle(dateType,date,year,monthArray,true);
		oracleTime =System.currentTimeMillis();
		searchMovieWithTime.searchInOracle(dateType,date,year,monthArray,false);
		long endTime = System.currentTimeMillis();

		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;
	}
	public JSONArray getBothWayTimeOfRuntime(String time1, String time2) throws SQLException
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		searchMovieWithRunTime.searchInOracle(time1,time2,true);
		oracleTime =System.currentTimeMillis();
		searchMovieWithRunTime.searchInOracle(time1,time2,false);
		long endTime = System.currentTimeMillis();

		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;
	}
	public JSONArray getBothWayTimeOfCombination(String date,String name,String actor,String director,String genre) throws SQLException
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		searchMovieWithCombination.searchInOracle(date,name,actor,director,genre,true);
		oracleTime =System.currentTimeMillis();
		searchMovieWithCombination.searchInOracle(date,name,actor,director,genre,false);
		long endTime = System.currentTimeMillis();

		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;
	}
	public JSONArray getBothWayTimeOfComment(String movieName,int level)
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		searchReview.searchReviewInOracle(movieName,level,true);
		oracleTime =System.currentTimeMillis();
		searchReview.searchReviewInOracle(movieName,level,true);
		long endTime = System.currentTimeMillis();
		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;
	}
}
