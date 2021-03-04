package com.simplefileserver.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtils {
    public static boolean isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login != null) {
            if (login) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
