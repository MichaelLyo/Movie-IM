package com.tongji.movie.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tongji.movie.model.AmazonFact;

import com.tongji.movie.service.GetBothWayTime;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.hadoop.metrics2.util.SampleStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONArray showHistogram(@PathVariable String operation) throws SQLException
    {
        //System.out.println(operation);
        return getBothWayTime.getBothWayTime(operation);
    }

    @RequestMapping(value = "/multiple/showtime")
    public JSONArray showHistogramOfDate(@RequestParam(value = "year", required = true) String year,
                                         @RequestParam(value = "month",required = true) String[]monthArray,
                                         @RequestParam(value = "day", required = true) String[] dayArray,
                                         @RequestParam(value = "dateType",required = true) String dateType,
                                         @RequestParam(value = "date", required = true) String date,
                                         @RequestParam(value = "season", required = true) String[] seasonArray) throws SQLException
    {
        return getBothWayTime.getBothWayTimeOfDate(dateType,date,year,seasonArray,monthArray,dayArray);
    }


}