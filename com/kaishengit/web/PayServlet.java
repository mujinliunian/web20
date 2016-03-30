package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取表单中的Token
        String reqToken = request.getParameter("token");
        //2.获取session中的token
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute("token");
        //3.比较
        if(reqToken != null && sessionToken != null && reqToken.equals(sessionToken)) {
            //4.如果相等，则是正常提交 ，并从session中删除token
            session.removeAttribute("token");

            String money = request.getParameter("money");
            System.out.println("已支付：" + money + "元");

            request.getRequestDispatcher("/WEB-INF/views/paysuc.jsp").forward(request,response);
        } else {
            //5.如果不相等，则是重复提交
            request.getRequestDispatcher("/WEB-INF/views/payerror.jsp").forward(request,response);
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.产生Token
        String token = UUID.randomUUID().toString();
        //2.将Token传递到表单页面
        request.setAttribute("token",token);
        //3.将token放入session
        HttpSession session = request.getSession();
        session.setAttribute("token",token);
        //4.跳转
        request.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(request,response);
    }
}
