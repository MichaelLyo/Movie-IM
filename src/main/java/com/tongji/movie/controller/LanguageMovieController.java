package com.tongji.movie.controller;
import com.tongji.movie.service.SearchMovieWithLanguage;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/language",method =  RequestMethod.POST)
public class LanguageMovieController {
    @Autowired
    private SearchMovieWithLanguage searchMovieWithLanguage;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByName(String language){
        System.out.println(language);
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithLanguage.searchInOracle(language,true);
        } catch (SQLException e) {
            System.out.println("按语种查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }

}