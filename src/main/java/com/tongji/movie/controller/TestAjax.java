package com.tongji.movie.controller;

import com.sun.org.apache.regexp.internal.RE;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.hadoop.metrics2.util.SampleStat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ajax",method = {RequestMethod.GET, RequestMethod.POST})
public class TestAjax{
    @RequestMapping(value = "/showtime",method = RequestMethod.GET)
    public JSONArray movieTimetest()
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();

        object.put("movieName","sjw");
        object.put("releaseTime","System Architect");
        object.put("genre","$3,120");
        object.put("director","2011/04/25");
        object.put("actor","Edinburgh");
        object.put("edition","5421");
        object.put("relation",15);
        object.put("mix",33);

        jsonArray.appendElement(object);
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
        object.put("relation",15);
        object.put("mix",33);



        jsonArray.appendElement(object);
        return jsonArray;
    }
    @RequestMapping(value = "/showdirector",method = RequestMethod.GET)
    public JSONArray directortest()
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        JSONObject object1 = new JSONObject();
        object.put("movieName","sjw3");
        object.put("releaseTime","System Architect");
        object.put("genre","$3,120");
        object.put("director","2011/04/25");
        object.put("actor","Edinburgh");
        object.put("edition","5421");

        object.put("relation",15);
        object.put("mix",33);
        jsonArray.appendElement(object);

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

        object.put("relation",15);
        object.put("mix",33);
        jsonArray.appendElement(object);
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

        object.put("relation",15);
        object.put("mix",33);
        jsonArray.appendElement(object);
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

        object.put("relation",15);
        object.put("mix",33);
        jsonArray.appendElement(object);
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

        object.put("relation",15);
        object.put("mix",33);
        jsonArray.appendElement(object);
        return jsonArray;
    }
}