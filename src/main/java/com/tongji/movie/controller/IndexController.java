package com.tongji.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by LSL on 2018/1/1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController
{
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}
	@RequestMapping(value = "/time.action", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String graph_manag() {
		System.out.println("访问图管理界面的图信息action");
		String datastr = "{ \"aaData\":[{ \"id\" : 25543254345325,\"graphname\" : \"aaa\", \"storageSize\" : 12288, \"iscluster\" : false, \"dataSize\" : 0 }," +
				" { \"id\" : 43242354325432, \"graphname\" : \"ludian_jm\", \"storageSize\" : 37601280, \"iscluster\" : false, \"dataSize\" : 103696079 }, " +
				"{ \"id\" : 35432543254325432, \"graphname\" : \"lvdian\", \"storageSize\" : 40783872, \"iscluster\" : true, \"dataSize\" : 103696079 }," +
				"{ \"id\" : 4543254325325, \"graphname\" : \"ts_ld_gnlk\", \"storageSize\" : 29380608, \"iscluster\" : true, \"dataSize\" : 36019005 }]}";
		return datastr;
	}
}
