package com.simplefileserver.controller;

import com.google.gson.Gson;
import com.simplefileserver.entity.Notice;
import com.simplefileserver.service.NoticeService;
import com.simplefileserver.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class ConsoleController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/console")
    public String openConsole(HttpServletRequest request) {
        boolean login = AuthUtils.isLogin(request);
        if (login == true) {
            return "/manakanemuConsole.html";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/notice")
    public String openNotice(HttpServletRequest request) {
        boolean login = AuthUtils.isLogin(request);
        if (login) {
            return "/manakanemuNotice.html";
        } else {
            return "redirect:/admin";
        }
    }

    @ResponseBody
    @GetMapping("/addNotice")
    public String addNotice(HttpServletRequest request, Notice notice) {
        if (AuthUtils.isLogin(request)) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            notice.setNumber(uuid);
            noticeService.addNotice(notice);
            List<Notice> notice1 = noticeService.getNotice();
            Gson gson = new Gson();
            return gson.toJson(notice1);
        } else {
            return "";
        }

    }

    @ResponseBody
    @GetMapping("/deleteNotice")
    public String deleteNotice(HttpServletRequest request, Notice notice) {
        if (AuthUtils.isLogin(request)) {
            noticeService.deleteNotice(notice);
            List<Notice> notice1 = noticeService.getNotice();
            Gson gson = new Gson();
            return gson.toJson(notice1);
        } else {
            return "";
        }
    }

    @GetMapping("/chart")
    public String openChartPage(HttpServletRequest request) {
        return "/manakanemuChart.html";
    }
}


