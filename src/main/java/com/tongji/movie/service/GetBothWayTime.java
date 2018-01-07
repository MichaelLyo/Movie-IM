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
	public JSONArray getBothWayTime(String operation) throws SQLException
	{
		long startTime = System.currentTimeMillis();
		JSONArray result = new JSONArray();
		long oracleTime =0;
		if(operation.indexOf("actor")>0)
		{
			searchMovieWithActor.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithActor.search(operation);

		}
		else if(operation.indexOf("language")>0)
		{
			searchMovieWithLanguage.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithLanguage.search(operation);

		}
		else if(operation.indexOf("director")>0)
		{
			searchMovieWithDirector.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithDirector.search(operation);

		}
		else if(operation.indexOf("category")>0)
		{
			searchMovieWithGenere.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithGenere.search(operation);

		}
		else if(operation.indexOf("movieName")>0)
		{
			searchMovieWithName.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithName.search(operation);

		}
		else if(operation.indexOf("combination")>0)
		{
			searchMovieWithName.searchInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithName.search(operation);

		}
		else if(operation.indexOf("coactor")>0)
		{
			searchMovieWithDirector.searchCoActorInOracle(operation);
			oracleTime =System.currentTimeMillis();
			searchMovieWithDirector.searchCoActor(operation);

		}
		//else if(operation.indexOf("time")>0)
		//{
		//	searchMovieWithTime.searchInOracle(operation);
		//	oracleTime =System.currentTimeMillis();
		//	searchMovieWithTime.search(operation);
		//
		//}

		long endTime = System.currentTimeMillis();

		JSONObject time = new JSONObject();
		time.put("relation", oracleTime - startTime);
		time.put("mix", endTime - oracleTime);
		result.add(time);

		return result;

	}
}
