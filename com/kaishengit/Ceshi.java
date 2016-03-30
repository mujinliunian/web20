package com.kaishengit;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;

import java.util.List;

public class Ceshi {
    public static void main(String[] args){
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAll();
        Product product = productList.get(2);
        System.out.println(product.getProdname()+";"+product.getProdprice()+";"+product.getProdaddress());
       // System.out.println(product.getProdnum());
    }
}
