package com.tongji.movie.controller;

import com.tongji.movie.service.SearchMovieWithCombination;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/combination",method = RequestMethod.POST)
public class CombinationMovieController {

    @Autowired
    private SearchMovieWithCombination searchMovieWithCombination;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByTime(@RequestParam(value = "date", required = true) String date,
                                  @RequestParam(value = "name",required = true) String name,
                                  @RequestParam(value = "actor", required = true) String actor,
                                  @RequestParam(value = "director",required = true) String director,
                                  @RequestParam(value = "genre", required = true) String genre)
    {
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithCombination.search(date,name,actor,director,genre);
        } catch (SQLException e) {
            System.out.println("高级查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
