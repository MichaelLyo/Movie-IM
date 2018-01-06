package com.tongji.movie.controller;


import com.google.gson.JsonArray;
import com.google.gson.annotations.JsonAdapter;
import com.tongji.movie.service.SearchMovieWithTime;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/time",method = {RequestMethod.GET,RequestMethod.POST})
public class TimeMovieController {
    @Autowired
    SearchMovieWithTime searchMovieWithTime;

    @RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONArray search(String isPublicationDate, String year, String mon ,String date){
        JSONArray jsonArray = new JSONArray();
        try{
        if(isPublicationDate.equals("true") & !date.isEmpty()){
                jsonArray = searchMovieWithTime.searchMVByDate(true,date);
            }
        else if(isPublicationDate.equals("true")&date.isEmpty()){
            jsonArray = searchMovieWithTime.searchMvByYearAndMon(true,Integer.valueOf(year),Integer.valueOf(mon));
        }
        else if(isPublicationDate.equals("false") & !date.isEmpty()){
                jsonArray = searchMovieWithTime.searchMVByDate(false,date);
            }
        else if(isPublicationDate.equals("false") & date.isEmpty()){
            jsonArray = searchMovieWithTime.searchMvByYearAndMon(false,Integer.valueOf(year),Integer.valueOf(mon));
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
