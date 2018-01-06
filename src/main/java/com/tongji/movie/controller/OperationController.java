package com.tongji.movie.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.service.DirectorService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.hadoop.metrics2.util.SampleStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ajax",method = {RequestMethod.GET, RequestMethod.POST})
public class OperationController
{
/*    @RequestMapping(value = "/testPost", method = {RequestMethod.POST})
    public void testPost(HttpServletRequest req) throws IOException {
        String[] array = req.getParameterValues("array[]");
        System.out.println(array[0]);//具体日期
        System.out.println(array[1]);//出版日期or上映日期
        System.out.println(array[2]);//年份
        System.out.println(array[3]);//季度数组
        System.out.println(array[4]);//月份数组
        System.out.println(array[5]);//天数组
        return ;
    }*/
    @Autowired
    DirectorService directorService;
    @RequestMapping(value = "/showtime",method = RequestMethod.GET)
    public JSONArray movieTimetest()
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();

        object.put("movieName","sjw");
        object.put("releaseTime","Systecm Architect");
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
        object.put("movieName","sjdw");
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