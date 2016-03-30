package com.kaishengit.web;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExcportCsvServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAll();

        //response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=GBK");
        response.addHeader("Content-Disposition","attachment;fileName=\"data.csv\"");

        PrintWriter out = response.getWriter();
        out.print("产品名称,价格,数量,产地\r\n");
        for(Product product : productList) {
            out.print(product.getProdname()+","+product.getProdprice()+","+product.getProdnum()+","+product.getProdaddress()+"\r\n");
        }

        out.flush();
        out.close();


    }
}
