package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(id != null && id.matches("\\d+")) {

            //根据id查找对应的Product对象
            ProductDao productDao = new ProductDao();

            Product product = productDao.findById(Integer.valueOf(id));

            if(product == null) {
                response.sendError(404,"产品未找到");
            } else {
                request.setAttribute("product", product);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/show.jsp");
                rd.forward(request, response);
            }

        } else {
            response.sendRedirect("/home.do");
        }
    }
}
