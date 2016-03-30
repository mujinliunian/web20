package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单内容
        String id = request.getParameter("id");
        String prodName = request.getParameter("prodname");
        String prodprice = request.getParameter("prodprice");
        String num = request.getParameter("num");
        String address = request.getParameter("address");

        //修改产品


        Product product = new Product();
        product.setProdaddress(address);
        product.setProdname(prodName);
        product.setProdnum(Integer.valueOf(num));
        product.setProdprice(Float.valueOf(prodprice));
        product.setId(Integer.valueOf(id));


        ProductDao productDao = new ProductDao();
        productDao.update(product);

        //回到home
        response.sendRedirect("/home.do");
    }

}
