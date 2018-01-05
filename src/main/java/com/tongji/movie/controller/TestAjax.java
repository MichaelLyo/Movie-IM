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
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public JSONArray test()
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        JSONObject object1 = new JSONObject();
        object.put("movieName","sjw");
        object.put("releaseTime","System Architect");
        object.put("style","$3,120");
        object.put("director","2011/04/25");
        object.put("actor","Edinburgh");
        object.put("edition","5421");

        object1.put("movieName","lsl");
        object1.put("releaseTime","System Architect");
        object1.put("style","$3,120");
        object1.put("director","2011/04/25");
        object1.put("actor","Edinburgh");
        object1.put("edition","5421");
        jsonArray.appendElement(object);
        jsonArray.appendElement(object1);
        return jsonArray;
    }
    private class dateType{
        private String name;

        public dateType(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @RequestMapping(value = "/page",method = {RequestMethod.GET,RequestMethod.POST})
    public JSONArray page(String name){
        System.out.println("enheng");
        System.out.println(name);
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("name","sjw");
        return jsonArray;
    }
}