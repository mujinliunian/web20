package com.kaishengit.web;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        //首先验证 验证码是否正确
        HttpSession session = request.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if(sessionCaptcha != null && captcha != null && sessionCaptcha.equals(captcha)) {


            UserDao userDao = new UserDao();

            final User user = userDao.findByUsername(username);

            if (user != null && password.equals(user.getPassword())) {

//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //登录成功，给用户发送电子邮件
//                        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//                        EmailUtil.sendSimpleEmail("产品系统登录通知","你的账号"+user.getUsername()+"在"+now+"登录了系统",user.getEmail());
//                    }
//                });
//                threadhtyrhtrtegsrfgserfefesdfse3dr5` 1df.start();


                session.setAttribute("user", user);

                //判断用户是否选择了记住我功能
                String rememberMe = request.getParameter("rememberme");
                if ("yes".equals(rememberMe)) {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(60 * 60 * 24);//生存周期
                    cookie.setPath("/");
                    cookie.setHttpOnly(true); //该Cookie值只能让服务器端读取

                    Cookie pwdCookie = new Cookie("password", password);
                    pwdCookie.setMaxAge(60 * 60 * 24);//生存周期
                    pwdCookie.setPath("/");
                    pwdCookie.setHttpOnly(true); //该Cookie值只能让服务器端读取

                    //将上面的cookie发送给客户端
                    response.addCookie(cookie);
                    response.addCookie(pwdCookie);
                }

                response.sendRedirect("/home.do");
            } else {
                response.sendRedirect("/index.do?code=1001");
            }
        } else {
            response.sendRedirect("/index.do?code=1003");
        }
    }
}
