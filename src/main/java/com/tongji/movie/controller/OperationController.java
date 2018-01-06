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


    @RequestMapping(value = "/show{.*}",method = RequestMethod.GET)
    public JSONArray showHistogram()
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();


        object.put("relation",20);
        object.put("mix",40);


        jsonArray.appendElement(object);
        return jsonArray;
    }

}