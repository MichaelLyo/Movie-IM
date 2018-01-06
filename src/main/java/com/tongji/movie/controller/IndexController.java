package com.tongji.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
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



}
