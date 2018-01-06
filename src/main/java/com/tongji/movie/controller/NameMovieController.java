package com.tongji.movie.controller;
import com.tongji.movie.model.AjaxBean;
import com.tongji.movie.service.SearchMovieWithName;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/name",method =  RequestMethod.POST)
public class NameMovieController {
    @Autowired
    private SearchMovieWithName searchMovieWithName;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByName(String name,  @RequestParam(value="years" ,required = true ) String[] years, @RequestParam(value="months" ,required = true ) String[] months){
        JSONArray jsonArray;
        for(String year : years){
            System.out.println(year);
        }
        for(String month : months){
            System.out.println(month);
        }
        try {
            jsonArray = searchMovieWithName.search(name);
            } catch (SQLException e) {
            System.out.println("按名字查询电影失败");
            jsonArray = null;
            e.printStackTrace();
            }
        return jsonArray;
    }

}


