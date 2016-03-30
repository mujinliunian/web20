package com.kaishengit.web;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取客户端的cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            String name = null;
            String pwd = null;

            for(Cookie cookie : cookies) {
                if("username".equals(cookie.getName())) {
                    name = cookie.getValue();
                } else if("password".equals(cookie.getName())) {
                    pwd = cookie.getValue();
                }
            }

            if(name != null && pwd != null) {
                User user = new UserDao().findByUsername(name);
                if(user != null && pwd.equals(user.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                    response.sendRedirect("/home.do");
                    return;
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        rd.forward(request,response);

    }
}
