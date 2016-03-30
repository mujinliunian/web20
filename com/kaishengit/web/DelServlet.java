package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(id != null && id.matches("\\d+")) {
            ProductDao productDao = new ProductDao();
            productDao.del(Integer.valueOf(id));
        }

        response.sendRedirect("/home.do");
    }


}
