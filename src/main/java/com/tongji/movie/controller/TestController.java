package com.tongji.movie.controller;


import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public  String showTest(){
        return "test";
    }
}

