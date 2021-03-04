package com.simplefileserver.controller;

import com.google.gson.Gson;
import com.simplefileserver.entity.Notice;
import com.simplefileserver.entity.Request;
import com.simplefileserver.service.NoticeService;
import com.simplefileserver.service.RequestService;
import com.simplefileserver.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DataController {
    @Autowired
    RequestService requestService;
    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @GetMapping("/getRequest")
    public String getRequest() {
        List<Request> requestList = requestService.getAllRequest();
        Gson gson = new Gson();
        return gson.toJson(requestList);
    }

    @ResponseBody
    @GetMapping("/getNotice")
    public String getNotice(HttpServletRequest request) {
        if (AuthUtils.isLogin(request)) {
            List<Notice> noticeList = noticeService.getNotice();
            Gson gson = new Gson();
            return gson.toJson(noticeList);
        } else {
            return "";
        }
    }

    @ResponseBody
    @GetMapping("/noticelist")
    public String userNoticeList() {
        List<Notice> noticeList = noticeService.getNotice();
        for(Notice notice:noticeList){
            notice.setLevel(0);
            notice.setNumber("");
        }
        Gson gson = new Gson();
        return gson.toJson(noticeList);
    }

}
