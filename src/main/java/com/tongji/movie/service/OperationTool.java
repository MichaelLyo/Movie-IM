package com.tongji.movie.service;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


import java.sql.ResultSet;
import java.util.HashSet;

public class OperationTool
{
    public static JSONArray getResult(ResultSet set, String... p){
        int index=0;
        try{
            JSONArray jsonArray = new JSONArray();
            while(set.next()){
                if( index++>1000)
                    break;
                JSONObject object = new JSONObject();
                for(int i=0 ;i<p.length;i=i+2)
                {
                    object.put(p[i], set.getString(p[i+1]));
                }
                jsonArray.add(object);
            }
            set.close();
            return jsonArray;
        }
        catch (Exception e){
            System.out.println("OperationTool");
            e.printStackTrace();
            return null;
        }
    }
    public static int getLevelOfReview(String type)
    {
        int level=-2;
        switch (type)
        {
            case "positive":
                level =1;
                break;
            case "moderate":
                level=0;
                break;
            case "negative":
                level=1;
                break;
            case "all":
                level = 2;
                break;
            default:
                break;
        }
        return level;
    }
}
