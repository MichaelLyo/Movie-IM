package com.tongji.movie.model;
import org.springframework.stereotype.Component;

@Component
public  class AjaxBean{
    public String myname;
    public AjaxBean() {

    }
    public AjaxBean(String myname) {
        this.myname = myname;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }
}