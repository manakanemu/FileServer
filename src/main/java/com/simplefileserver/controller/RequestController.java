package com.simplefileserver.controller;

import com.google.gson.Gson;
import com.simplefileserver.entity.Request;
import com.simplefileserver.entity.ResultInfo;
import com.simplefileserver.service.RequestService;
import com.simplefileserver.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RequestController {
    @Autowired
    RequestService requestService;

    @ResponseBody
    @GetMapping("/requestList")
    public String getFullRequestList(HttpServletRequest request) {
        if (AuthUtils.isLogin(request)) {
            List<Request> requestList = requestService.getAllRequest();
            Gson gson = new Gson();
            return gson.toJson(requestList);
        } else {
            return "";
        }
    }

    @ResponseBody
    @GetMapping("/requestStatistics")
    public String getRequestStatistics(HttpServletRequest request) {
        List<ResultInfo> statistics = requestService.getRequestStatistics();
        Gson gson = new Gson();
        return gson.toJson(statistics);

    }
}
