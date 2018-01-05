package com.tongji.movie.controller;
import com.tongji.movie.service.SearchMovieWithName;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@RestController
@RequestMapping(value = "/name",method =  RequestMethod.POST)
public class NameMovieController {
    @Autowired
    private SearchMovieWithName searchMovieWithName;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByName(String name){
        JSONArray jsonArray;
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
