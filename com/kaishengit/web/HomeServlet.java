package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAll();

        request.setAttribute("productList", productList);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
        rd.forward(request, response);
    }
}
