package com.tongji.movie.service;
import net.minidev.json.JSONArray;


import java.util.HashSet;

public class procTool {
    public static JSONArray getResult(String... p){
        try{
            JSONArray jsonArray = new JSONArray();
            return jsonArray;
        }
        catch (Exception e){
            System.out.println("procTool");
            e.printStackTrace();
            return null;
        }
    }
}
