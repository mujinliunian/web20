package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 让session过期
        request.getSession().invalidate();
        //2. 删除cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
                    cookie.setMaxAge(0);//生存周期
                    cookie.setPath("/");
                    cookie.setHttpOnly(true); //该Cookie值只能让服务器端读取
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect("/index.do");
    }
}
