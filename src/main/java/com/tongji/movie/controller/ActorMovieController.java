package com.tongji.movie.controller;

import com.google.gson.JsonArray;
import com.tongji.movie.service.SearchMovieWithActor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.jcodings.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping(value = "/actor")
public class ActorMovieController {
    @Autowired
    private SearchMovieWithActor searchMovieWithActor;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public JSONArray searchByName(String actorName){
        JSONArray jsonArray = new JSONArray();
        try {
            String[] nameList = actorName.split("\\,");
            HashSet<Object> set = new HashSet<>();
            for(int i =0; i<nameList.length;i++){
                JSONArray temp = searchMovieWithActor.searchInOracle(nameList[i]);
                HashSet tempSet = new HashSet();
                for(Object o:temp){
                    tempSet.add(o);
                }
                if(i==0){
                    set.addAll(tempSet);
                }
                else
                {
                    set.retainAll(temp);
                }

            }
            jsonArray.addAll(set);
            return jsonArray;

        } catch (SQLException e) {
            System.out.println("按名字查询电影失败");
            jsonArray = null;
            e.printStackTrace();
            return jsonArray;
        }
    }

    @RequestMapping(value = "/starring",method = RequestMethod.POST)
    public JSONArray searchByStarring(String actorName)
    {
        JSONArray jsonArray = new JSONArray();

        try {
            JSONArray tempArray = new JSONArray();
            tempArray = searchMovieWithActor.searchStarringInOracle(actorName);
            HashSet<Object> set = new HashSet<>();
            for(Object o : tempArray){
                if(!set.contains(o))
                    set.add(o);
            }
            jsonArray.addAll(set);
            return jsonArray;

        } catch (SQLException e) {
            System.out.println("按名字查询电影失败");
            jsonArray = null;
            e.printStackTrace();
            return jsonArray;
        }
    }
}