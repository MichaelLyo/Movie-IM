package com.tongji.movie.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.service.DirectorService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/",method = {RequestMethod.GET, RequestMethod.POST})
public class OperationController{
	@Autowired
	DirectorService directorService;

	@RequestMapping(value = "/showtime",method = RequestMethod.GET)
	public JSONArray movieTimetest()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","sjw");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,120");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsl");
		object1.put("releaseTime","System Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","5421");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}

	@RequestMapping(value = "/showmovieName",method = RequestMethod.GET)
	public JSONArray movieNametest()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","sjw");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,120");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsldd");
		object1.put("releaseTime","System Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","54321");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}
	@RequestMapping(value = "/showdirector",method = RequestMethod.GET)
	public JSONArray directortest(String directorName)
	{
		System.out.println(directorName);
		JSONArray jsonArray = new JSONArray();
		List<AmazonFact> movies =directorService.findMoviesByDirectorName(directorName);
		for(AmazonFact item:movies)
		{
			JSONObject object = new JSONObject();

			object.put("movieID",item.getMovieId());
			object.put("movieName",item.getTitle());
			object.put("director",directorName);
			object.put("runTime",item.getRunTime());
			object.put("releaseTime",item.getReleaseDate());
			object.put("edition",item.getEdition());

			jsonArray.appendElement(object);

		}

		return jsonArray;
	}
	@RequestMapping(value = "/showactor",method = RequestMethod.GET)
	public JSONArray actortest()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","s2jw3");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,120");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsldd");
		object1.put("releaseTime","S3ysdtem Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","5421");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}
	@RequestMapping(value = "/showcategory",method = RequestMethod.GET)
	public JSONArray categorytest()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","s2jw3");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,1230");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsldd");
		object1.put("releaseTime","S3ystfem Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","5421");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}
	@RequestMapping(value = "/showcombination",method = RequestMethod.GET)
	public JSONArray combination()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","s2jw33");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,1230");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsldd");
		object1.put("releaseTime","S3yfstem Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","5421");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}
	@RequestMapping(value = "/showcomment",method = RequestMethod.GET)
	public JSONArray comment()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		object.put("movieName","3");
		object.put("releaseTime","System Architect");
		object.put("genre","$3,1230");
		object.put("director","2011/04/25");
		object.put("actor","Edinburgh");
		object.put("edition","5421");

		object1.put("movieName","lsldd");
		object1.put("releaseTime","S3ystem Architect");
		object1.put("genre","$3,120");
		object1.put("director","2011/04/25");
		object1.put("actor","Edinburgh");
		object1.put("edition","5421");
		jsonArray.appendElement(object);
		jsonArray.appendElement(object1);
		return jsonArray;
	}
}