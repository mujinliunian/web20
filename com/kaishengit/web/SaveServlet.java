package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单内容
        String prodName = request.getParameter("prodname");
        String prodprice = request.getParameter("prodprice");
        String num = request.getParameter("num");
        String address = request.getParameter("address");

        //将内容存储到数据库中
        Product product = new Product();
        product.setProdaddress(address);
        product.setProdname(prodName);
        product.setProdnum(Integer.valueOf(num));
        product.setProdprice(Float.valueOf(prodprice));

        ProductDao productDao = new ProductDao();
        productDao.save(product);

        //重定向到home.jsp
        response.sendRedirect("/home.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req,resp);
    }
}
