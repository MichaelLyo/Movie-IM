package com.tongji.movie.controller;

import com.tongji.movie.service.SearchMovieWithActor;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/actor")
public class ActorMovieController {
    @Autowired
    private SearchMovieWithActor searchMovieWithActor;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByName(String actorName){
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithActor.search(actorName);
        } catch (SQLException e) {
            System.out.println("按名字查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
