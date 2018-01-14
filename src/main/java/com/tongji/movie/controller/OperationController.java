package com.tongji.movie.controller;

import com.sun.org.apache.regexp.internal.RE;


import com.tongji.movie.service.GetBothWayTime;

import com.tongji.movie.service.OperationTool;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.hadoop.metrics2.util.SampleStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OperationController
{

    @Autowired
    GetBothWayTime getBothWayTime;

    @RequestMapping(value = "/ajax/{operation}",method = {RequestMethod.GET, RequestMethod.POST})
    public JSONArray showHistogram(@PathVariable String operation,String searchStr) throws SQLException
    {
        System.out.println(searchStr);
        return getBothWayTime.getBothWayTime(operation,searchStr);
    }

    @RequestMapping(value = "/multiple/showtime")
    public JSONArray showHistogramOfDate(@RequestParam(value = "year", required = true) String year,
                                         @RequestParam(value = "month",required = true) String[]monthArray,
                                         @RequestParam(value = "dateType",required = true) String dateType,
                                         @RequestParam(value = "date", required = true) String date) throws SQLException
    {
        return getBothWayTime.getBothWayTimeOfDate(dateType,date,year,monthArray);
    }

    @RequestMapping(value = "/multiple/showruntime")
    public JSONArray showRuntimeHistogram(String time1, String time2) throws SQLException
    {
        return getBothWayTime.getBothWayTimeOfRuntime(time1, time2);
    }
    @RequestMapping(value = "/multiple/showcombination")
    public JSONArray showCombinationHistogram(@RequestParam(value = "date", required = true) String date,
                                              @RequestParam(value = "name",required = true) String name,
                                              @RequestParam(value = "actor", required = true) String actor,
                                              @RequestParam(value = "director",required = true) String director,
                                              @RequestParam(value = "genre", required = true) String genre) throws SQLException
    {
        System.out.println("compare:"+date+"-"+name+"-"+actor+"-"+director+"-"+genre);
        return getBothWayTime.getBothWayTimeOfCombination(date,name,actor,director,genre);
    }
    @RequestMapping(value = "/multiple/test")
    public JSONArray testForCompareTime()
    {
        JSONArray result = new JSONArray();
        JSONObject time = new JSONObject();
        System.out.println("test for histogram");
        time.put("relation", 30);
        time.put("mix",40);
        result.add(time);
        return result;
    }
    @RequestMapping(value = "/multiple/showcomment")
    public JSONArray showCommentTime(String type,String movieName)
    {
        JSONArray result = getBothWayTime.getBothWayTimeOfComment(movieName, OperationTool.getLevelOfReview(type));
        return result;
    }
}