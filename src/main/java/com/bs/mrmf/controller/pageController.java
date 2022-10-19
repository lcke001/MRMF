package com.bs.mrmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/pay")
    public String pay(){
        return "pay";
    }
    @GetMapping("/order")
    public String order(){
        return "order";
    }
    @GetMapping("/project")
    public String project(){
        return "project";
    }
    @GetMapping("/vip")
    public String vip(){
        return "vip";
    }
}
