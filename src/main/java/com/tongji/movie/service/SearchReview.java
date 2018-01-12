package com.tongji.movie.service;

import net.minidev.json.JSONArray;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by LSL on 2018/1/12
 */
@Component
public class SearchReview
{
	@Autowired
	ConToOracle conToOracle;

	public JSONArray searchReview(String movieName,int level){
		JSONArray result = new JSONArray();
		try{
			Connection con = conToOracle.getConnection();
			CallableStatement proc = con.prepareCall("{call search_review_p(?,?,?)}");

			proc.setInt(1,level);
			proc.setString(2,movieName);
			proc.registerOutParameter(3, OracleTypes.CURSOR);

			proc.execute();
			ResultSet set = (ResultSet)proc.getObject(3);
			result = procTool.getResult(set,"title","title","userName","reviewer_name","summary","summary","score","score","helpfulness","agree_rate");
		}
		catch (Exception e){
			System.out.println("searchReview error!");
			e.printStackTrace();
		}
		return result;
	}
}