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
@RequestMapping(value = "/ajax",method = {RequestMethod.GET, RequestMethod.POST})
public class OperationController
{

    @Autowired
    GetBothWayTime getBothWayTime;

    @RequestMapping(value = "/{operation}",method = RequestMethod.GET)
    public JSONArray showHistogram(@PathVariable String operation) throws SQLException
    {
        System.out.println(operation);
        return getBothWayTime.getBothWayTime(operation);
    }

}