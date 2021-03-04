package com.simplefileserver.controller;

import com.google.gson.Gson;
import com.simplefileserver.entity.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @ResponseBody
    @GetMapping("/login")
    public String login(HttpServletRequest request,String username, String password){
        HttpSession session = request.getSession();
        ResultInfo info = new ResultInfo();
        if("admin".equals(username) && "nishengri".equals(password)){
            session.setAttribute("login",true);
            info.setStatus(1);
            info.setMsg("/console");
        }else {
            info.setStatus(2);
            info.setMsg("用户名密码错误");
        }
        Gson gson = new Gson();
        return gson.toJson(info);
    }

    @GetMapping("/admin")
    public String adminLoginPage(){
        return "login.html";
    }
}
