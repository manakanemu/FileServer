package com.simplefileserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/index")
    public String index1(){
        return "/";
    }
    @GetMapping("/")
    public String index3(){
        return "index.html";
    }
}
