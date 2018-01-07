package com.tongji.movie.controller;

import com.tongji.movie.service.SearchMovieWithRunTime;
import com.tongji.movie.service.SearchMovieWithTime;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/runtime")
public class RunTimeMovieController {
    @Autowired
    private SearchMovieWithRunTime searchMovieWithRunTime;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByTime(@RequestParam(value = "time1", required = true) String time1,
                                  @RequestParam(value = "time2",required = true) String time2)
    {
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithRunTime.searchInOracle(time1,time2);
        } catch (SQLException e) {
            System.out.println("按时长查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
