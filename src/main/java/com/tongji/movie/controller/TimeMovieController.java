package com.tongji.movie.controller;

import com.tongji.movie.service.SearchMovieWithTime;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/time")
public class TimeMovieController {

    @Autowired
    private SearchMovieWithTime searchMovieWithTime;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByTime(@RequestParam(value = "year", required = true) String year,
                                  @RequestParam(value = "month",required = true) String[]monthArray,
                                  @RequestParam(value = "day", required = true) String[] dayArray,
                                  @RequestParam(value = "dateType",required = true) String dateType,
                                  @RequestParam(value = "date", required = true) String date,
                                  @RequestParam(value = "season", required = true) String[] seasonArray)
    {
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithTime.searchInOracle(dateType,date,year,seasonArray,monthArray,dayArray);
        } catch (SQLException e) {
            System.out.println("按时间查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
