package com.tongji.movie.controller;

import com.tongji.movie.service.SearchReview;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LSL on 2018/1/12
 */
@RestController
@RequestMapping(value = "/comment")
public class ReviewController
{
	@Autowired
	SearchReview searchReview;

	@RequestMapping(value = "/{type}",method = RequestMethod.POST)
	public JSONArray searchByMovieName(String movieName,@PathVariable String type){
		int level=-2;
		switch (type)
		{
			case "positive":
				level =1;
				break;
			case "moderate":
				level=0;
				break;
			case "negative":
				level=1;
				break;
			default:
				break;
		}
		return searchReview.searchReview(movieName, level);
	}
}
