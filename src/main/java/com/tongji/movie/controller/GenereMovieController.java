package com.tongji.movie.controller;
import com.tongji.movie.service.SearchMovieWithGenere;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/genere",method = RequestMethod.POST)
public class GenereMovieController {
    @Autowired
    SearchMovieWithGenere searchMovieWithGenere;
    @RequestMapping(value = "/search")
    public JSONArray searchMovieWithGenere(String genere){
        JSONArray jsonArray;
        try {
            jsonArray = searchMovieWithGenere.search(genere);
        } catch (SQLException e) {
            System.out.println("按类型查询电影失败");
            jsonArray = null;
            e.printStackTrace();
        }
        return jsonArray;
    }
}
