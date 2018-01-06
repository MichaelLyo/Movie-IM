package com.tongji.movie.controller;
import com.tongji.movie.service.SearchMovieWithDirector;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/director",method = {RequestMethod.GET,RequestMethod.POST})
public class DirectorMovieController {
    @Autowired
    SearchMovieWithDirector searchMovieWithDirector;

    @RequestMapping(value = "/search")
    public JSONArray searchMovieWithDirector(String directorName){
        System.out.println("hellosdfsfs");
        JSONArray jsonArray;
        try {
            jsonArray =searchMovieWithDirector.searchInOracle(directorName);
        } catch (SQLException e) {
            System.out.println("按类型查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
